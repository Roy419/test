package com.goodsure.frameworkdemo.em_message_presenter;

import com.goodsure.frameworkdemo.callback.Observerble;
import com.goodsure.frameworkdemo.common.greendao.DbManager;
import com.goodsure.frameworkdemo.model.ChatItem;
import com.goodsure.frameworkdemo.ui.view.IChartView;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

import static com.goodsure.frameworkdemo.MyApplication.context;

/**
 * Created by Administrator on 2018/6/1.
 */

public  class ChartPresenter extends MessagePresenter<IChartView> {

    public ChartPresenter(){

    }
      public void message(){
      }

    @Override
    public void messageReceived(List<EMMessage> messages) {

        for (EMMessage em :
                messages) {
            EMTextMessageBody emTextMessageBody = (EMTextMessageBody) em.getBody();
            ChatItem chatItem = new ChatItem(em.getFrom(), em.getTo(), emTextMessageBody.getMessage(), "", em.getMsgTime());
            chatItem.setIsWhether(false);

            DbManager.getDaoSession(context).getChatItemDao().insertOrReplaceInTx(chatItem);

        }
        //这里可以进行处理消息把需要的数据传出去
        Observerble.getInstence().sendMessageReceived(messages);
        ichartVeiw.messageProcessing(messages);
    }

    @Override
    public void cmdMessageReceived(List<EMMessage> messages) {
          Observerble.getInstence().sendCmdMessageReceived(messages);
    }

    @Override
    public void messageRead(List<EMMessage> messages) {
          Observerble.getInstence().sendMessageRead(messages);
    }

    @Override
    public void messageDelivered(List<EMMessage> message) {
      Observerble.getInstence().sendMessageDelivered(message);
    }

    @Override
    public void messageRecalled(List<EMMessage> messages) {
        Observerble.getInstence().sendMessageRecalled(messages);
    }

    @Override
    public void messageChanged(EMMessage message, Object change) {
        Observerble.getInstence().sendMessageChanged(message,change);
    }
}

