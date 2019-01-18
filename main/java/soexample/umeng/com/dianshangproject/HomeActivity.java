package soexample.umeng.com.dianshangproject;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.fragment.CircleFragment;
import soexample.umeng.com.dianshangproject.fragment.ListFragment;
import soexample.umeng.com.dianshangproject.fragment.MyFragment;
import soexample.umeng.com.dianshangproject.fragment.ShoppingFragment;
import soexample.umeng.com.dianshangproject.fragment.ShouyeFragment;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.frame)
    FrameLayout frame;

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame,new ShouyeFragment()).commit();
    }

    @OnClick({R.id.rb1,R.id.rb2,R.id.rb3,R.id.rb4,R.id.rb5,R.id.rg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb1:
                manager.beginTransaction().replace(R.id.frame, new ShouyeFragment()).commit();
                break;
            case R.id.rb2:
                manager.beginTransaction().replace(R.id.frame, new CircleFragment()).commit();
                break;
            case R.id.rb3:
                manager.beginTransaction().replace(R.id.frame, new ShoppingFragment()).commit();
                break;
            case R.id.rb4:
                manager.beginTransaction().replace(R.id.frame, new ListFragment()).commit();
                break;
            case R.id.rb5:
                manager.beginTransaction().replace(R.id.frame, new MyFragment()).commit();
                break;
        }
    }

}
