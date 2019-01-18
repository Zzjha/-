package soexample.umeng.com.dianshangproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.bean.GoodsDetailsBean;
import soexample.umeng.com.dianshangproject.bean.ShopCarDataBean;
import soexample.umeng.com.dianshangproject.bean.TongBuBean;
import soexample.umeng.com.dianshangproject.fragment.ShoppingFragment;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class GoodsDetailsActivity extends AppCompatActivity implements IView {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.saleNum)
    TextView saleNum;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.addcar)
    ImageView addcar;
    @BindView(R.id.buy)
    ImageView buy;
    @BindView(R.id.webview)
    WebView webview;

    private String commodityId;
    private SharedPreferences sp;
    private Map<String, Object> headmap;
    private Map<String, String> map;
    private Map<String, String> map2;
    private MyPresenter myPresenter;
    private List<String> imgList = new ArrayList<>();  //图片轮播集合
    private List<ShopCarDataBean.ResultBean> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        ButterKnife.bind(this);
        sp = getSharedPreferences("project", MODE_PRIVATE);
        myPresenter = new MyPresenter(this);
        Intent intent = getIntent();
        commodityId = intent.getIntExtra("commodityId", 0) + "";

        //请求头
        headmap = new HashMap<>();
        String sessionId = sp.getString("sessionId", "");
        String userId = sp.getString("userId", "");
        headmap.put("sessionId", sessionId);
        headmap.put("userId", userId);
        map = new HashMap<>();
        map.put("commodityId", commodityId + "");
        myPresenter.getHeaderRequest(Contacts.GOODSDETAILS, 6, headmap, map);
    }


    //IView
    @Override
    public void success(Object success) {
        //详情
        if(success instanceof GoodsDetailsBean){
            GoodsDetailsBean bean = (GoodsDetailsBean) success;
            GoodsDetailsBean.ResultBean result = bean.getResult();
            //轮播
            String picture = result.getPicture();
            String[] split = picture.split("\\,");
            for (int i = 0; i < split.length; i++) {
                imgList.add(split[i]);
            }
            if (!imgList.isEmpty()) {
                banner.setData(imgList, null);
                banner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(GoodsDetailsActivity.this).load(imgList.get(position)).into((ImageView) view);
                    }
                });
                banner.setPageTransformer(Transformer.Default);
            }
            //赋值
            price.setText("￥" + result.getPrice() + "");
            saleNum.setText("已售" + result.getSaleNum() + "件");
            title.setText(result.getCategoryName());
            weight.setText("重量：" + result.getWeight() + "kg");
            //webview网页
            WebSettings settings = webview.getSettings();
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setJavaScriptEnabled(true);//支持JS
            String js = "<script type=\"text/javascript\">" +
                    "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                    "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                    "imgs[i].style.width = '100%';" +  // 宽度改为100%
                    "imgs[i].style.height = 'auto';" +
                    "}" +
                    "</script>";
            webview.setWebViewClient(new WebViewClient());
            webview.loadDataWithBaseURL(null, result.getDetails() + js, "text/html", "utf-8", null);
        }else if(success instanceof ShopCarDataBean){
            //同步到购物车
            ShopCarDataBean bean = (ShopCarDataBean) success;
            list.addAll(bean.getResult());
            if(list.size()!=0){
                JSONArray jsonArray=new JSONArray();
                for(int i=0;i<list.size();i++) {
                    JSONObject jsonObject=null;
                    jsonObject=new JSONObject();
                    try {
                        jsonObject.put("commodityId",list.get(i).getCommodityId());
                        jsonObject.put("count",1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                try {
                    JSONObject jsonObject=null;
                    jsonObject = new JSONObject();
                    jsonObject.put("commodityId",commodityId);
                    jsonObject.put("count",1);
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                map.put("data",jsonArray.toString());
                //同步
                map2 = new HashMap<>();
                map2.put("data",jsonArray.toString());
                myPresenter.putHeaderRequest(Contacts.SHOPCARDATA,2,headmap,map2);
            }else{
                myPresenter.putHeaderRequest(Contacts.SHOPCARDATA,2,headmap,map2);
            }
        }
    }
    @Override
    public void error(Object error) {

    }


    //点击
    @OnClick({R.id.back, R.id.addcar, R.id.buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.addcar:
                //先查询购物车
                myPresenter.getHeaderRequest(Contacts.ADDSHOPCAR, 7, headmap, map);
                break;
            case R.id.buy:
                break;
        }
    }
}
