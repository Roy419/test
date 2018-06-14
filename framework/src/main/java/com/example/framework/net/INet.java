package com.example.framework.net;

import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by admin on 2018/4/26.
 */

public interface INet {
    /**
     * 请求出错后的处理
     */
   void  volleyError(int what, Response<JSONObject> response, String pageNumber, boolean controlOff);
    /**
     * 直接获取最后响应的结果
     * @param what  区分哪个网络请求
     * @param json
     */
    void netVolleyResponese(int what, JSONObject json, String pageNumber, boolean controlOff);
    /**
     * 关闭加载弹框
     */
    void PdDismiss(int what, String pageNumber, boolean controlOff, boolean isError);

/*    *//**
     * 网络异常关闭加载框
     *//*
     void pdExceptionDismiss(int what,String pageNumber, boolean controlOff);*/

     void errorMessages(int what, String message, String pageNumber, boolean controlOff)  ;

    void  expire();
}
