package soexample.umeng.com.dianshangproject;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.repo.XEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.bean.LoginBean;
import soexample.umeng.com.dianshangproject.bean.RegisterBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.utils.ZzUtils;
import soexample.umeng.com.dianshangproject.view.IView;

public class LoginActivity extends AppCompatActivity implements IView {

    @BindView(R.id.tel_login)
    XEditText telLogin;
    @BindView(R.id.pwd_login)
    XEditText pwdLogin;
    @BindView(R.id.jizhumm_login)
    CheckBox jizhummLogin;
    @BindView(R.id.kszhuce_login)
    TextView kszhuceLogin;
    @BindView(R.id.login)
    Button login;

    private MyPresenter myPresenter;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private String telLog;
    private String pwdLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        myPresenter = new MyPresenter(this);
        sp = getSharedPreferences("project",MODE_PRIVATE);
        edit = sp.edit();
        boolean jizhumm = sp.getBoolean("jizhumm",false);
        String phone = sp.getString("phone", null);
        String pwd = sp.getString("pwd", null);
        jizhummLogin.setChecked(jizhumm);
        if(jizhumm){
            telLogin.setText(phone);
            pwdLogin.setText(pwd);
        }
    }

    //点击
    @OnClick({R.id.jizhumm_login, R.id.kszhuce_login, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jizhumm_login:  //记住密码
                if(!jizhummLogin.isChecked()){
                    jizhummLogin.setChecked(false);
                }
                break;
            case R.id.kszhuce_login:  //快速注册
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:   //登录
                telLog = telLogin.getText().toString().trim();
                pwdLog = pwdLogin.getText().toString().trim();
                boolean zhengze = ZzUtils.zhengze(telLog);
                if(telLog.isEmpty() || pwdLog.isEmpty()){
                    Toast.makeText(this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    if (!zhengze){
                        Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Map<String,String> map = new HashMap<>();
                    map.put("phone",telLog);
                    map.put("pwd",pwdLog);
                    myPresenter.postRequest(Contacts.LOGIN,1,map);
                }
                break;
        }
    }

    //IView
    @Override
    public void success(Object success) {
        LoginBean loginBean = (LoginBean) success;
        if(jizhummLogin.isChecked()){
            edit.putBoolean("jizhumm",true);
            edit.putString("phone",telLog);
            edit.putString("pwd",pwdLog);
        }else{
            edit.putBoolean("jizhumm",false);
        }
        //把sessionId和userId保存
        edit.putString("userId",loginBean.getResult().getUserId()+"");
        edit.putString("sessionId",loginBean.getResult().getSessionId());
        edit.commit();
        Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);

        LoginBean.ResultBean result = loginBean.getResult();
        EventBus.getDefault().postSticky(result);
    }
    @Override
    public void error(Object error) {
        LoginBean loginBean = (LoginBean) error;
        Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
