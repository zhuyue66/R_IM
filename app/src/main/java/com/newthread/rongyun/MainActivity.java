package com.newthread.rongyun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button message_list,p2p,registered,broadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        message_list = findViewById(R.id.message_list);
        p2p = findViewById(R.id.p2p);
        registered = findViewById(R.id.registered);
        broadCast = findViewById(R.id.broadCast);

        message_list.setOnClickListener(this);
        p2p.setOnClickListener(this);
        registered.setOnClickListener(this);
        broadCast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message_list:
                Map<String, Boolean> map = new HashMap<>();
                // 会话列表需要显示私聊会话, 第二个参数 true 代表私聊会话需要聚合显示
                map.put(Conversation.ConversationType.PRIVATE.getName(), false);
                // 会话列表需要显示群组会话, 第二个参数 false 代表群组会话不需要聚合显示
                map.put(Conversation.ConversationType.GROUP.getName(), false);
                // 会话列表需要显示系统会话, 第二个参数 false 代表群组会话不需要聚合显示
                map.put(Conversation.ConversationType.SYSTEM.getName(), false);
                RongIM.getInstance().startConversationList(MainActivity.this, map);
                break;
            case R.id.p2p:
                RongIM.getInstance().startPrivateChat(MainActivity.this, "002", "zhuyue");
                break;
            case R.id.registered:
                startActivity(new Intent(MainActivity.this,RegisteredActivity.class));
                break;
            case R.id.broadCast:
                Intent intent = new Intent("com.newthread.rong.push");
                sendBroadcast(intent);
                break;
            default:
                    break;
        }
    }

}
