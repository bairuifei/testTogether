package com.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.websocket.ChatWebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView content;
    private EditText msg;
    private ImageButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (TextView) findViewById(R.id.content);
        msg = (EditText) findViewById(R.id.msg);
        send = (ImageButton) findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==send.getId()){
            Toast.makeText(this,"send",Toast.LENGTH_SHORT).show();
            try {
                URI uri = new URI("ws://localhost:8080/chatWebSocket");
                ChatWebSocketClient socketClient = new ChatWebSocketClient(uri);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
