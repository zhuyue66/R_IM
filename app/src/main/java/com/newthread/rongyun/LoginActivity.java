package com.newthread.rongyun;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.newthread.rongyun.utils.App;
import com.newthread.rongyun.utils.MyReceiver;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * The type Login activity.
 */
public class LoginActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private MyReceiver myReceiver;

    final private String token1 = "qS5R/WXN9EnP2yyMZWVDsXJ+6UBLk8CzJNrQRU6rQifNO9Pu5wBmjOVoeTo3MmKmUihW3yLBhHLFh1yifYgt3w==";
    final private String token2 = "LD2HSJwOT921XNWNXPlg+nJ+6UBLk8CzJNrQRU6rQifNO9Pu5wBmjCaavA83MaCv10xUKKtpkbMOFkUeMsgBag==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.newthread.rong.push");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, intentFilter);

        /**
         * 连接融云服务器
         *
         * */
        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))){
            RongIM.connect(token1, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    Toast.makeText(LoginActivity.this,"token失效",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess(String s) {
                    Toast.makeText(LoginActivity.this,"连接成功",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Toast.makeText(LoginActivity.this,"连接失败",Toast.LENGTH_LONG).show();
                }
            });
        }

        /**
         * 调用API之后获取用户信息，设置用户信息
         * */
        RongIM.getInstance().setCurrentUserInfo(new UserInfo("002","zhuyue", Uri.parse(
                "http://static.house.sina.com.cn/bizdatasydc/63/4d/biz_64580dd5b86275383936e1b0f2b50fd3_320X240.jpg"
        )));

        /**
         * 刷新用户信息
         * */
        RongIM.getInstance().refreshUserInfoCache(new UserInfo("002","zhuyue", Uri.parse(
                "http://static.house.sina.com.cn/bizdatasydc/63/4d/biz_64580dd5b86275383936e1b0f2b50fd3_320X240.jpg")));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
