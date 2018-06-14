package com.example.framework.net;

import android.content.Context;
import android.util.Log;

import com.example.framework.utils.ToastUtils;
import com.yanzhenjie.nohttp.rest.AsyncRequestExecutor;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2018/4/26.
 */

public  class RequestNetApi {

    private INet iNet;
    public RequestNetApi(INet iNet){
        this.iNet = iNet;
    }


    public void request(int what, Request<JSONObject> request, final String pageNumber, final boolean controlOff, final Context context){
        //   RequestQueue requestQueue = NoHttp.newRequestQueue();
        // 如果要指定并发值，传入数字即可：NoHttp.newRequestQueue(3);
        //Request<JSONObject> request = NoHttp.createJsonObjectRequest(url, method);
        // 发起请求

        AsyncRequestExecutor.INSTANCE.execute(what, request, new SimpleResponseListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                if(response==null){
                    ToastUtils.showToast(context, "服务器异常");
                    iNet.PdDismiss(what,pageNumber,controlOff,true);
                   // iNet.pdExceptionDismiss(what,pageNumber,controlOff);
                    return;
                }
                JSONObject json = response.get();
                Log.e("当前线程是：", Thread.currentThread().getName() + "ss");

                try {
                    String code = json.getString("code");
                    String message = json.getString("message");
                    if ("100".equals(code)) {
                        iNet.netVolleyResponese(what,json,pageNumber,controlOff);
                        iNet.PdDismiss(what,pageNumber,controlOff,false);
                        return;
                    } else if ("403".equals(code)) {

                     //   iNet.pdExceptionDismiss(what,pageNumber,controlOff);
                         //  SPUtils.putString(MyApplication.context,"login_key","0");
                        iNet.expire();
                    } else if("101".equals(code)){
                        iNet.errorMessages(what,message,pageNumber,controlOff);

                     //   iNet.pdExceptionDismiss(what,pageNumber,controlOff);
                    } else {

                       // iNet.pdExceptionDismiss(what,pageNumber,controlOff);
                        //  Toast.makeText(MyApplication.mContext, message, Toast.LENGTH_SHORT).show();
                        iNet.errorMessages(what,message,pageNumber,controlOff);
                    }

                } catch (JSONException e) {

                   // iNet.pdExceptionDismiss(what,pageNumber,controlOff);
                    e.printStackTrace();
                }

                iNet.PdDismiss(what,pageNumber,controlOff,true);
            }




            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                // 请求失败。
                iNet.volleyError(what,response,pageNumber,controlOff);
                iNet.PdDismiss(what,pageNumber,controlOff,true);
            }

            @Override
            public void onFinish(int what) {
                super.onFinish(what);
            }
        });
    }

    public void unINet(){
        iNet=null;
    }

}
