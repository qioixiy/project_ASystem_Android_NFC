package com.qioixiy.mvpdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements BaseView {
    public abstract int getContentViewId();
    protected abstract void initAllMembersView(Bundle savedInstanceState);
    protected Context mContext;
    protected View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        this.mContext = getActivity();
        initAllMembersView(savedInstanceState);
        return mRootView;
    }
    @Override
    public void showLoading() {
        checkActivityAttached();
        ((BaseFragmentActivity) mContext).showLoading();
    }
    @Override
    public void hideLoading() {
        checkActivityAttached();
        ((BaseFragmentActivity) mContext).hideLoading();
    }
    @Override
    public void showToast(String msg) {
        checkActivityAttached();
        ((BaseFragmentActivity) mContext).showToast(msg);
    }
    @Override
    public void showErr() {
        checkActivityAttached();
        ((BaseFragmentActivity) mContext).showErr();
    }
    protected boolean isAttachedContext(){
        return getActivity() != null;
    }
    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }
    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }
}