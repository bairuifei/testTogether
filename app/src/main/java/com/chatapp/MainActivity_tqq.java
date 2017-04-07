package com.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity_tqq extends Activity implements View.OnClickListener {

    private TextView content,receiver;
    private EditText msg;
    private ImageButton send;
    private static final String uri = "ws://182.92.2.234:9000/chatWebSocket/tqq/brf";
    private WebSocketClient socketClient;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Bundle bundle = msg.getData();
                    recevieContent(bundle.getString("msg"));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tqq);
        content = (TextView) findViewById(R.id.content);
        receiver = (TextView) findViewById(R.id.receiver);
        msg = (EditText) findViewById(R.id.msg);
        send = (ImageButton) findViewById(R.id.send);
        send.setOnClickListener(this);
        URI uri = null;
        try {
            uri = new URI(MainActivity_tqq.uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        socketClient = new ChatWebSocketClient(this,uri);
//        socketClient.connect();
        socketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }

            @Override
            public void onMessage(String s) {
                Message message = new Message();
                Bundle data = new Bundle();
                data.putString("msg",s);
                message.setData(data);
                message.what=1;
                handler.sendMessage(message);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
            }

            @Override
            public void onError(Exception e) {
            }
        };
        socketClient.connect();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==send.getId()){
            try {
                recevieContent("tqq："+msg.getText().toString());
                socketClient.send(msg.getText().toString());
                msg.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void recevieContent(String msg){
        String str = content.getText().toString();
        str += msg+"\n";
        content.setText(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socketClient.close();
    }
}
