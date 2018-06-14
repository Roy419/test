package com.example.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.framework.common.ImBaseDataPreSenter;

/**
 * Created by Administrator on 2018/6/13.
 */

public   class BaseActivity<T extends ImBaseDataPreSenter> extends FragmentActivity {

    protected  T baseDataPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         baseDataPresenter = TUtil.getT(this,0);

    }
}
