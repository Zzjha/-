package soexample.umeng.com.dianshangproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.dianshangproject.bean.MyInfoBean;
import soexample.umeng.com.dianshangproject.bean.UpdataIconBean;
import soexample.umeng.com.dianshangproject.bean.UpdateNickAndPwdBean;
import soexample.umeng.com.dianshangproject.presenter.MyPresenter;
import soexample.umeng.com.dianshangproject.utils.Contacts;
import soexample.umeng.com.dianshangproject.view.IView;

public class MyInfoActivity extends AppCompatActivity implements IView {

    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.nick)
    TextView nick;
    @BindView(R.id.pwd)
    TextView pwd;

    private SharedPreferences sp;
    private MyPresenter myPresenter;
    private Map<String, Object> headermap;
    private Map<String, String> map;

    private PopupWindow pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        sp = getSharedPreferences("project", MODE_PRIVATE);

        myPresenter = new MyPresenter(this);
        headermap = new HashMap<>();
        String sessionId = sp.getString("sessionId", "");
        String userId = sp.getString("userId", "");
        headermap.put("sessionId", sessionId);
        headermap.put("userId", userId);

        map = new HashMap<>();
        map.put("page", 1 + "");
        map.put("count", 1 + "");
        myPresenter.getHeaderRequest(Contacts.GETURSEBYID, 3, headermap, map);


        //拍照、相册
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(MyInfoActivity.this, R.layout.pop, null);
                pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                pop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                TextView paizhao = (TextView) view.findViewById(R.id.paizhao);
                TextView xiangce = (TextView) view.findViewById(R.id.xiangce);
                TextView cancel = (TextView) view.findViewById(R.id.cancel);
                //拍照
                paizhao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.addCategory("android.intent.category.DEFAULT");
                        startActivityForResult(intent, 1000);
                    }
                });
                //相册
                xiangce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 2000);
                    }
                });
                //取消
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                //上传头像
                Map<String,String> mMap = new HashMap<>();
              //  mMap.put("image",);
                myPresenter.postHeaderRequest(Contacts.HEADPIC,4,headermap,mMap);
            }
        });


        //修改昵称
        nick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.dialog, null);
                final EditText editText = (EditText) view.findViewById(R.id.update);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
                builder.setTitle("请输入新的昵称");//设置对话框的标题
                builder.setView(view);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editText.getText().toString().trim();
                        if(content.isEmpty()){
                            Toast.makeText(MyInfoActivity.this, "请输入修改过后昵称", Toast.LENGTH_SHORT).show();
                        }else{
                            nick.setText(content);
                            Map<String,String> map2 = new HashMap<>();
                            map2.put("nickName",content+"");
                            myPresenter.putHeaderRequest(Contacts.UPDATENICK,1,headermap,map2);

                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


        //修改密码
        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.dialog, null);
                final EditText editText = (EditText) view.findViewById(R.id.update);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
                builder.setTitle("请输入新的密码");//设置对话框的标题
                builder.setView(view);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editText.getText().toString().trim();
                        if(content.isEmpty()){
                            Toast.makeText(MyInfoActivity.this, "请输入修改过后密码", Toast.LENGTH_SHORT).show();
                        }else{
                            Map<String,String> map3 = new HashMap<>();
                            map3.put("oldPwd",pwd.getText().toString().trim()+"");
                            pwd.setText(content);
                            map3.put("newPwd",content+"");
                            myPresenter.putHeaderRequest(Contacts.UPDATEPWD,2,headermap,map3);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    //IView
    @Override
    public void success(Object success) {
        if(success instanceof MyInfoBean){
            MyInfoBean bean = (MyInfoBean) success;
            String headPic = bean.getResult().getHeadPic();
            Glide.with(this).load(headPic).into(icon);
            nick.setText(bean.getResult().getNickName());
            pwd.setText(bean.getResult().getPassword());
        }else if(success instanceof UpdateNickAndPwdBean){
            UpdateNickAndPwdBean updateNickAndPwdBean = (UpdateNickAndPwdBean) success;
            if(updateNickAndPwdBean.getStatus().equals("0000")){
                Toast.makeText(this, updateNickAndPwdBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(success instanceof UpdataIconBean){
            UpdataIconBean updataIconBean = (UpdataIconBean) success;
            if(updataIconBean.getStatus().equals("0000")){
                Toast.makeText(this, updataIconBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void error(Object error) {
        UpdateNickAndPwdBean updateNickAndPwdBean = (UpdateNickAndPwdBean) error;
        if(updateNickAndPwdBean.getStatus().equals("0000")){
            Toast.makeText(this, updateNickAndPwdBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Bitmap bitmap = data.getParcelableExtra("data");
            icon.setImageBitmap(bitmap);
        }
        if (requestCode == 2000) {
            Uri uri = data.getData();
            icon.setImageURI(uri);
            pop.dismiss();
        }
        if (requestCode == 3000) {
            Bitmap bitmap = data.getParcelableExtra("data");
            icon.setImageBitmap(bitmap);
        }
    }

    //裁剪
    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");

        //裁减比例1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        //裁剪后图片大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDatection", false);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 3000);
    }
}