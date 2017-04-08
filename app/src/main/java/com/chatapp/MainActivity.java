package com.chatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button link_brf,link_tqq,show_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        link_brf = (Button) findViewById(R.id.link_brf);
        link_tqq = (Button) findViewById(R.id.link_tqq);
        show_pic = (Button) findViewById(R.id.show_pic);
        link_brf.setOnClickListener(this);
        link_tqq.setOnClickListener(this);
        show_pic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.link_brf:
                Intent intent = new Intent(this,MainActivity_tqq.class);
                startActivity(intent);
                break;
            case R.id.link_tqq:
                intent = new Intent(this,MainActivity_brf.class);
                startActivity(intent);
                break;
            case R.id.show_pic:
                intent = new Intent(this,NetImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
