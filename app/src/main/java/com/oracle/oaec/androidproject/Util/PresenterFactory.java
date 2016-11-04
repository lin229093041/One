package com.oracle.oaec.androidproject.Util;


import com.oracle.oaec.androidproject.Base.Presenter;

/**
 * Created by linruoyu on 2016/10/20.
 */

public interface PresenterFactory<P extends Presenter> {
    P create();//创建presenter
}
