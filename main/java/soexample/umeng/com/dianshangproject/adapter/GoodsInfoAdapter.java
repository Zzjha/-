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
import soexample.umeng.com.dianshangproject.bean.GoodsInfoBean;
import soexample.umeng.com.dianshangproject.bean.XinpinBean;

/**
 * author:author${朱佳华}
 * data:2019/1/2
 * function：
 */
public class GoodsInfoAdapter extends RecyclerView.Adapter<GoodsInfoAdapter.ViewHolder> {

    private List<GoodsInfoBean.ResultBean> list;
    private Context context;

    public GoodsInfoAdapter(List<GoodsInfoBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.goodsinforitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GoodsInfoBean.ResultBean resultBean = list.get(i);
        Glide.with(context).load(resultBean.getMasterPic()).into(viewHolder.img);

        String commodityName = resultBean.getCommodityName();
        String substring = commodityName.substring(0, 3);
        viewHolder.title.setText(substring+"...");

        viewHolder.price.setText("￥"+resultBean.getPrice());
        viewHolder.saleNum.setText("已售"+resultBean.getSaleNum()+"件");

        viewHolder.cardview.setRadius(20);
        viewHolder.cardview.setCardElevation(8);
        viewHolder.cardview.setContentPadding(5,5,5,5);
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
        private TextView saleNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardview);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            saleNum = itemView.findViewById(R.id.saleNum);
        }
    }
}
