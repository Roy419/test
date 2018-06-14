package com.example.framework.common;

import com.example.framework.ui.TUtil;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/6/13.
 */

public  class ImBaseDataPreSenter <T extends ImBaseMode,Y extends BaseView> implements BaseDataPreSenter {
     protected  T imBaseMode;
     protected  Y baseView;

     public void bindView(Y y){
          baseView = y;
     }
    public void bindMode(){
            imBaseMode = TUtil.getT(this,0);
            imBaseMode.bind(this);
    }


    public void unBindViewAndMode(){
        baseView = null;
        imBaseMode = null;
    }

    @Override
    public void volleyError(int what, Response<JSONObject> response, String pageNumber, boolean controlOff) {

    }

    @Override
    public void netVolleyResponese(int what, JSONObject json, String pageNumber, boolean controlOff) {

    }

    @Override
    public void PdDismiss(int what, String pageNumber, boolean controlOff, boolean isError) {

    }

    @Override
    public void errorMessages(int what, String message, String pageNumber, boolean controlOff) {

    }

    @Override
    public void expire() {

    }


}
