package soexample.umeng.com.dianshangproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.adapter.CircleListAdapter;
import soexample.umeng.com.dianshangproject.bean.CancleGreatBean;
import soexample.umeng.com.dianshangproject.bean.CircleListBean;
import soexample.umeng.com.dianshangproject.bean.GreatBean;
import soexample.umeng.com.dianshangproject.bean.LoginBean;
import soexample.umeng.com.dianshangproject.bean.RegisterBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class CircleFragment extends Fragment implements IView {


    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    Unbinder unbinder;

    private MyPresenter myPresenter;
    private List<CircleListBean.ResultBean> list = new ArrayList<>();
    private CircleListAdapter adapter;
    private Map<String, String> map;

    private String sessionId;
    private int userId;

    private Map<String, Object> mapheader;
    private int index = 1;

    public CircleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        xrecy.setLayoutManager(manager);
        myPresenter = new MyPresenter(this);
        adapter = new CircleListAdapter(list, getActivity());
        xrecy.setAdapter(adapter);
        map = new HashMap<>();
        map.put("userId", "1010");
        map.put("sessionId", "15320748258726");
        map.put("page", "1");
        map.put("count", "5");
        myPresenter.getRequest(Contacts.CIRCLELIST, 3, map);


        //上拉下拉
        xrecy.setPullRefreshEnabled(true);
        xrecy.setLoadingMoreEnabled(true);
        xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                index = 1;
                map.put("page", index + "");
                myPresenter.getRequest(Contacts.CIRCLELIST, 3, map);
                adapter.notifyDataSetChanged();
                xrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                map.put("page", (++index) + "");
                myPresenter.getRequest(Contacts.CIRCLELIST, 3, map);
                adapter.notifyDataSetChanged();
                xrecy.loadMoreComplete();
            }
        });


        //判断点赞  接口回调
        adapter.setGreat(new CircleListAdapter.CallBack() {
            @Override
            public void setGreat(View view, int position) {
                mapheader = new HashMap<>();
                map.put("circleId", list.get(position).getId() + "");
                if (list.get(position).getWhetherGreat() == 2) {
                    list.get(position).setWhetherGreat(1);
                    list.get(position).setGreatNum(list.get(position).getGreatNum() + 1);
                    adapter.notifyDataSetChanged();
                    mapheader.put("userId", userId + "");
                    mapheader.put("sessionId", sessionId);
                    myPresenter.postHeaderRequest(Contacts.GREAT, 3, mapheader, map);
                } else {
                    list.get(position).setWhetherGreat(2);
                    list.get(position).setGreatNum(list.get(position).getGreatNum() - 1);
                    adapter.notifyDataSetChanged();
                    mapheader.put("userId", userId + "");
                    mapheader.put("sessionId", sessionId);
                    myPresenter.deleteHeaderRequest(Contacts.CANCLEGREAT, 1, mapheader, map);
                }
            }
        });
        return view;
    }

    //获取请求头
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getHeadParam(LoginBean.ResultBean resultBean) {
        String sessionId = resultBean.getSessionId();
        this.sessionId = sessionId;
        int userId = resultBean.getUserId();
        this.userId = userId;
    }


    @Override
    public void success(Object success) {
        if (success instanceof CircleListBean) {
            CircleListBean circleListBean = (CircleListBean) success;
            list.addAll(circleListBean.getResult());
            adapter.notifyDataSetChanged();
        }else if (success instanceof GreatBean) {
            GreatBean greatBean = (GreatBean) success;
            Toast.makeText(getActivity(),greatBean.getMessage() , Toast.LENGTH_SHORT).show();
        }else if (success instanceof RegisterBean) {
            RegisterBean registerBean = (RegisterBean) success;
            Toast.makeText(getActivity(),registerBean.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
