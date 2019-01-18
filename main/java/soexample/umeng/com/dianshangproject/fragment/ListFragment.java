package soexample.umeng.com.dianshangproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.dianshangproject.R;

public class ListFragment extends Fragment {

    @BindView(R.id.qbdd)
    ImageView qbdd;
    @BindView(R.id.dfk)
    ImageView dfk;
    @BindView(R.id.dsh)
    ImageView dsh;
    @BindView(R.id.dpj)
    ImageView dpj;
    @BindView(R.id.ywc)
    ImageView ywc;
    @BindView(R.id.list_frame)
    FrameLayout frame;
    Unbinder unbinder;
    @BindView(R.id.list_card)
    CardView listCard;

    private FragmentManager manager;
    private QuanbuDingdanFragment quanbuDingdanFragment;
    private DaiFukuanFragment daiFukuanFragment;
    private DaiShouhuoFragment daiShouhuoFragment;
    private DaiPingjiaFragment daiPingjiaFragment;
    private YiWanchengFragment yiWanchengFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        manager = getFragmentManager();
        initData();
        return view;
    }

    private void initData() {
        quanbuDingdanFragment = new QuanbuDingdanFragment();
        daiFukuanFragment = new DaiFukuanFragment();
        daiShouhuoFragment = new DaiShouhuoFragment();
        daiPingjiaFragment = new DaiPingjiaFragment();
        yiWanchengFragment = new YiWanchengFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.qbdd, R.id.dfk, R.id.dsh, R.id.dpj, R.id.ywc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qbdd:
                manager.beginTransaction().replace(R.id.list_frame, quanbuDingdanFragment).commit();
                break;
            case R.id.dfk:
                manager.beginTransaction().replace(R.id.list_frame, daiFukuanFragment).commit();
                break;
            case R.id.dsh:
                manager.beginTransaction().replace(R.id.list_frame, daiShouhuoFragment).commit();
                break;
            case R.id.dpj:
                manager.beginTransaction().replace(R.id.list_frame, daiPingjiaFragment).commit();
                break;
            case R.id.ywc:
                manager.beginTransaction().replace(R.id.list_frame, yiWanchengFragment).commit();
                break;
        }
    }
}
