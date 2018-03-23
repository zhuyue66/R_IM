package com.newthread.rongyun;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

import static android.content.ContentValues.TAG;

/**
 * @author zhuyue66
 * @date 2018/3/20
 *
 * userID 001
 * username zhuda
 * token "qS5R/WXN9EnP2yyMZWVDsXJ+6UBLk8CzJNrQRU6rQifNO9Pu5wBmjOVoeTo3MmKmUihW3yLBhHLFh1yifYgt3w=="
 * portraitUri http://static.house.sina.com.cn/bizdatasydc/63/4d/biz_64580dd5b86275383936e1b0f2b50fd3_320X240.jpg
 *
 * userID 002
 * username zhuyue
 * token "LD2HSJwOT921XNWNXPlg+nJ+6UBLk8CzJNrQRU6rQifNO9Pu5wBmjCaavA83MaCv10xUKKtpkbMOFkUeMsgBag=="
 * portraitUri http://static.house.sina.com.cn/bizdatasydc/63/4d/biz_64580dd5b86275383936e1b0f2b50fd3_320X240.jpg
 */

public class App extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();


        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);

            /**
             * 设置会话列表界面操作的监听器。
             */
            RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());

            /**
             *
             * 设置接收消息的监听器
             * */
            RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener());

        }
    }
    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    private class MyConversationListBehaviorListener implements RongIM.ConversationListBehaviorListener {


        /**
         * 当点击会话头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param targetId         被点击的用户id。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */

        @Override
        public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
            Toast.makeText(context,"点击头像",Toast.LENGTH_LONG).show();
            return false;
        }

        /**
         * 当长按会话头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param targetId         被点击的用户id。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */

        @Override
        public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
            Toast.makeText(context,"长按头像",Toast.LENGTH_LONG).show();
            return false;
        }

        /**
         * 长按会话列表中的 item 时执行。
         *
         * @param context        上下文。
         * @param view           触发点击的 View。
         * @param uiConversation 长按时的会话条目。
         * @return 如果用户自己处理了长按会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
         */

        @Override
        public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
            Toast.makeText(context,"长按",Toast.LENGTH_LONG).show();
            return false;
        }

        /**
         * 点击会话列表中的 item 时执行。
         *
         * @param context        上下文。
         * @param view           触发点击的 View。
         * @param uiConversation 会话条目。
         * @return 如果用户自己处理了点击会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
         */

        @Override
        public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
            Toast.makeText(context,"点击",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {

        /**
         * 收到消息的处理。
         *
         * @param message 收到的消息实体。
         * @param left    剩余未拉取消息数目。
         * @return 收到消息是否处理完成，true 表示自己处理铃声和后台通知，false 走融云默认处理方式。
         */

        @Override
        public boolean onReceived(Message message, int i) {
            //Toast.makeText(context,"收到新消息\n"+ message + "还剩" + i +"条",Toast.LENGTH_LONG).show();
            Log.e(TAG, "onReceived: " + "收到新消息\n"+ message + "还剩" + i +"条");
            return false;
        }
    }
}
