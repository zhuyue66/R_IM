package com.newthread.rongyun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginActivity extends AppCompatActivity {

    final private String token1 = "qS5R/WXN9EnP2yyMZWVDsXJ+6UBLk8CzJNrQRU6rQifNO9Pu5wBmjOVoeTo3MmKmUihW3yLBhHLFh1yifYgt3w==";
    final private String token2 = "LD2HSJwOT921XNWNXPlg+nJ+6UBLk8CzJNrQRU6rQifNO9Pu5wBmjCaavA83MaCv10xUKKtpkbMOFkUeMsgBag==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))){
            RongIM.connect(token2, new RongIMClient.ConnectCallback() {
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
    }
}
