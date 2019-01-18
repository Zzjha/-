package soexample.umeng.com.dianshangproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.dianshangproject.R;
import soexample.umeng.com.dianshangproject.bean.ShopCarDataBean;
import soexample.umeng.com.dianshangproject.weight.JiaJianView;

/**
 * author:author${朱佳华}
 * data:2019/1/10
 */
public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private List<ShopCarDataBean.ResultBean> list;
    private Context context;

    public ShoppingAdapter(List<ShopCarDataBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shoppingitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getPic()).into(viewHolder.img);
        viewHolder.title.setText(list.get(i).getCommodityName());
        viewHolder.price.setText("￥"+list.get(i).getPrice()+"");
        //cardView设置圆角
        viewHolder.cardview.setRadius(5);
        viewHolder.cardview.setCardElevation(8);
        viewHolder.cardview.setContentPadding(5,5,5,5);

        // ckb
        viewHolder.ckb.setChecked(list.get(i).isWhetherChecked());
        viewHolder.ckb.setOnClickListener(new View.OnClickListener() {  //给checkbox设置点击事件
            @Override
            public void onClick(View v) {
                callBack.setChecked(v,i);
            }
        });

        // 数量 +  -
        viewHolder.jiaJianView.setOnChange(new JiaJianView.OnCountChange() {
            @Override
            public void setCount(int count) {
                callBack.setNum(i,count);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox ckb;
        private CardView cardview;
        private ImageView img;
        private TextView title;
        private TextView price;
        private Button del;
        private JiaJianView jiaJianView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ckb = itemView.findViewById(R.id.ckb);
            cardview = itemView.findViewById(R.id.cardview);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            del = itemView.findViewById(R.id.del);
            jiaJianView = itemView.findViewById(R.id.jiajianview);
        }
    }


    //被选中的id 去结算  传给  提交订单
    public String getCheckedId(){
        String id="";
        for (int i = 0; i < list.size(); i++) {
            boolean checked = list.get(i).isWhetherChecked();  //被选中
            if (checked){
                int commodityId = list.get(i).getCommodityId();
                id+=commodityId+" ";
            }
        }
        return id;
    }


    //给商品数量进行赋值
    public void setGoodsNumber(int position,int num){
        list.get(position).setCount(num);
    }

    //计算所有商品的价格
    public float getAllGoodsPrice(){
        float allPrice=0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isWhetherChecked()){
                allPrice=allPrice+list.get(i).getCount()*list.get(i).getPrice();
            }
        }
        return allPrice;
    }

    //判断遍历商品有没有没选中------------------------------------
    public boolean isItemGoodChecked(int position){
        boolean checked = list.get(position).isWhetherChecked();
        if (checked){
            return true;
        }
        return false;
    }
    //设置点击
    public void setItemGoodChecked(int position,boolean isChecked){//=============
        ShopCarDataBean.ResultBean resultBean = list.get(position);
        resultBean.setWhetherChecked(isChecked);
    }


    //全选反选  查看遍历
    public boolean isAllGoods(){
        boolean boo=true;
        for (int i = 0; i < list.size(); i++) {
            ShopCarDataBean.ResultBean resultBean = list.get(i);
            if (!resultBean.isWhetherChecked()){
                boo=false;
            }
        }
        return  boo;
    }
    //全选反选
    public void setAllGoodsIsChecked(boolean isChecked){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWhetherChecked(isChecked);
        }
    }



    //接口回调
    public interface CallBack{
        void setChecked(View view,int position); //checkbox点击事件的方法
        void setNum(int position,int num);  //num数量设置点击事件的方法
    }
    private CallBack callBack;
    public void setOnLinstener(CallBack callBack){
        this.callBack = callBack;
    }
}
