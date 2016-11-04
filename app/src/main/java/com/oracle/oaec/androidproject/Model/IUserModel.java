package com.oracle.oaec.androidproject.Model;

/**
 * Created by linruoyu on 2016/10/21.
 */

public interface IUserModel {
    void login(String username, String password, CallBack callBack);
}
