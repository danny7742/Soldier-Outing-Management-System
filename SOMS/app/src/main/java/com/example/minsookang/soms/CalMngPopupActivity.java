package com.example.minsookang.soms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

public class CalMngPopupActivity extends AppCompatActivity {
    //각종 팝업 관련 Activity 구현 예정

    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calmngpopup);

        requestWindowFeature(Window.FEATURE_NO_TITLE);



        //UI 객체생성
        txtText = (TextView)findViewById(R.id.mngpopupText);

        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        txtText.setText(data);



    }
}
