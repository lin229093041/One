package com.oracle.oaec.androidproject.Base;

/**
 * Created by linruoyu on 2016/10/21.
 */

public interface BaseMvpView {
    /**
     * 显示进度条
     * @param msg
     */
    void showLoding(String msg);

    /**
     * 隐藏进度条
     */
    void hideLoding();

    /**
     * 显示加载错误
     * @param err 错误内容
     */
    void showErr(String err);
}
