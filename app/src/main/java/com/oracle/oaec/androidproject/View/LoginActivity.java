package com.oracle.oaec.androidproject.View;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oracle.oaec.androidproject.R;
import com.oracle.oaec.androidproject.RegisterActivity;
import com.oracle.oaec.androidproject.publicmethod.ToUrl;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends Activity {
    private EditText ed_number;
    private EditText ed_password;
    private TextView btn_login;
    private TextView btn_register;
    private CheckBox cb_password;
    private String userNumber;
    private String userPassword;

    private ImageView iv_return;

    private Intent intent;
    private boolean aBoolean = false;//判断是否已记录账号密码

    private SharedPreferences preferences;
    public static String Urlusername=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_number = (EditText) findViewById(R.id.ed_number);
        ed_password = (EditText) findViewById(R.id.ed_password);

        cb_password = (CheckBox) findViewById(R.id.checkBox);

        preferences = getSharedPreferences("Settings", 3);

        String username = preferences.getString("ed_number", "");
        String userpassword = preferences.getString("ed_password", "");

        if (username.equals("") && userpassword.equals("")) {
            aBoolean = false;
            cb_password.setChecked(false);
        } else {
            aBoolean = true;
            cb_password.setChecked(true);

            ed_number.setText(username);
            ed_password.setText(userpassword);
        }


        btn_login = (TextView) findViewById(R.id.btn_log);
        btn_register = (TextView) findViewById(R.id.btn_register);

        iv_return = (ImageView) findViewById(R.id.iv_return);//返回
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        if (preferences.getBoolean("USERPASSWORLD", false)) {
            cb_password.setChecked(true);//设置默认记录密码状态
            this.ed_number.setText(preferences.getString("ED_NUMBER", ""));
            this.ed_password.setText(preferences.getString("ED_PASSWORD", ""));

            //跳转页面

        }
//设置登录监听事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNumber = LoginActivity.this.ed_number.getText().toString();
                userPassword = LoginActivity.this.ed_password.getText().toString();
                if (userNumber.equals("") || userPassword.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空，请重新输入", Toast.LENGTH_LONG).show();
                    return;
                }
                FinalHttp http=new FinalHttp();
                StringBuffer sb=new StringBuffer(ToUrl.Url);
                /**
                 * mode为登陆请求
                 */
                sb.append("UserServlet?mode=1&name=");
                sb.append(ToString(userNumber));
                sb.append("&password=");
                sb.append(userPassword);
                http.get(sb.toString(), new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        if(o.equals("3")){//登陆成功
                            Urlusername=userNumber;
                            Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("Username", userNumber);
                            Log.d("FourFragment", userNumber);
                            setResult(RESULT_OK, intent);
                            finish();
                            if (cb_password.isChecked()) {//登陆成功和记住密码框为选中状态才能保存信息
                                //记住用户、密码
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("ed_number", userNumber);
                                editor.putString("ed_password", userPassword);
                                editor.commit();
                            }
                        }else if(o.equals("2")){//密码错误
                            Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                        }else if(o.equals("3")){
                            Toast.makeText(LoginActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
                        }
                        super.onSuccess(o);
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        Toast.makeText(LoginActivity.this,"访问数据库失败，请检查网络环境",Toast.LENGTH_SHORT).show();
                        super.onFailure(t, errorNo, strMsg);
                    }
                });





                    //跳转界面
//                    Intent intent = new Intent(LoginActivity.this,WelcomActivity.class);
//                    LoginActivity.this.startActivity(intent);

            }
        });
//设置记住密码多选框按钮事件
        cb_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb_password.isChecked()) {
                    preferences.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    preferences.edit().putBoolean("ISCHECK", false).commit();
                }
            }
        });
        //设置注册按钮监听
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_register:
                        //跳转页面
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        LoginActivity.this.startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private String ToString (String s){
        String str="";
        try {
            str=java.net.URLEncoder.encode(s,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
