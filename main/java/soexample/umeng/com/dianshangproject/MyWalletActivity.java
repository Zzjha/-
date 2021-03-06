package soexample.umeng.com.dianshangproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.dianshangproject.bean.LoginBean;
import soexample.umeng.com.dianshangproject.bean.MyWalletBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class MyWalletActivity extends AppCompatActivity implements IView {

    @BindView(R.id.money)
    TextView money;

    private SharedPreferences sp;

    private MyPresenter myPresenter;
    private Map<String,Object> headermap;
    private Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
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
        map.put("count",1+"");
        myPresenter.getHeaderRequest(Contacts.WALLET,2,headermap,map);
    }


    //IView
    @Override
    public void success(Object success) {
        MyWalletBean bean = (MyWalletBean) success;
        money.setText(bean.getResult().getBalance()+"");
    }
    @Override
    public void error(Object error) {

    }
}
