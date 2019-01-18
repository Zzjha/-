package soexample.umeng.com.dianshangproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.view.IView;

public class DaiShouhuoFragment extends Fragment implements IView {

    private MyPresenter myPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dai_shouhuo, container, false);
        myPresenter = new MyPresenter(this);
        return view;
    }



    //IView
    @Override
    public void success(Object success) {

    }
    @Override
    public void error(Object error) {

    }
}
