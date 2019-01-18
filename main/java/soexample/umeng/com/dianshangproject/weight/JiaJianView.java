package soexample.umeng.com.dianshangproject.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import soexample.umeng.com.dianshangproject.R;

/**
 * author:author${朱佳华}
 * data:2019/1/11
 */
public class JiaJianView extends LinearLayout implements View.OnClickListener {
    private TextView jian;
    private TextView num;
    private TextView jia;
    private int count;

    public JiaJianView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.jiajianview, this);
        initView();
    }

    private void initView() {
        jian = findViewById(R.id.jian);
        num = findViewById(R.id.num);
        jia = findViewById(R.id.jia);

        jian.setOnClickListener(this);
        jia.setOnClickListener(this);
    }

    //先给初始值赋值
    public void setCount(int count) {
        this.count = count;
        num.setText(count+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jian:
                if(count>1){
                    count--;
                    num.setText(count+"");
                    if(onCountChange!=null){
                        onCountChange.setCount(count);
                    }
                }else{
                    Toast.makeText(getContext(), "该宝贝不能减少了~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.jia:
                count++;
                num.setText(count+"");
                if(onCountChange!=null){
                    onCountChange.setCount(count);
                }
                break;
        }
    }



    //接口回调
    public interface OnCountChange{
        void setCount(int count);
    }
    private OnCountChange onCountChange;
    public void setOnChange(OnCountChange onCountChange){
        this.onCountChange = onCountChange;
    }
}
