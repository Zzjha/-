package soexample.umeng.com.dianshangproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.dianshangproject.GoodsDetailsActivity;
import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.adapter.GoodsInfoAdapter;
import soexample.umeng.com.dianshangproject.adapter.MoliAdapter;
import soexample.umeng.com.dianshangproject.adapter.PinzhiAdapter;
import soexample.umeng.com.dianshangproject.adapter.SearchAdapter;
import soexample.umeng.com.dianshangproject.adapter.XinpinAdapter;
import soexample.umeng.com.dianshangproject.bean.GoodsInfoBean;
import soexample.umeng.com.dianshangproject.bean.LunboBean;
import soexample.umeng.com.dianshangproject.bean.XinpinBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class SearchoneFragment extends Fragment implements IView {

    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.xinpinXrecy)
    RecyclerView xinpinXrecy;
    @BindView(R.id.moliXrecy)
    RecyclerView moliXrecy;
    @BindView(R.id.pinzhiXrecy)
    RecyclerView pinzhiXrecy;
    Unbinder unbinder;
    @BindView(R.id.yellow)
    ImageView yellow;
    @BindView(R.id.purple)
    ImageView purple;
    @BindView(R.id.pink)
    ImageView pink;
    @BindView(R.id.fra)
    LinearLayout fra;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    @BindView(R.id.rxxp)
    TextView rxxp;
    @BindView(R.id.mlss)
    TextView mlss;
    @BindView(R.id.pzsh)
    TextView pzsh;
    @BindView(R.id.titleName)
    TextView titleName;
    private MyPresenter myPresenter;
    private List<LunboBean.ResultBean> list = new ArrayList<>();
    private ArrayList<String> mImgesUrl = new ArrayList<>();
    private Map<String, String> map2;
    private int index = 1;

    private List<XinpinBean.ResultBean.RxxpBean.CommodityListBean> xinpinList = new ArrayList<>();
    private XinpinAdapter xinpinAdapter;
    private List<XinpinBean.ResultBean.MlssBean.CommodityListBeanXX> moliList = new ArrayList<>();
    private MoliAdapter moliAdapter;
    private List<XinpinBean.ResultBean.PzshBean.CommodityListBeanX> pinzhiList = new ArrayList<>();
    private PinzhiAdapter pinzhiAdapter;
    private List<GoodsInfoBean.ResultBean> goodsList = new ArrayList<>();
    private GoodsInfoAdapter goodsInfoAdapter;

    private FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_searchone, container, false);
        unbinder = ButterKnife.bind(this, view);
        myPresenter = new MyPresenter(this);
        manager = getChildFragmentManager();

        //热销新品
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        xinpinXrecy.setLayoutManager(manager);
        xinpinAdapter = new XinpinAdapter(xinpinList, getActivity());
        xinpinXrecy.setAdapter(xinpinAdapter);
        //魔力时尚
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        moliXrecy.setLayoutManager(manager2);
        moliAdapter = new MoliAdapter(moliList, getActivity());
        moliXrecy.setAdapter(moliAdapter);
        //品质生活
        GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        pinzhiXrecy.setLayoutManager(manager3);
        pinzhiAdapter = new PinzhiAdapter(pinzhiList, getActivity());
        pinzhiXrecy.setAdapter(pinzhiAdapter);
        //商品详情
        GridLayoutManager manager4 = new GridLayoutManager(getActivity(), 2);
        xrecy.setLayoutManager(manager4);
        goodsInfoAdapter = new GoodsInfoAdapter(goodsList, getActivity());
        xrecy.setAdapter(goodsInfoAdapter);

        Map<String, String> map = new HashMap<>();
        myPresenter.getRequest(Contacts.LUNBO, 1, map);
        myPresenter.getRequest(Contacts.REXIAO, 2, map);

        map2 = new HashMap<>();
        map2.put("labelId", "1002");
        map2.put("page", "1");
        map2.put("count", "5");
        myPresenter.getRequest(Contacts.GOODSINFO, 5, map2);

        //上拉下拉
        xrecy.setPullRefreshEnabled(true);
        xrecy.setLoadingMoreEnabled(true);
        xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                goodsList.clear();
                index = 1;
                map2.put("page", index + "");
                myPresenter.getRequest(Contacts.GOODSINFO, 5, map2);
                goodsInfoAdapter.notifyDataSetChanged();
                xrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                map2.put("page", (++index) + "");
                myPresenter.getRequest(Contacts.GOODSINFO, 5, map2);
                goodsInfoAdapter.notifyDataSetChanged();
                xrecy.loadMoreComplete();
            }
        });


        //热销新品  接口回调  跳转详情
        xinpinAdapter.setListener(new SearchAdapter.CallBack() {
            @Override
            public void setOnItemListener(View view, int position) {
                int commodityId = xinpinList.get(position).getCommodityId();
                Intent intent = new Intent(getActivity(),GoodsDetailsActivity.class);
                intent.putExtra("commodityId",commodityId);
                startActivity(intent);
            }
        });

        //魔力时尚  接口回调  跳转详情
        moliAdapter.setListener(new SearchAdapter.CallBack() {
            @Override
            public void setOnItemListener(View view, int position) {
                int commodityId = moliList.get(position).getCommodityId();
                Intent intent = new Intent(getActivity(),GoodsDetailsActivity.class);
                intent.putExtra("commodityId",commodityId);
                startActivity(intent);
            }
        });

        //品质生辉  接口回调  跳转详情
        pinzhiAdapter.setListener(new SearchAdapter.CallBack() {
            @Override
            public void setOnItemListener(View view, int position) {
                int commodityId = pinzhiList.get(position).getCommodityId();
                Intent intent = new Intent(getActivity(),GoodsDetailsActivity.class);
                intent.putExtra("commodityId",commodityId);
                startActivity(intent);
            }
        });

        return view;
    }

    //IView
    @Override
    public void success(Object success) {
        if (success instanceof LunboBean) {    //轮播
            LunboBean lunboBean = (LunboBean) success;
            list.addAll(lunboBean.getResult());
            for (int i = 0; i < list.size(); i++) {
                mImgesUrl.add(list.get(i).getImageUrl());
            }
            if (!mImgesUrl.isEmpty()) {
                banner.setData(mImgesUrl, null);
                banner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(getActivity()).load(mImgesUrl.get(position)).into((ImageView) view);
                    }
                });
                banner.setPageChangeDuration(1000);
                banner.setPageTransformer(Transformer.Default);
            }
        } else if (success instanceof XinpinBean) {
            XinpinBean xinpinBean = (XinpinBean) success;
            xinpinList.addAll(xinpinBean.getResult().getRxxp().get(0).getCommodityList());
            xinpinAdapter.notifyDataSetChanged();

            moliList.addAll(xinpinBean.getResult().getMlss().get(0).getCommodityList());
            moliAdapter.notifyDataSetChanged();

            pinzhiList.addAll(xinpinBean.getResult().getPzsh().get(0).getCommodityList());
            pinzhiAdapter.notifyDataSetChanged();
        } else if (success instanceof GoodsInfoBean) {
            GoodsInfoBean bean = (GoodsInfoBean) success;
            goodsList.addAll(bean.getResult());
            goodsInfoAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.yellow, R.id.purple, R.id.pink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yellow:
                titleName.setText(rxxp.getText().toString().trim());
                ll.setVisibility(View.GONE);
                fra.setVisibility(View.VISIBLE);
                break;
            case R.id.purple:
                titleName.setText(mlss.getText().toString().trim());
                ll.setVisibility(View.GONE);
                fra.setVisibility(View.VISIBLE);
                break;
            case R.id.pink:
                titleName.setText(pzsh.getText().toString().trim());
                ll.setVisibility(View.GONE);
                fra.setVisibility(View.VISIBLE);
                break;
        }
    }
}
