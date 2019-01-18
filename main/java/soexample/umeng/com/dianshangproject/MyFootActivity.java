package soexample.umeng.com.dianshangproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.dianshangproject.bean.MyCircleBean;
import soexample.umeng.com.dianshangproject.bean.MyFootBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class MyFootActivity extends AppCompatActivity implements IView {

    @BindView(R.id.xrecy)
    XRecyclerView xrecy;

    private SharedPreferences sp;

    private MyPresenter myPresenter;
    private Map<String,Object> headermap;
    private Map<String,String> map;
    private List<MyFootBean.ResultBean> list = new ArrayList<>();
    private MyFootAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_foot);
        ButterKnife.bind(this);
        myPresenter = new MyPresenter(this);
        sp = getSharedPreferences("project",MODE_PRIVATE);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        xrecy.setLayoutManager(manager);


        //请求头
        headermap = new HashMap<>();
        String sessionId = sp.getString("sessionId", "");
        String userId = sp.getString("userId", "");
        headermap.put("sessionId",sessionId);
        headermap.put("userId",userId);
        adapter = new MyFootAdapter(list,this);
        xrecy.setAdapter(adapter);
        map = new HashMap<>();
        map.put("page",1+"");
        map.put("count",20+"");
        myPresenter.getHeaderRequest(Contacts.MYFOOT,5,headermap,map);
    }

    @Override
    public void success(Object success) {
        MyFootBean bean = (MyFootBean) success;
        list.addAll(bean.getResult());
        adapter.notifyDataSetChanged();
        Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(Object error) {

    }
}
