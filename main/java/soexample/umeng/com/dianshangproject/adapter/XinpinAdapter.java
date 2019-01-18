package soexample.umeng.com.dianshangproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.bean.XinpinBean;

/**
 * author:author${朱佳华}
 * data:2019/1/2
 * function：
 */
public class XinpinAdapter extends RecyclerView.Adapter<XinpinAdapter.ViewHolder> {

    private List<XinpinBean.ResultBean.RxxpBean.CommodityListBean> list;
    private Context context;

    public XinpinAdapter(List<XinpinBean.ResultBean.RxxpBean.CommodityListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.xinpinitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        XinpinBean.ResultBean.RxxpBean.CommodityListBean commodityListBean = list.get(i);
        Glide.with(context).load(commodityListBean.getMasterPic()).into(viewHolder.img);

        String commodityName = commodityListBean.getCommodityName();
        String substring = commodityName.substring(0, 3);
        viewHolder.title.setText(substring+"...");
        viewHolder.price.setText("￥"+commodityListBean.getPrice());

        viewHolder.cardview.setRadius(20);
        viewHolder.cardview.setCardElevation(8);
        viewHolder.cardview.setContentPadding(5,5,5,5);

        //点击跳转详情页面
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.setOnItemListener(v,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardview;
        private ImageView img;
        private TextView title;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardview);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }
    }



    //接口回调
    public interface CallBack{
        void setOnItemListener(View view,int position);
    }
    private SearchAdapter.CallBack callBack;
    public void setListener(SearchAdapter.CallBack callBack){
        this.callBack = callBack;
    }
    public void onClick(View view){
        if(callBack!=null){
            callBack.setOnItemListener(view, (Integer) view.getTag());
        }
    }
}
