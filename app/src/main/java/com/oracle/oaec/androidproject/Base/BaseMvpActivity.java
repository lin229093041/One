package com.oracle.oaec.androidproject.Base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by linruoyu on 2016/10/21.
 */

public class BaseMvpActivity <P extends Presenter<V>,V extends BaseMvpView> extends AppCompatActivity implements BaseMvpView,  android.support.v4.app.LoaderManager.LoaderCallbacks<P> {
    private final int BASE_LODER_ID=1000;//loader的id值
    private ProgressDialog progressDialog;//登录进度条
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        progressDialog=new ProgressDialog(this);//实例化progressDialog
        getSupportLoaderManager().initLoader(BASE_LODER_ID,null,this);//初始化loader
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView((V)this);//presenter与view建立连接
    }

    @Override
    public void showLoding(String msg) {
        progressDialog.setMessage(msg);//设置进度条加载内容
        if(!progressDialog.isShowing()){//如果进度条没有显示
            progressDialog.show();
        }
    }

    @Override
    public void hideLoding() {
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showErr(String err) {
        Toast.makeText(this,err,Toast.LENGTH_SHORT).show();
    }

    @Override
    public android.support.v4.content.Loader<P> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<P> loader, P data) {
        presenter=data;
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<P> loader) {
        presenter=null;
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();

    }
}
