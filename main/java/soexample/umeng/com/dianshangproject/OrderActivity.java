package soexample.umeng.com.dianshangproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.adapter.ShoppingAdapter2;
import soexample.umeng.com.dianshangproject.bean.GoodsDetailsBean;
import soexample.umeng.com.dianshangproject.bean.MyAddrBean;
import soexample.umeng.com.dianshangproject.bean.ShopCarBean;
import soexample.umeng.com.dianshangproject.bean.ShopCarDataBean;
import soexample.umeng.com.dianshangproject.bean.WaitPayBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class OrderActivity extends AppCompatActivity implements IView {


    @BindView(R.id.addAddr)
    Button addAddr;
    @BindView(R.id.send_order_elect)
    TextView sendOrderElect;
    @BindView(R.id.send_order_erect)
    TextView sendOrderErect;
    @BindView(R.id.names)
    TextView names;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.addr)
    TextView addr;
    @BindView(R.id.send_order_across)
    TextView sendOrderAcross;
    @BindView(R.id.liear)
    LinearLayout liear;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.sunCountPay)
    TextView sunCountPay;
    @BindView(R.id.commit)
    Button commit;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.cardView)
    CardView cardView;
    private MyPresenter myPresenter;
    private List<ShopCarDataBean.ResultBean> list = new ArrayList<>();
    private List<MyAddrBean.ResultBean> addList = new ArrayList<>();
    private ShoppingAdapter2 adapter;
    private MyAddrAdapter2 adapter2;
    private Map<String, Object> headermap;
    private SharedPreferences sp;
    private View inflate;

    private String userId;
    private String sessionId;
    private int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        sp = getSharedPreferences("project", MODE_PRIVATE);
        myPresenter = new MyPresenter(this);
        userId = sp.getString("userId", "");
        sessionId = sp.getString("sessionId", "");
        headermap = new HashMap<>();
        headermap.put("userId", userId);
        headermap.put("sessionId", sessionId);
        Intent intent = getIntent();
        ShopCarBean bean = (ShopCarBean) intent.getSerializableExtra("mList");
        list = bean.getmList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
        adapter = new ShoppingAdapter2(list, this);
        recy.setAdapter(adapter);
        for (int i = 0; i < bean.getmList().size(); i++) {
            sum += bean.getmList().get(i).getPrice() * bean.getmList().get(i).getCount();
        }
        sunCountPay.setText("共" + bean.getmList().size() + "件商品，需要付款" + sum + "元");
    }

    //点击
    @OnClick({R.id.addAddr, R.id.commit,R.id.send_order_elect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addAddr:  //添加收货地址
                addAddr.setVisibility(View.VISIBLE);
                inflate = LayoutInflater.from(this).inflate(R.layout.addresspop, null);
                RecyclerView pRecycler_view = inflate.findViewById(R.id.pRecycler_view);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                pRecycler_view.setLayoutManager(linearLayoutManager);
                adapter2 = new MyAddrAdapter2(addList, this);
                pRecycler_view.setAdapter(adapter2);
                HashMap<String, String> map = new HashMap<>();
                myPresenter.getHeaderRequest(Contacts.ADDRESSLIST, 1, headermap, map);
                 pop();

                break;
            case R.id.commit:   //提交订单
                JSONArray jsonArray=new JSONArray();
                for(int i=0;i<list.size();i++)
                {
                    try {
                        JSONObject jsonObject=null;
                        jsonObject=new JSONObject();
                        jsonObject.put("commodityId",list.get(i).getCommodityId());
                        jsonObject.put("amount",list.get(i).getCount());
                        jsonArray.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Map<String,String> mMap = new HashMap<>();
                mMap.put("orderInfo",jsonArray.toString());
                mMap.put("totalPrice",sum+"");
                mMap.put("addressId", addList.get(0).getId()+"");
                myPresenter.postHeaderRequest(Contacts.WAITPAY, 8,headermap,mMap);
                Toast.makeText(this, "创建订单成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.send_order_elect:
                pop();
                break;
        }
    }

    private void pop() {
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, 500, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(addAddr);
        adapter2.setOnItemClickLinstener(new MyAddrAdapter2.CallBack() {
            @Override
            public void onItemCheck(int position, String name, String phone, String address) {
                addr.setText(address);
                tel.setText(phone);
                names.setText(name);
                popupWindow.dismiss();
                addAddr.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }

    //IView
    @Override
    public void success(Object success) {
        if (success instanceof MyAddrBean) {
            MyAddrBean bean = (MyAddrBean) success;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            addList.addAll(bean.getResult());
            adapter2.notifyDataSetChanged();
        }else if (success instanceof WaitPayBean) {
            WaitPayBean beans = (WaitPayBean) success;
            Toast.makeText(this, beans.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(Object error) {

    }
}
