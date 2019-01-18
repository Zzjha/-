package soexample.umeng.com.dianshangproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.bean.DaiFukuanBean;

/**
 * author:author${朱佳华}
 * data:2019/1/15
 */
public class DaiFukuanAdapter extends RecyclerView.Adapter<DaiFukuanAdapter.ViewHolder> {
    private List<DaiFukuanBean.OrderListBean> list;
    private Context context;

    public DaiFukuanAdapter(List<DaiFukuanBean.OrderListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.daifukuanitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        DaiFukuanBean.OrderListBean orderListBean = list.get(i);
        List<DaiFukuanBean.OrderListBean.DetailListBean> detailList = orderListBean.getDetailList();
        String commodityPic = detailList.get(0).getCommodityPic();
        String[] split = commodityPic.split(",");
        Glide.with(context).load(split[0]).into(viewHolder.img);
        viewHolder.title.setText(detailList.get(0).getCommodityName());
        viewHolder.price.setText("￥"+detailList.get(0).getCommodityPrice());
        viewHolder.dingdan.setText("订单号：  "+list.get(0).getOrderId());

        //点击删除订单
        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.setItemClick(v,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dingdan;
        private ImageView img;
        private TextView title;
        private TextView price;
        private Button del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dingdan = itemView.findViewById(R.id.dingdan);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            del = itemView.findViewById(R.id.del);

        }
    }


    //接口回调
    public interface CallBack{
        void setItemClick(View view,int position);
    }
    private CallBack callBack;
    public void setClick(CallBack callBack){
        this.callBack = callBack;
    }
}
