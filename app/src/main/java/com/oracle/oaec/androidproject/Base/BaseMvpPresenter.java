package com.oracle.oaec.androidproject.Base;

/**
 * Created by linruoyu on 2016/10/21.
 */

public class BaseMvpPresenter <V extends BaseMvpView> implements Presenter<V> {
    private V mvpView;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    /**
     * 判断view是否为空
     *
     * @return
     */
    public boolean isAttachView() {
        return mvpView != null;
    }

    /**
     * 返回目标view
     *
     * @return
     */
    public V getMvpView() {
        return mvpView;
    }

    /**
     * 检查view和presenter是否连接
     *
     * @throws Exception
     */
    public void checkViewAttach() {
        if (!isAttachView()) {
            throw new MvpViewNotAttachedException();
        }
    }

    /**
     * 自定义异常
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用attachView(MvpView)方法与view建立连接");
        }
    }
}