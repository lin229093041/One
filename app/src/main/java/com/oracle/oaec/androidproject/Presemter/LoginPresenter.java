package com.oracle.oaec.androidproject.Presemter;

import com.oracle.oaec.androidproject.Base.BaseMvpPresenter;
import com.oracle.oaec.androidproject.Model.CallBack;
import com.oracle.oaec.androidproject.Model.UserModel;
import com.oracle.oaec.androidproject.View.LoginView;

/**
 * Created by linruoyu on 2016/10/21.
 */

public class LoginPresenter extends BaseMvpPresenter<LoginView> implements ILoginPresenter{
    private UserModel userModel;//Model层具体实现类
    public LoginPresenter(UserModel userModel) {
        this.userModel = userModel;
    }
    @Override
    public void login() {
        checkViewAttach();//检查是否绑定
        final LoginView loginView = getMvpView();//获得LoginView
        loginView.showLoding("正在登录中。。。");//loginViw的ui逻辑处理
        userModel.login(loginView.getUsername(), loginView.getPassword(), new CallBack() {
            @Override
            public void onSuccess() {
                loginView.hideLoding();
                loginView.showResult("登录成功");
            }

            @Override
            public void onFilure(String fail) {
                loginView.hideLoding();
                loginView.showErr(fail);
            }


        });
    }
}
