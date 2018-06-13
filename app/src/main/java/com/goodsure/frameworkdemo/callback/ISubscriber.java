package com.goodsure.frameworkdemo.callback;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public abstract class ISubscriber implements Subscriber {
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
