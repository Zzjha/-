package soexample.umeng.com.dianshangproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
import butterknife.OnClick;
import soexample.umeng.com.dianshangproject.adapter.SearchAdapter;
import soexample.umeng.com.dianshangproject.bean.SearchBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class SearchActivity extends AppCompatActivity implements IView {

    @BindView(R.id.searchStr)
    EditText searchStr;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.xrecy)
    XRecyclerView xrecy;

    private MyPresenter myPresenter;
    private List<SearchBean.ResultBean> list = new ArrayList<>();
    private SearchAdapter adapter;
    private Map<String,String> map;
    private int index;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        myPresenter = new MyPresenter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        xrecy.setLayoutManager(manager);
        adapter = new SearchAdapter(list, this);
        xrecy.setAdapter(adapter);
        map = new HashMap<>();
        map.put("keyword",name);
        map.put("page",1+"");
        map.put("count",5+"");
        myPresenter.getRequest(Contacts.SEARCHGOODS,4,map);


        //上拉 下拉
        xrecy.setPullRefreshEnabled(true);
        xrecy.setLoadingMoreEnabled(true);
        xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                index = 1;
                map.put("page", index + "");
                myPresenter.getRequest(Contacts.SEARCHGOODS,4,map);
                adapter.notifyDataSetChanged();
                xrecy.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                map.put("page", (++index) + "");
                myPresenter.getRequest(Contacts.SEARCHGOODS,4,map);
                adapter.notifyDataSetChanged();
                xrecy.loadMoreComplete();
            }
        });


        //点击 接口回调  查看详情
        adapter.setListener(new SearchAdapter.CallBack() {
            @Override
            public void setOnItemListener(View view, int position) {
                int commodityId = list.get(position).getCommodityId();
                Intent intent = new Intent(SearchActivity.this,GoodsDetailsActivity.class);
                intent.putExtra("commodityId",commodityId);
                startActivity(intent);
            }
        });

    }


    //接收值
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void messageEventBus(String name) {
        this.name = name;
    }

    //IView
    @Override
    public void success(Object success) {
        SearchBean bean = (SearchBean) success;
        list.addAll(bean.getResult());
        adapter.notifyDataSetChanged();
    }
    @Override
    public void error(Object error) {

    }


    //点击
    @OnClick(R.id.search)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.search:
                String name = searchStr.getText().toString().trim();
                EventBus.getDefault().postSticky(name);
                startActivity(new Intent(this,SearchActivity.class));
                break;
        }
    }
}
