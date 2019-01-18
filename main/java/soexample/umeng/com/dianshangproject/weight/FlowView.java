package soexample.umeng.com.dianshangproject.weight;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * author:author${朱佳华}
 * data:2019/1/9
 */
public class FlowView extends LinearLayout {

    private int mWidth;

    public FlowView(Context context,AttributeSet attrs) {
        super(context, attrs);
        //获取屏幕的宽高
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mWidth = metrics.widthPixels;
        setOrientation(VERTICAL);
    }

    public void removeChile(){
        removeAllViews();
    }


    public void setData(List<String> data){
        LinearLayout linearLayout = getLin();
        for (int i = 0; i < data.size(); i++) {
            String temp = data.get(i);
            int width=0;
            int childCount = linearLayout.getChildCount();
            for (int j = 0; j < childCount; j++) {
                TextView tv = (TextView) linearLayout.getChildAt(j);
                LayoutParams params = (LayoutParams) tv.getLayoutParams();
                int leftMargin = params.leftMargin; //设置一个左边距
                tv.measure(getMeasuredWidth(),getHeight());
                width+=tv.getMeasuredWidth()+leftMargin+tv.getPaddingLeft()+tv.getPaddingRight();
            }
            TextView tv = getText();
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            params.topMargin = 10;
            params.leftMargin = 10;
            tv.setLayoutParams(params);
            tv.setText(temp);
            tv.measure(getMeasuredWidth(),getMeasuredHeight());
            int tvWidth = tv.getMeasuredWidth()+tv.getPaddingLeft()+tv.getPaddingRight();
            if(mWidth>=width+tvWidth){
                linearLayout.addView(tv);
            }else{
                linearLayout = getLin();
                linearLayout.addView(tv);
            }
        }
    }

    private LinearLayout getLin(){
        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        this.addView(linearLayout);
        return linearLayout;
    }

    private TextView getText(){
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(16);
        textView.setPadding(10,10,10,10);
        return textView;
    }
}
