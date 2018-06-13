package com.goodsure.frameworkdemo.callback;

import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public class Observerble {
    public static List<Subscriber> list = new ArrayList<>();
      public static Observerble observerble;
     private Observerble(){}
     public static Observerble getInstence(){
         if(null == observerble){
             synchronized (Observerble.class){
                 if(null == observerble){
                     observerble = new Observerble();
                 }
             }
         }
         return observerble;
     }

    public void registerSubscriberMessage(Subscriber sb){
         if(!list.contains(sb))
         list.add(sb);
    }

     public void removeSubscriberMessage(Subscriber sb){
          list.remove(sb);
     }



    private void message(List<EMMessage> messages) {
        for (int i = 0; i < list.size(); i++) {
            Subscriber subscriber = list.get(i);
              subscriber.messageReceived(messages);

        }
    }


    public void  sendMessageReceived(List<EMMessage> messages) {//发送消息}
        message(messages);
     }
   public void  sendCmdMessageReceived(List<EMMessage> messages){//发送透传消息
       message(messages);
    }

  public void  sendMessageRead(List<EMMessage> messages) { //发送已读回执
      message(messages);
  }
  public void  sendMessageDelivered(List<EMMessage> messages)  { //发送已送达回执
      message(messages);
  }
  public void  sendMessageRecalled(List<EMMessage> messages) {   //发送消息被撤回
      message(messages);
   }
  public void  sendMessageChanged(EMMessage message, Object change) {//发送消息状态变动
      for (int i = 0; i < list.size(); i++) {
          Subscriber subscriber = list.get(i);
          subscriber.messageChanged(message,change);
      }
  }



}
