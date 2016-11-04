package com.oracle.oaec.androidproject.View;

import com.oracle.oaec.androidproject.Base.BaseMvpView;

/**
 * Created by linruoyu on 2016/10/21.
 */

public interface LoginView extends BaseMvpView {
    /**
     * 显示登陆的结果
     * @param result
     */
    void showResult(String result);

    /**
     * 获取界面上用户名的值
     * @return
     */
    String getUsername();
    /**
     * 获取界面上密码的值
     */
    String getPassword();
}
