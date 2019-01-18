package soexample.umeng.com.dianshangproject.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.dianshangproject.QuanbuDingdanAdapter;
import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.adapter.DaiFukuanAdapter;
import soexample.umeng.com.dianshangproject.bean.DaiFukuanBean;
import soexample.umeng.com.dianshangproject.bean.GoPayBean;
import soexample.umeng.com.dianshangproject.bean.QuanbuDingdanBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanbuDingdanFragment extends Fragment implements IView {

    @BindView(R.id.recy)
    RecyclerView recy;
    Unbinder unbinder;
    @BindView(R.id.cancelOrder)
    Button cancelOrder;
    @BindView(R.id.goPay)
    Button goPay;

    private SharedPreferences sp;
    private MyPresenter myPresenter;
    private List<QuanbuDingdanBean.OrderListBean> list = new ArrayList<>();
    private QuanbuDingdanAdapter adapter;
    private String userId;
    private String sessionId;
    private Map<String, Object> headermap;
    private List<GoPayBean> goPayBeans = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quanbu_dingdan, container, false);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("project", Context.MODE_PRIVATE);
        myPresenter = new MyPresenter(this);
        userId = sp.getString("userId", "");
        sessionId = sp.getString("sessionId", "");
        headermap = new HashMap<>();
        headermap.put("userId", userId);
        headermap.put("sessionId", sessionId);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(manager);
        adapter = new QuanbuDingdanAdapter(list, getActivity());
        recy.setAdapter(adapter);
        Map<String, String> map = new HashMap<>();
        map.put("status", 0 + "");
        map.put("page", 1 + "");
        map.put("count", 5 + "");
        myPresenter.getHeaderRequest(Contacts.ORDERINFO, 10, headermap, map);
        return view;
    }

    @Override
    public void success(Object success) {
        if(success instanceof QuanbuDingdanBean){
            QuanbuDingdanBean bean = (QuanbuDingdanBean) success;
            list.addAll(bean.getOrderList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(Object error) {

    }
}
