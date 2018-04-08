package com.newthread.rongyun;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.newthread.rongyun.utils.App;
import org.json.JSONObject;

import java.security.spec.ECField;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class RegisteredActivity extends AppCompatActivity {

    public EditText userID,name,portraitUri;
    public Button registered;
    public TextView response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {
        userID = findViewById(R.id.userID);
        name = findViewById(R.id.name);
        portraitUri = findViewById(R.id.portraitUri);
        registered = findViewById(R.id.registered);
        response = findViewById(R.id.response);

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetTokenTask getTokenTask = new GetTokenTask();
                getTokenTask.execute();
            }
        });
    }

    /**
     * 获取token的Task
     * */

    class GetTokenTask extends AsyncTask<Void,Void,String>{

        private String SUserID,SName,SPortraitUri;

        public GetTokenTask() {
            SUserID = userID.getText().toString();
            SName = name.getText().toString();
            SPortraitUri = portraitUri.getText().toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(App.getContext(), "开始获取Token", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... voids) {

            String responseData;

            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("userId",SUserID)
                        .add("name",SName)
                        .add("portraitUri",SPortraitUri)
                        .build();
                Request request = new Request.Builder()
                        .url("http://api.cn.ronghub.com/user/getToken.json")
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();

                responseData = response.body().string();

                return responseData;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: " + s);
            Toast.makeText(App.getContext(),s,Toast.LENGTH_LONG).show();

            try{
                JSONObject jsonObject = new JSONObject(s);
                response.setText(jsonObject.getString("errorMessage"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
