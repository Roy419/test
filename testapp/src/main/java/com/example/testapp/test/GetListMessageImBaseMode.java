package com.example.testapp.test;

import com.example.framework.common.ImBaseMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/6/13.
 */

public class GetListMessageImBaseMode extends ImBaseMode<GetListMessagePresenter>{


    @Override
    public void volleyError(int what, Response<JSONObject> response, String pageNumber, boolean controlOff) {
           baseImbaseDataPresenter.volleyError(what,response,pageNumber,controlOff);
    }

    @Override
    public void netVolleyResponese(int what, JSONObject json, String pageNumber, boolean controlOff) {
        baseImbaseDataPresenter.netVolleyResponese(what,json,pageNumber,controlOff);
    }

    @Override
    public void PdDismiss(int what, String pageNumber, boolean controlOff, boolean isError) {
        baseImbaseDataPresenter.PdDismiss(what,pageNumber,controlOff,isError);
    }

    @Override
    public void errorMessages(int what, String message, String pageNumber, boolean controlOff) {
        baseImbaseDataPresenter.errorMessages(what,message,pageNumber,controlOff);
    }

    @Override
    public void expire() {
        baseImbaseDataPresenter.expire();
    }


}
