package soexample.umeng.com.dianshangproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.dianshangproject.bean.MyAddrBean;

/**
 * author:author${朱佳华}
 * data:2019/1/7
 */
public class MyAddrAdapter2 extends RecyclerView.Adapter<MyAddrAdapter2.ViewHolder> {
    private List<MyAddrBean.ResultBean> list;
    private Context context;

    public MyAddrAdapter2(List<MyAddrBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.myaddritem2, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(list.get(i).getRealName());
        viewHolder.tel.setText(list.get(i).getPhone());
        viewHolder.addr.setText(list.get(i).getAddress());
        viewHolder.send_order_elect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onItemCheck(i,list.get(i).getRealName(),list.get(i).getPhone(),list.get(i).getAddress());
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
        private TextView send_order_elect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            tel = itemView.findViewById(R.id.tel);
            addr = itemView.findViewById(R.id.addr);
            send_order_elect= itemView.findViewById(R.id.send_order_elect);
        }
    }




    //接口回调
    public interface CallBack{
        void onItemCheck(int position, String name,String phone,String address);
    }
    private CallBack callBack;
    public void setOnItemClickLinstener(CallBack callBack){
        this.callBack = callBack;
    }
}
