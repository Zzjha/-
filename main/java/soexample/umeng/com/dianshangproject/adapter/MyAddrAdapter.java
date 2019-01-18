package soexample.umeng.com.dianshangproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.bean.MyAddrBean;

/**
 * author:author${朱佳华}
 * data:2019/1/7
 */
public class MyAddrAdapter extends RecyclerView.Adapter<MyAddrAdapter.ViewHolder> {
    private List<MyAddrBean.ResultBean> list;
    private Context context;

    public MyAddrAdapter(List<MyAddrBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.myaddritem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(list.get(i).getRealName());
        viewHolder.tel.setText(list.get(i).getPhone());
        viewHolder.addr.setText(list.get(i).getAddress());

        if (list.get(i).getWhetherDefault()==1){
            viewHolder.ckb.setChecked(true);
        }else{
            viewHolder.ckb.setChecked(false);
        }
        //设置默认地址
        viewHolder.ckb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.onItemCheck(v,i);
                }
            }
        });


        //修改地址
        viewHolder.xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.onUpdataAddr(v,i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView tel;
        private TextView addr;
        private CheckBox ckb;
        private TextView xiugai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            tel = itemView.findViewById(R.id.tel);
            addr = itemView.findViewById(R.id.addr);
            ckb = itemView.findViewById(R.id.ckb);
            xiugai = itemView.findViewById(R.id.xiugai);
        }
    }




    //接口回调
    public interface CallBack{
        void onItemCheck(View view,int position);
        void onUpdataAddr(View view,int position);
    }
    private CallBack callBack;
    public void setOnItemClickLinstener(CallBack callBack){
        this.callBack = callBack;
    }
}
