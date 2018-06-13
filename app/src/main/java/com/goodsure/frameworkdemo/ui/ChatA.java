package com.goodsure.frameworkdemo.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cly.greendaotest.gen.ChatItemDao;
import com.goodsure.frameworkdemo.BaseActivity;
import com.goodsure.frameworkdemo.MainActivity;
import com.goodsure.frameworkdemo.MyApplication;
import com.goodsure.frameworkdemo.R;
import com.goodsure.frameworkdemo.callback.Observerble;
import com.goodsure.frameworkdemo.common.ThreadCommonUtils;
import com.goodsure.frameworkdemo.common.greendao.DbManager;
import com.goodsure.frameworkdemo.common.wegit.utils.EaseSmileUtils;
import com.goodsure.frameworkdemo.em_message_presenter.ChartPresenter;
import com.goodsure.frameworkdemo.em_message_presenter.ChatPresenter;
import com.goodsure.frameworkdemo.model.ChatItem;
import com.goodsure.frameworkdemo.ui.view.IChartView;
import com.goodsure.frameworkdemo.utils.SPUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.goodsure.frameworkdemo.MyApplication.list;

public class ChatA extends BaseActivity<ChatPresenter,ChartPresenter>  implements IChartView {

    private RecyclerView rlv;
    private ChatAdapter chatAdapter;
    private String loginName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        rlv = (RecyclerView) findViewById(R.id.rlv);
        Intent intent = getIntent();
        loginName = intent.getStringExtra("loginName");
        SPUtils.putString(this,"loginName", loginName);
        DbManager.DB_NAME = loginName +".db";
        baseMessagePresenter.bindView(this);
        MyApplication.list.clear();

        update();

        Observerble.getInstence().registerSubscriberMessage(this);
        //添加消息监听器
        EMClient.getInstance().chatManager().addMessageListener(baseMessagePresenter.callBackMessage());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rlv.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatAdapter(R.layout.item_chat, MyApplication.list);
        chatAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        chatAdapter.setEnableLoadMore(false);
        rlv.setAdapter(chatAdapter);

        chatAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent=     new Intent(ChatA.this, MainActivity.class);
                intent.putExtra("name", list.get(position).getFromName());
                startActivity(intent);
            }
        });
    }

    private void update() {
        list.clear();
        List<ChatItem> chatItems = queryAll(this);
        for (int i = 0; i <chatItems.size() ; i++) {
            ChatItem chatItem = chatItems.get(i);
            updateList(chatItem, chatItem.getContent(), chatItem.getFromName(), chatItem.getToName(), chatItem.getDate(), false);

        }

        ThreadCommonUtils.runonuiThread(new Runnable() {
            @Override
            public void run() {
                if(chatAdapter !=null)
                    chatAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     *
     * 判断mainactivity是否处于栈顶
     * @return  true在栈顶false不在栈顶
     */
    private boolean isMainActivityTop(){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(ChatA.class.getName());
    }




    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<ChatItem> queryAll(Context context) {
        try {
              QueryBuilder<ChatItem> builder = DbManager.getDaoSession(context).getChatItemDao().queryBuilder().orderDesc(ChatItemDao.Properties.Date);
            //QueryBuilder<ChatItem> builder = DbManager.getDaoSession(context).getChatItemDao().queryBuilder();
            return builder.build().list();

        }catch (Exception e){

        }
        return  new ArrayList<>();
    }


    //废弃
    @Override
    public void messageProcessing(List<EMMessage> messages) {

    }

    @Override
    public void messageReceived(List<EMMessage> messages) {
        super.messageReceived(messages);
        update();
        Log.i("aaabb",Thread.currentThread().getName());
        ThreadCommonUtils.runonuiThread(new Runnable() {
            @Override
            public void run() {
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    private void updateList(ChatItem chatItem, String content, String fromName, String toName, long date, boolean isWhether) {
        if (!list.contains(chatItem) && !chatItem.getFromName().equals(loginName))
        list.add(chatItem);
        if(list.size()==0 && !chatItem.getFromName().equals(loginName)){
            list.add(chatItem);
        }
    }

    public class ChatAdapter extends BaseQuickAdapter<ChatItem, BaseViewHolder> {
        public ChatAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ChatItem item) {
            TextView view = helper.getView(R.id.tv_toName);
            TextView tv_content = helper.getView(R.id.tv_content);
            TextView tv_count =     helper.getView(R.id.tv_count);
            tv_count.setText(item.getCount()+"");
            view.setText(item.getFromName());
            tv_content.setText(EaseSmileUtils.getSmiledText(ChatA.this,item.getContent()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(baseMessagePresenter !=null)
        baseMessagePresenter.removeBackMessage();

    }

    @Override
    protected void onStart() {
        super.onStart();
        update();
        if(baseMessagePresenter !=null){
            if(baseMessagePresenter.getLinstener()==null){

                //添加消息监听器
                EMClient.getInstance().chatManager().addMessageListener(baseMessagePresenter.callBackMessage());
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //添加消息监听器
        if(baseMessagePresenter !=null)
            baseMessagePresenter.removeBackMessage();
    }
}
