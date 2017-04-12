package com.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SecretActivity extends Activity implements OnClickListener {
    private Button reqNet;
    private InputStream input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);
        reqNet = (Button) findViewById(R.id.reqNet);
        reqNet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        input = this.getResources().openRawResource(R.raw.privatekey);
        switch (v.getId()){
            case R.id.reqNet:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //参数加签
                        String data = "myId:2";
                        String sign = null;
                        try {
                            String privateKey = RSA_Encrypt.getprivatekey(input);
                            sign = RSA_Encrypt.sign(data.getBytes(), privateKey);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //网络请求
                        List<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("myId", "2"));
                        params.add(new BasicNameValuePair("sign", sign));
                        JSONObject jb = NetUtil.getResponseForPost("http://192.168.1.8:9000/local/queryLocation", params);
                        System.out.println(jb.toString());
                    }
                }).start();
                break;
        }
    }
}
