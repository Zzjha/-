package soexample.umeng.com.dianshangproject.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.dianshangproject.OrderActivity;
import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.adapter.ShoppingAdapter;
import soexample.umeng.com.dianshangproject.bean.ShopCarBean;
import soexample.umeng.com.dianshangproject.bean.ShopCarDataBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class ShoppingFragment extends Fragment implements IView {


    @BindView(R.id.recy)
    RecyclerView recy;
    Unbinder unbinder;
    @BindView(R.id.checkAll)
    CheckBox checkAll;
    @BindView(R.id.jiesuan)
    Button jiesuan;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.bigrl)
    RelativeLayout bigrl;
    @BindView(R.id.total)
    TextView total;

    private SharedPreferences sp;
    private MyPresenter myPresenter;
    private Map<String, Object> headermap;
    private Map<String, String> map;

    private List<ShopCarDataBean.ResultBean> list = new ArrayList<>();
    private ShoppingAdapter adapter;

    private String checkedId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        unbinder = ButterKnife.bind(this, view);

        myPresenter = new MyPresenter(this);
        sp = getActivity().getSharedPreferences("project", Context.MODE_PRIVATE);
        recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShoppingAdapter(list, getActivity());
        recy.setAdapter(adapter);
        //请求头 headermap
        String sessionId = sp.getString("sessionId", "");
        String userId = sp.getString("userId", "");
        headermap = new HashMap<>();
        headermap.put("sessionId", sessionId);
        headermap.put("userId", userId);
        //map
        map = new HashMap<>();
        myPresenter.getHeaderRequest(Contacts.ADDSHOPCAR, 7, headermap, map);


        //接口回调
        adapter.setOnLinstener(new ShoppingAdapter.CallBack() {
            @Override
            public void setChecked(View view, int position) {
                boolean b = adapter.isItemGoodChecked(position);
                adapter.setItemGoodChecked(position,!b);
                checkedId = adapter.getCheckedId();
                adapter.notifyDataSetChanged();
                flushBottom();
            }

            @Override
            public void setNum(int position, int num) {
                adapter.setGoodsNumber(position,num);
                adapter.notifyDataSetChanged();
                flushBottom();
            }
        });
        return view;
    }


    //IView
    @Override
    public void success(Object success) {
        ShopCarDataBean bean = (ShopCarDataBean) success;
        list.addAll(bean.getResult());
        adapter.notifyDataSetChanged();
    }
    @Override
    public void error(Object error) {
        ShopCarDataBean bean = (ShopCarDataBean) error;
        Toast.makeText(getActivity(), bean.getMessage(), Toast.LENGTH_SHORT).show();
    }


    //刷新底部数据
    private void flushBottom(){
        boolean allGoods = adapter.isAllGoods();
        checkAll.setChecked(allGoods);
        float allGoodsPrice = adapter.getAllGoodsPrice();
        total.setText("￥"+allGoodsPrice+"");
    }


    //点击
    @OnClick({R.id.jiesuan,R.id.checkAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jiesuan:   //结算
                Intent intent = new Intent(getActivity(),OrderActivity.class);
                List<ShopCarDataBean.ResultBean> mList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).isWhetherChecked()){
                      mList.add(list.get(i));
                    }
                }
                intent.putExtra("mList", new ShopCarBean(mList));
                startActivity(intent);
                break;
            case R.id.checkAll:  //全选、全不选
                boolean allGoods = adapter.isAllGoods();
                adapter.setAllGoodsIsChecked(!allGoods);
                adapter.notifyDataSetChanged();
                flushBottom();
                break;
        }
    }


    //解绑
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
