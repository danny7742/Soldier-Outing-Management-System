package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PopupActivity extends Activity {
    //각종 팝업 관련 Activity 구현 예정
    private int type;
    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        txtText = (TextView)findViewById(R.id.popupText);

        if(type==1){
            txtText.setText("승인 하시겠습니까?");
            Button button = (Button)findViewById(R.id.popup_button1);
            button.setText("승인하기");
            Button button2 = (Button)findViewById(R.id.popup_button2);
            button2.setText("닫기");
        }

        else if(type==2){
            txtText.setText("제거 하시겠습니까?");
            Button button = (Button)findViewById(R.id.popup_button1);
            button.setText("제거하기");
            Button button2 = (Button)findViewById(R.id.popup_button2);
            button2.setText("닫기");
        }
    }


}
