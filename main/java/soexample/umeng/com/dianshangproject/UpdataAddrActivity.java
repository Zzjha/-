package soexample.umeng.com.dianshangproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPickerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.bean.GetMyAddrBean;
import soexample.umeng.com.dianshangproject.bean.MyAddrBean;
import soexample.umeng.com.dianshangproject.bean.UpdateNickAndPwdBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class UpdataAddrActivity extends AppCompatActivity implements IView {

    @BindView(R.id.sjr)
    TextView sjr;
    @BindView(R.id.updsjname)
    EditText updsjname;
    @BindView(R.id.sjhm)
    TextView sjhm;
    @BindView(R.id.updtelNum)
    EditText updtelNum;
    @BindView(R.id.szdq)
    TextView szdq;
    @BindView(R.id.updlocal)
    EditText updlocal;
    @BindView(R.id.sanjilb)
    ImageView sanjilb;
    @BindView(R.id.xqdz)
    TextView xqdz;
    @BindView(R.id.updaddress)
    EditText updaddress;
    @BindView(R.id.yzbm)
    TextView yzbm;
    @BindView(R.id.updcode)
    EditText updcode;
    @BindView(R.id.updBtn)
    Button updBtn;

    private SharedPreferences sp;

    private Map<String,Object> headermap;
    private Map<String,String> map;
    private MyPresenter myPresenter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_addr);
        ButterKnife.bind(this);

        myPresenter = new MyPresenter(this);
        sp = getSharedPreferences("project",MODE_PRIVATE);

        intent = getIntent();
        updsjname.setText(intent.getStringExtra("name"));
        updtelNum.setText(intent.getStringExtra("phone"));
        String address = intent.getStringExtra("address");
        String[] split = address.split("-");
        String local = split[0];
//        String content = split[1];
        updlocal.setText(local);
      //  updaddress.setText(content);
        updcode.setText(intent.getStringExtra("code"));
    }


    @OnClick({R.id.sanjilb, R.id.updBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sanjilb:
                CityPickerView cityPickerView = new CityPickerView(UpdataAddrActivity.this);
                cityPickerView.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        //省份
                        String province = citySelected[0];
                        //城市
                        String city = citySelected[1];
                        //区县
                        String district = citySelected[2];
                        //邮编
                        String code = citySelected[3];
                        updlocal.setText(province+"-"+city+"-"+district);
                        updcode.setText(code);
                    }
                });
                cityPickerView.show();
                break;
            case R.id.updBtn:
                //请求头
                String sessionId = sp.getString("sessionId", "");
                String userId = sp.getString("userId", "");
                headermap = new HashMap<>();
                headermap.put("sessionId",sessionId);
                headermap.put("userId",userId);

                //请求参数
                map = new HashMap<>();
                String trimName = updsjname.getText().toString().trim();
                String trimTel = updtelNum.getText().toString().trim();
                String trimLocal = updlocal.getText().toString().trim();

                String[] split = trimLocal.split("-");
                for (int i = 0; i < split.length; i++) {
                    trimLocal+=split[i];
                }
                String trimAddress = updaddress.getText().toString().trim();
                String trimCode = updcode.getText().toString().trim();
                map = new HashMap<>();
                map.put("realName",trimName);
                map.put("phone",trimTel);
                map.put("address",trimLocal+trimAddress);
                map.put("zipCode",trimCode);
                map.put("id",intent.getIntExtra("id",0)+"");
                myPresenter.putHeaderRequest(Contacts.UPDATEADDRESS,1,headermap,map);


                //跳转返回收货地址列表
                Map<String,String> maps = new HashMap<>();
                myPresenter.getHeaderRequest(Contacts.ADDRESSLIST,1,headermap,maps);
                startActivity(new Intent(UpdataAddrActivity.this,MyAddrActivity.class));
                break;
        }
    }


    //IView
    @Override
    public void success(Object success) {
        UpdateNickAndPwdBean bean = (UpdateNickAndPwdBean) success;
        Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();

    }
    @Override
    public void error(Object error) {
        UpdateNickAndPwdBean bean = (UpdateNickAndPwdBean) error;
        Log.d("error",error+"");
    }
}
