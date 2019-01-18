package soexample.umeng.com.dianshangproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.dianshangproject.bean.MyCircleBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class MyCircleActivity extends AppCompatActivity implements IView {

    @BindView(R.id.xrecy)
    XRecyclerView xrecy;

    private SharedPreferences sp;

    private MyPresenter myPresenter;
    private Map<String,Object> headermap;
    private Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle);
        ButterKnife.bind(this);

        sp = getSharedPreferences("project",MODE_PRIVATE);

        myPresenter = new MyPresenter(this);

        //请求头
        headermap = new HashMap<>();
        String sessionId = sp.getString("sessionId", "");
        String userId = sp.getString("userId", "");
        headermap.put("sessionId",sessionId);
        headermap.put("userId",userId);

        map = new HashMap<>();
        map.put("page",1+"");
        map.put("count",5+"");
        myPresenter.getHeaderRequest(Contacts.MYCIRCLE,4,headermap,map);

    }


    //IView
    @Override
    public void success(Object success) {
        MyCircleBean bean = (MyCircleBean) success;
        Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void error(Object error) {

    }
}
