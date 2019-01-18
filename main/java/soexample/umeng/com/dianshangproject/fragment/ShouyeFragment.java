package soexample.umeng.com.dianshangproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.dianshangproject.FlowActivity;
import soexample.umeng.com.dianshangproject.R;

public class ShouyeFragment extends Fragment{

    Unbinder unbinder;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.searchStr)
    EditText searchStr;
    @BindView(R.id.frame)
    FrameLayout frame;

    private FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        unbinder = ButterKnife.bind(this, view);

        manager = getChildFragmentManager();
        manager.beginTransaction().replace(R.id.frame, new SearchoneFragment()).commit();
        return view;
    }


  //点击  输入框  出现  流式布局
    @OnClick({R.id.searchStr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchStr:
                startActivity(new Intent(getActivity(),FlowActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}