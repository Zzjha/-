package soexample.umeng.com.dianshangproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.bean.GoodsInfoBean;
import soexample.umeng.com.dianshangproject.bean.ShopCarDataBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.sql.Dao;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;
import soexample.umeng.com.dianshangproject.weight.FlowView;

public class FlowActivity extends AppCompatActivity implements IView {

    @BindView(R.id.searchStr)
    EditText searchStr;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.zuijinsousuo)
    FlowView zuijinsousuo;
    @BindView(R.id.sousuofaxian)
    FlowView sousuofaxian;
    @BindView(R.id.delete)
    ImageView delete;
    private List<GoodsInfoBean.ResultBean> list = new ArrayList<>();

    private String[] datas = {"基本护肤", "面部清洁", "一叶子新鲜营养面膜", "法国兰蔻", "雅诗兰黛", "资生堂", "眼部护理", "悦诗风吟定妆粉", "脸部护肤"};
    private List<String> list1 = new ArrayList<>();  //最近搜索
    private List<String> list2 = new ArrayList<>();  //搜索发现
    private Dao dao;
    private String name;
    private MyPresenter myPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        ButterKnife.bind(this);
        initData();
        dao = new Dao(this);
        myPresenter = new MyPresenter(this);
        Map<String,String> map = new HashMap<>();
        map = new HashMap<>();
        map.put("keyword",name);
        map.put("page",1+"");
        map.put("count",5+"");
        myPresenter.getRequest(Contacts.SEARCHGOODS,4,map);
    }

    //给  搜索发现  赋值
    private void initData() {
        for (int i = 0; i < datas.length; i++) {
            list2.add(datas[i]);
        }
        sousuofaxian.setData(list2);
    }


    @OnClick({R.id.search,R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:  //搜索
                zuijinsousuo.removeChile();
                name = searchStr.getText().toString().trim();
                dao.add(name);
                list1.add(name);
                zuijinsousuo.setData(list1);
                if (name.isEmpty()) {
                    startActivity(new Intent(this, SearchErrorActivity.class));
                } else {
                    EventBus.getDefault().postSticky(name);
                    startActivity(new Intent(this,SearchActivity.class));
                }
                break;
            case R.id.delete:  //删除
                zuijinsousuo.removeChile();
                dao.del();
                list1.clear();
                break;
        }
    }

    @Override
    public void success(Object success) {
        ShopCarDataBean bean = (ShopCarDataBean) success;

    }

    @Override
    public void error(Object error) {

    }
}
