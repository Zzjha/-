package soexample.umeng.com.dianshangproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.dianshangproject.MyAddrActivity;
import soexample.umeng.com.dianshangproject.MyCircleActivity;
import soexample.umeng.com.dianshangproject.MyFootActivity;
import soexample.umeng.com.dianshangproject.MyInfoActivity;
import soexample.umeng.com.dianshangproject.MyWalletActivity;
import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.bean.MyInfoBean;

public class MyFragment extends Fragment {

    @BindView(R.id.myInfo)
    TextView myInfo;
    @BindView(R.id.myCircle)
    TextView myCircle;
    @BindView(R.id.myFoot)
    TextView myFoot;
    @BindView(R.id.myWallet)
    TextView myWallet;
    @BindView(R.id.myAddr)
    TextView myAddr;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.iconPic)
    ImageView iconPic;
    Unbinder unbinder;
    @BindView(R.id.nickName)
    TextView nickName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(MyInfoBean.ResultBean bean) {
        String headPics = bean.getHeadPic();
        Glide.with(this).load(headPics).into(iconPic);
        String nickNames = bean.getNickName();
        nickName.setText(nickNames);
    }


    //点击
    @OnClick({R.id.myInfo, R.id.myCircle, R.id.myFoot, R.id.myWallet, R.id.myAddr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myInfo:
                startActivity(new Intent(getActivity(), MyInfoActivity.class));
                break;
            case R.id.myCircle:
                startActivity(new Intent(getActivity(), MyCircleActivity.class));
                break;
            case R.id.myFoot:
                startActivity(new Intent(getActivity(), MyFootActivity.class));
                break;
            case R.id.myWallet:
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.myAddr:
                startActivity(new Intent(getActivity(), MyAddrActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
