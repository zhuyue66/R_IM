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

public class MainActivity extends AppCompatActivity {

    private Button message_list,p2p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message_list = findViewById(R.id.message_list);
        p2p = findViewById(R.id.p2p);

        message_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Boolean> map = new HashMap<>();

                // 会话列表需要显示私聊会话, 第二个参数 true 代表私聊会话需要聚合显示
                map.put(Conversation.ConversationType.PRIVATE.getName(), false);
                // 会话列表需要显示群组会话, 第二个参数 false 代表群组会话不需要聚合显示
                map.put(Conversation.ConversationType.GROUP.getName(), false);

                RongIM.getInstance().startConversationList(MainActivity.this, map);
            }
        });

        p2p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                RongIM.getInstance().startPrivateChat(MainActivity.this, "002", "zhuyue");
            }
        });
    }
}
