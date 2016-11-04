package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText editText_number;
    private EditText editText_password;
    private EditText editText_again;
    private TextView btn_again;

    SQLiteDatabase mysql;

    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_number = (EditText) findViewById(R.id.editText_number);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_again = (EditText) findViewById(R.id.editText_again);
        btn_again = (TextView) findViewById(R.id.btn_again);

        btn_again.setOnClickListener(this);

        //mysql = openOrCreateDatabase("", MODE_PRIVATE, null);

        iv_return = (ImageView) findViewById(R.id.iv_return);//返回
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String i = editText_number.getText().toString();
        switch (v.getId()) {

            case R.id.btn_again:

                if(!editText_password.getText().toString().equals(editText_again.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                    editText_password.setText("");
                    editText_again.setText("");
                    return;
                }
                String url2="http://10.0.12.101:8093/OneAndroidDemo/UserServlet?mode=2&name=";
                StringBuffer url=new StringBuffer(url2);
                url.append(editText_number.getText().toString());
                url.append("&password=");
                url.append(editText_password.getText().toString());
                // String strurl="http://10.0.12.102:8093/OneAndroidDemo/UserServlet?mode=2&name=123&password=123";
                FinalHttp http=new FinalHttp();
                http.get(url.toString(), new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        int index=Integer.valueOf(o.toString());
                        if(index==4){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(index==5) {
                            Toast.makeText(RegisterActivity.this,"账号已存在",Toast.LENGTH_SHORT).show();
                        }
                        super.onSuccess(o);
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        Toast.makeText(RegisterActivity.this,"失败",Toast.LENGTH_SHORT).show();
                        super.onFailure(t, errorNo, strMsg);
                    }
                });
            default:
                break;
        }
    }
}

