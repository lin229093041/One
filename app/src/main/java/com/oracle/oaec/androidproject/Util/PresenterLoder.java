package com.oracle.oaec.androidproject.Util;

import android.content.Context;
import android.support.v4.content.Loader;

import com.oracle.oaec.androidproject.Base.Presenter;


/**
 * Created by linruoyu on 2016/10/20.
 */

public class PresenterLoder<P extends Presenter> extends Loader<P> {
    private final PresenterFactory<P> factory;
    private P presenter;
    public PresenterLoder(Context context, PresenterFactory<P> factory){
        super(context);
        this.factory=factory;
    }
    /**
     * 在activity的onStart()调用后执行
     */
    @Override
    protected void onStartLoading() {
        if(presenter!=null){
            deliverResult(presenter);//会将Presenter传递给Activity/Fragment。
            return;
        }
        forceLoad();
    }
    /**
     * 在调用forceLoad()方法后自动调用，我们在这个方法中创建Presenter并返回它
     */
    @Override
    protected void onForceLoad() {
        presenter=factory.create();//创建presenter
        deliverResult(presenter);
        super.onForceLoad();

    }

    /**
     * 会在Loader被销毁前调用，我们可在这里高知Presenter以终止某些操作或者进行清理工作
     */
    @Override
    protected void onReset() {
        presenter=null;
        super.onReset();
    }
}
