package com.goodsure.frameworkdemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Administrator on 2018/6/6.
 */
@Entity
public class ChatItem {
    private String fromName;
    private String toName;
    private String content;
    private String url;
    private long id;

    private long date;
    @Transient
    private int count;
    private boolean isWhether;   //是否已读
    private long msgId;
    public ChatItem(String fromName, String toName, String content, String url,long date) {
        this.fromName = fromName;
        this.toName = toName;
        this.content = content;
        this.url = url;
        this.date = date;
    }



    @Generated(hash = 1079613128)
    public ChatItem() {
    }


   @Keep
    @Generated(hash = 1728897366)
    public ChatItem(String fromName, String toName, String content, String url, long id, long date, boolean isWhether,
                    long msgId) {
        this.fromName = fromName;
        this.toName = toName;
        this.content = content;
        this.url = url;
        this.id = id;
        this.msgId = msgId;
        this.date = date;
        this.isWhether = isWhether;
    }



    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public boolean isWhether() {
        return isWhether;
    }

    public void setWhether(boolean whether) {
        isWhether = whether;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public boolean getIsWhether() {
        return this.isWhether;
    }



    public void setIsWhether(boolean isWhether) {
        this.isWhether = isWhether;
    }

    @Override
    public boolean equals(Object o) {

        ChatItem per = (ChatItem) o;
        if (per.getFromName().equals(this.getFromName())) {
            return true;
        }
        return false;
    }


}
