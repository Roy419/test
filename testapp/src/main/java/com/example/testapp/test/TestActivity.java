package com.example.testapp.test;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.example.framework.R;
import com.example.framework.ui.BaseActivity;
import com.example.framework.utils.ToastUtils;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

public class TestActivity extends BaseActivity<GetListMessagePresenter> implements GetListMessageView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        baseDataPresenter.bindView(this);
        baseDataPresenter.bindMode();

    }


    public void tv(View view){
        baseDataPresenter.getDataUrl(this);
    }
    @Override
    public void onSuccess(int what, JSONObject json, String pageNumber, boolean controlOff) {

    }

    @Override
    public void onError(int what, Response<JSONObject> response, String pageNumber, boolean controlOff) {
        ToastUtils.showLongToast(this,"错了"+ SystemClock.uptimeMillis());
    }
}
