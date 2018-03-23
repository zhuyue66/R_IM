package com.newthread.rongyun;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import java.util.Locale;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

public class ConversationActivity extends FragmentActivity {

    private String  targetId;
    private Conversation.ConversationType conversationType;//会话类型


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Intent intent = getIntent();
        targetId = intent.getData().getQueryParameter("targetId");
        conversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
        initView(intent);
    }

    private void initView(Intent intent) {
        setTitle(intent.getData().getQueryParameter("title"));

        //加载Fragment
        ConversationFragment conversationFragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(conversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", targetId)
                .build();
        conversationFragment.setUri(uri);
    }
}
