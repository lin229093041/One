package com.oracle.oaec.androidproject.Base;

/**
 * Created by linruoyu on 2016/10/21.
 */

public interface Presenter <V extends BaseMvpView>{
    /**
     * presenter和对应的view绑定
     * @param mvpView 目标view
     */
    void attachView(V mvpView);

    /**
     * presenter与view解绑
     */
    void detachView();
}
