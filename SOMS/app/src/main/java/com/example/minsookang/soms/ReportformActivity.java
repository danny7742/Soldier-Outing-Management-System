package com.example.minsookang.soms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReportformActivity extends AppCompatActivity {
    //지휘관이 설정하는 보고체계 설정 Activity
    //휴가, 외출, 외박으로 분류 예정
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportform);

        Button vcabutton = (Button) findViewById(R.id.vacabtn);
        vcabutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),RfVacationActivity.class);
                startActivity(intent);
            }
        });

        Button waebakbutton = (Button) findViewById(R.id.waebakbtn);
        waebakbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),RfwaebakActivity.class);
                startActivity(intent);
            }
        });

        Button waechulbutton = (Button) findViewById(R.id.waechulbtn);
        waechulbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),RfwaechulActivity.class);
                startActivity(intent);
            }
        });


    }
}
