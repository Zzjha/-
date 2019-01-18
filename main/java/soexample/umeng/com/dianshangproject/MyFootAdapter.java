package soexample.umeng.com.dianshangproject;

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
import java.util.Random;

import soexample.umeng.com.dianshangproject.bean.MyFootBean;
import soexample.umeng.com.dianshangproject.bean.XinpinBean;

/**
 * author:author${朱佳华}
 * data:2019/1/15
 */
public class MyFootAdapter extends RecyclerView.Adapter<MyFootAdapter.ViewHolder> {
    private List<MyFootBean.ResultBean> list;
    private Context context;

    public MyFootAdapter(List<MyFootBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.myfootitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyFootBean.ResultBean bean = list.get(i);
        ViewGroup.LayoutParams params = viewHolder.itemView.getLayoutParams();
        Random random = new Random();
        int height = random.nextInt(300)+300;
        params.height = height;
        viewHolder.itemView.setLayoutParams(params);

        Glide.with(context).load(bean.getMasterPic()).into(viewHolder.img);
        String commodityName = bean.getCommodityName();
        String substring = commodityName.substring(0, 3);
        viewHolder.title.setText(substring+"...");
        viewHolder.price.setText("￥"+bean.getPrice());
        viewHolder.num.setText("已售"+bean.getBrowseNum()+"件");
        viewHolder.dateTime.setText(""+bean.getBrowseTime());

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
        private TextView num;
        private TextView dateTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardview);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            num = itemView.findViewById(R.id.num);
            dateTime = itemView.findViewById(R.id.dateTime);
        }
    }
}
