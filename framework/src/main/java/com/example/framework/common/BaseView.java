package com.example.framework.common;

import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/6/13.
 *  作为更新ui接口
 */
public interface BaseView {
    void  onSuccess(int what, JSONObject json, String pageNumber, boolean controlOff);
    void onError(int what, Response<JSONObject> response, String pageNumber, boolean controlOff);
}
