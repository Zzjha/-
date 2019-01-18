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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.adapter.DaiFukuanAdapter;
import soexample.umeng.com.dianshangproject.bean.DaiFukuanBean;
import soexample.umeng.com.dianshangproject.bean.DelListBean;
import soexample.umeng.com.dianshangproject.bean.GoPayBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class DaiFukuanFragment extends Fragment implements IView {

    @BindView(R.id.recy)
    RecyclerView recy;
    Unbinder unbinder;
    @BindView(R.id.cancelOrder)
    Button cancelOrder;
    @BindView(R.id.goPay)
    Button goPay;

    private SharedPreferences sp;
    private MyPresenter myPresenter;
    private List<DaiFukuanBean.OrderListBean> list = new ArrayList<>();
    private DaiFukuanAdapter adapter;
    private String userId;
    private String sessionId;
    private Map<String, Object> headermap;
    private List<GoPayBean> goPayBeans = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dai_fukuan, container, false);
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
        adapter = new DaiFukuanAdapter(list, getActivity());
        recy.setAdapter(adapter);
        Map<String, String> map = new HashMap<>();
        map.put("status", 1 + "");
        map.put("page", 1 + "");
        map.put("count", 5 + "");
        myPresenter.getHeaderRequest(Contacts.ORDERINFO, 9, headermap, map);


        //接口回调
        adapter.setClick(new DaiFukuanAdapter.CallBack() {
            @Override
            public void setItemClick(View view, int position) {
                String orderId = list.get(position).getOrderId();
                Map<String,String> map = new HashMap<>();
                map.put("orderId",orderId);
                myPresenter.deleteHeaderRequest(Contacts.DELLIST, 2, headermap, map);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }


    //IView
    @Override
    public void success(Object success) {
        if(success instanceof DaiFukuanBean){
            DaiFukuanBean bean = (DaiFukuanBean) success;
            list.addAll(bean.getOrderList());
            adapter.notifyDataSetChanged();
        }else if(success instanceof GoPayBean){
            GoPayBean bean = (GoPayBean) success;
            if(bean.getClass().equals("0000")){

            }
        }else if(success instanceof DelListBean){
            DelListBean bean = (DelListBean) success;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(getActivity(), bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(Object error) {

    }


    //点击
    @OnClick({R.id.cancelOrder, R.id.goPay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancelOrder:

                break;
            case R.id.goPay:
                Map<String, String> maps = new HashMap<>();
                maps.put("orderId", list.get(0).getOrderId()+"");
                maps.put("payType", 1 + "");
                myPresenter.postHeaderRequest(Contacts.GOPAY, 5, headermap, maps);
                Toast.makeText(getActivity(), goPayBeans.get(0).getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
