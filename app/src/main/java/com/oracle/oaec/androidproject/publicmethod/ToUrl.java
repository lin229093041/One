package com.oracle.oaec.androidproject.publicmethod;

import android.util.Log;
import android.widget.Toast;

import com.oracle.oaec.androidproject.RegisterActivity;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.util.Objects;

/**
 * Created by linruoyu on 2016/7/8.
 */

public class ToUrl {
    public final   static String Url="http://10.0.12.102:8093/OneAndroidDemo/";
    private static Object obj=null;



    public static Objects getUrl(String url){
        FinalHttp http=new FinalHttp();
        http.get(url.toString(), new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Log.i("1234","成功");
                obj=o;
                super.onSuccess(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("1234","失败");
            }
        });
        return (Objects) obj;
    }
}
