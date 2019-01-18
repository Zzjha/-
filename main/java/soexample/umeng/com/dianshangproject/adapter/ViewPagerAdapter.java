package soexample.umeng.com.dianshangproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import soexample.umeng.com.dianshangproject.bean.GoodsDetailsBean;

/**
 * author:author${朱佳华}
 * data:2019/1/10
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<GoodsDetailsBean.ResultBean> list;
    private Context context;

    public ViewPagerAdapter(List<GoodsDetailsBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
       // iv.setImageResource(imgs[position%imgs.length]);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
