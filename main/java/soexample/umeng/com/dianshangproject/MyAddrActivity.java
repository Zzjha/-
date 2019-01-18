package soexample.umeng.com.dianshangproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.adapter.MyAddrAdapter;
import soexample.umeng.com.dianshangproject.bean.LoginBean;
import soexample.umeng.com.dianshangproject.bean.MorenAddrBean;
import soexample.umeng.com.dianshangproject.bean.MyAddrBean;
import soexample.umeng.com.dianshangproject.bean.RegisterBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class MyAddrActivity extends AppCompatActivity implements IView {

    @BindView(R.id.addBtn)
    Button addBtn;
    @BindView(R.id.xrecy)
    XRecyclerView xrecy;

    private SharedPreferences sp;
    private MyAddrBean myAddrBean;

    private Map<String,Object> mapheader;
    private MyPresenter myPresenter;
    private List<MyAddrBean.ResultBean> list = new ArrayList<>();
    private MyAddrAdapter adapter;
    private Map<String,String> map;

    private Map<String,String> map2;

    private Map<String,String> map3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addr);
        ButterKnife.bind(this);
        sp = getSharedPreferences("project",MODE_PRIVATE);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        xrecy.setLayoutManager(manager);
        myPresenter = new MyPresenter(this);
        adapter = new MyAddrAdapter(list,this);
        xrecy.setAdapter(adapter);
        //请求头
        mapheader = new HashMap<>();
        String sessionId = sp.getString("sessionId", "");
        String userId = sp.getString("userId", "");
        mapheader.put("sessionId",sessionId);
        mapheader.put("userId",userId);
        map = new HashMap<>();
        myPresenter.getHeaderRequest(Contacts.ADDRESSLIST,1,mapheader,map);


        //接口回调  设置默认地址
        adapter.setOnItemClickLinstener(new MyAddrAdapter.CallBack() {
            //设置默认地址
            @Override
            public void onItemCheck(View view, int position) {
                int id = list.get(position).getId();
                map2 = new HashMap<>();
                map2.put("id",id+"");
                //请求头
                String sessionId = sp.getString("sessionId", "");
                String userId = sp.getString("userId", "");
                mapheader.put("sessionId",sessionId);
                mapheader.put("userId",userId);
                myPresenter.postHeaderRequest(Contacts.MORENADDRESS,2,mapheader,map2);
            }

            //修改地址
            @Override
            public void onUpdataAddr(View view, int position) {
                Intent intent = new Intent(MyAddrActivity.this,UpdataAddrActivity.class);
                intent.putExtra("name", list.get(position).getRealName());
                intent.putExtra("phone", list.get(position).getPhone());
                intent.putExtra("address", list.get(position).getAddress());
                intent.putExtra("code", list.get(position).getZipCode());
                intent.putExtra("id", list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.addBtn)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addBtn:
                startActivity(new Intent(this, GetMyAddrMainActivity.class));
                break;
        }
    }

    //IView
    @Override
    public void success(Object success) {
        if(success instanceof MyAddrBean){
            myAddrBean = (MyAddrBean) success;
            list.addAll(myAddrBean.getResult());
            adapter.notifyDataSetChanged();

        }else if(success instanceof MorenAddrBean){
            MorenAddrBean morenAddrBean = (MorenAddrBean) success;
            Toast.makeText(this, morenAddrBean.getMessage(), Toast.LENGTH_SHORT).show();
            list.clear();
            myPresenter.getHeaderRequest(Contacts.ADDRESSLIST,1,mapheader,map);
        }
    }
    @Override
    public void error(Object error) {

    }
}
