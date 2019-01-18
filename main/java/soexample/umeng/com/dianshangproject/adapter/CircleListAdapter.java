package soexample.umeng.com.dianshangproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.bean.CircleListBean;
import soexample.umeng.com.dianshangproject.bean.XinpinBean;

/**
 * author:author${朱佳华}
 * data:2019/1/2
 * function：
 */
public class CircleListAdapter extends RecyclerView.Adapter<CircleListAdapter.ViewHolder> {

    private List<CircleListBean.ResultBean> list;
    private Context context;

    public CircleListAdapter(List<CircleListBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.circlelistitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        CircleListBean.ResultBean resultBean = list.get(i);
        Glide.with(context).load(resultBean.getHeadPic()).into(viewHolder.headPic);
        viewHolder.nickName.setText(resultBean.getNickName());
        //时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(resultBean.getCreateTime());
        viewHolder.creatTime.setText(dateString);
        viewHolder.content.setText(resultBean.getContent());
        Glide.with(context).load(resultBean.getImage()).into(viewHolder.img1);
        Glide.with(context).load(resultBean.getImage()).into(viewHolder.img2);
        viewHolder.greatNum.setText(resultBean.getGreatNum()+"");

        viewHolder.cardview.setRadius(20);
        viewHolder.cardview.setCardElevation(8);
        viewHolder.cardview.setContentPadding(5,5,5,5);

        if(list.get(i).getWhetherGreat() == 2){
            Glide.with(context).load(R.mipmap.common_btn_prise_n).into(viewHolder.praise);
        }else{
            Glide.with(context).load(R.mipmap.common_btn_prise_s).into(viewHolder.praise);
        }
        viewHolder.praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.setGreat(v,i);
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
        private ImageView headPic;
        private TextView nickName;
        private TextView creatTime;
        private TextView content;
        private ImageView img1;
        private ImageView img2;
        private ImageView praise;
        private TextView greatNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardview);
            headPic = itemView.findViewById(R.id.headPic);
            nickName = itemView.findViewById(R.id.nickName);
            creatTime = itemView.findViewById(R.id.creatTime);
            content = itemView.findViewById(R.id.content);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            praise = itemView.findViewById(R.id.praise);
            greatNum = itemView.findViewById(R.id.greatNum);
        }
    }


    //接口回调
    public interface CallBack{
        void setGreat(View view,int position);
    }
    private CallBack callBack;
    public void setGreat(CallBack callBack){
        this.callBack = callBack;
    }
    public boolean ifGreat(int i){
        int whetherGreat = list.get(i).getWhetherGreat();
        if(whetherGreat==1){
            return true;
        }else {
            return false;
        }
    }
}
