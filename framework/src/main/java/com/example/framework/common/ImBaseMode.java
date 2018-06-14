package com.example.framework.common;

import android.content.Context;

import com.example.framework.net.RequestNetApi;
import com.yanzhenjie.nohttp.rest.Request;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/6/13.
 */

public abstract class ImBaseMode<T extends ImBaseDataPreSenter> implements BaseMode {

     protected T baseImbaseDataPresenter;
    private RequestNetApi requestNetApi;

    public ImBaseMode(){
        if(null == requestNetApi)
            requestNetApi = new RequestNetApi(this);
    }

    public void bind(T t){
         baseImbaseDataPresenter =t;
    }

     public void requestURL (int what, Request<JSONObject> request, final String pageNumber, final boolean controlOff, final Context context){
         requestNetApi.request(what,request,pageNumber,controlOff,context);
     }


     public void unBind(){
         baseImbaseDataPresenter = null;
     }

}
