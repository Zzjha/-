package soexample.umeng.com.dianshangproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.repo.XEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.bean.RegisterBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.utils.ZzUtils;
import soexample.umeng.com.dianshangproject.view.IView;

public class RegisterActivity extends AppCompatActivity implements IView {

    @BindView(R.id.tel_register)
    EditText telRegister;
    @BindView(R.id.code_register)
    EditText codeRegister;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.pwd_register)
    XEditText pwdRegister;
    @BindView(R.id.register_login)
    TextView registerLogin;
    @BindView(R.id.register)
    Button register;

    private MyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        myPresenter = new MyPresenter(this);
    }

    @OnClick({R.id.getcode, R.id.register_login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //获取验证码
            case R.id.getcode:
                break;
            //立即登录
            case R.id.register_login:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            //注册
            case R.id.register:
                String telRes = telRegister.getText().toString().trim();
                String pwdRes = pwdRegister.getText().toString().trim();
                boolean zhengze = ZzUtils.zhengze(telRes);
                if(telRes.isEmpty() || pwdRes.isEmpty()){
                    Toast.makeText(this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    if (!zhengze){
                        Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Map<String,String> map = new HashMap<>();
                    map.put("phone",telRes);
                    map.put("pwd",pwdRes);
                    myPresenter.postRequest(Contacts.REGISTER,2,map);
                }
                break;
        }
    }


    //View
    @Override
    public void success(Object success){
        RegisterBean registerBean = (RegisterBean) success;
        if(registerBean.getStatus().equals("0000")){
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void error(Object error) {
        RegisterBean registerBean = (RegisterBean) error;
        Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
