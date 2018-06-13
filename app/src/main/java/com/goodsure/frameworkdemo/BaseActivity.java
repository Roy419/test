package com.goodsure.frameworkdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.goodsure.frameworkdemo.callback.Observerble;
import com.goodsure.frameworkdemo.callback.Subscriber;
import com.goodsure.frameworkdemo.common.CustomProgressDialog;
import com.goodsure.frameworkdemo.em_message_presenter.MessagePresenter;
import com.goodsure.frameworkdemo.ui.view.IChartView;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class BaseActivity<T extends BasePreSenter, Y extends MessagePresenter<IChartView>> extends AppCompatActivity implements Subscriber {
    protected  T basePreSenter;
    protected   Y baseMessagePresenter;
    protected CustomProgressDialog cpd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        basePreSenter = TUtil.getT(this,0);
        baseMessagePresenter = TUtil.getT(this,1);  //Y这个子类不要室abstract 不然 创建不了对象
        cpd = new CustomProgressDialog(this, "加载中...");
        cpd.setCancelable(false);//设置进度条不可以按退回键取消
        cpd.setCanceledOnTouchOutside(false);//设置点击进度对话框外的区域对话框不消失
        Observerble.getInstence().registerSubscriberMessage(this);
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

  /*  *
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

  /*  *
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token*/

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

}


    @Override
    public void messageReceived(List<EMMessage> messages) {

    }

    @Override
    public void cmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void messageRead(List<EMMessage> messages) {

    }

    @Override
    public void messageDelivered(List<EMMessage> message) {

    }

    @Override
    public void messageRecalled(List<EMMessage> messages) {

    }

    @Override
    public void messageChanged(EMMessage message, Object change) {

    }
}
