package com.example.testapp.test;

import android.content.Context;

import com.example.framework.common.ImBaseDataPreSenter;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/6/13.
 * 这个类处理网络请求数据
 */

public class GetListMessagePresenter extends ImBaseDataPreSenter<GetListMessageImBaseMode,GetListMessageView> {

    public GetListMessagePresenter(){

    }
    /**
     * 这个方法可以去获取某个接口数据
     */
    public void getDataUrl(Context context){
        Request<JSONObject> jsonObjectRequest = NoHttp.createJsonObjectRequest("http://www.baidu.com", RequestMethod.POST);
        jsonObjectRequest.setDefineRequestBodyForJson(new JSONObject());
        imBaseMode.requestURL(0,jsonObjectRequest,"0",true,context);
    }

    //处理结果
    @Override
    public void netVolleyResponese(int what, JSONObject json, String pageNumber, boolean controlOff) {
        super.netVolleyResponese(what, json, pageNumber, controlOff);
         baseView.onSuccess(what, json, pageNumber, controlOff);
    }

    @Override
    public void volleyError(int what, Response<JSONObject> response, String pageNumber, boolean controlOff) {
        super.volleyError(what, response, pageNumber, controlOff);
        baseView.onError(what, response, pageNumber, controlOff);
    }
}
