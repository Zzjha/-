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
import soexample.umeng.com.dianshangproject.bean.LoginBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class GetMyAddrMainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.getsjname)
    EditText getsjname;
    @BindView(R.id.gettelNum)
    EditText gettelNum;
    @BindView(R.id.getlocal)
    EditText getlocal;
    @BindView(R.id.getaddress)
    EditText getaddress;
    @BindView(R.id.getcode)
    EditText getcode;
    @BindView(R.id.sanjilb)
    ImageView sanjilb;
    @BindView(R.id.addBtn)
    Button addBtn;

    private Map<String,Object> headermap;
    private Map<String,String> map;
    private MyPresenter myPresenter;

    private SharedPreferences sp;

    private String sjName;
    private String telNum;
    private String getLocal;
    private String address;
    private String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_my_addr_main);
        ButterKnife.bind(this);

        myPresenter = new MyPresenter(this);
        sp = getSharedPreferences("project",MODE_PRIVATE);

    }

    //点击  添加地址
    @OnClick({R.id.addBtn,R.id.sanjilb})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.sanjilb:
                CityPickerView cityPickerView = new CityPickerView(GetMyAddrMainActivity.this);
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
                        getlocal.setText(province+"-"+city+"-"+district);
                        getcode.setText(code);
                    }
                });
                cityPickerView.show();
                break;
            case R.id.addBtn:
                //请求头
                String sessionId = sp.getString("sessionId", "");
                String userId = sp.getString("userId", "");
                headermap = new HashMap<>();
                headermap.put("sessionId",sessionId);
                headermap.put("userId",userId);

                //请求参数
                map = new HashMap<>();
                sjName = getsjname.getText().toString().trim();
                telNum = gettelNum.getText().toString().trim();
                getLocal = getlocal.getText().toString().trim();

                String[] split = getLocal.split("-");
                for (int i = 0; i < split.length; i++) {
                    getLocal+=split[i];
                }
                address = getaddress.getText().toString().trim();
                code = getcode.getText().toString().trim();
                if(sjName.isEmpty()){
                    Toast.makeText(this, "请将信息填写完整！！！", Toast.LENGTH_SHORT).show();
                }else {
                    map.put("realName",sjName);
                    map.put("phone",telNum);
                    map.put("address",getLocal+address);
                    map.put("zipCode",code);
                    myPresenter.postHeaderRequest(Contacts.ADDADDRESS,1,headermap,map);
                }
                Intent intent = new Intent(this,MyAddrActivity.class);
                startActivity(intent);
                break;
        }
    }

    //IView
    @Override
    public void success(Object success) {
        GetMyAddrBean bean = (GetMyAddrBean) success;
        Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void error(Object error) {
        GetMyAddrBean bean = (GetMyAddrBean) error;
        Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
