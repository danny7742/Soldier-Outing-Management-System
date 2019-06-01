package com.example.minsookang.soms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class VacationRequestActivity extends AppCompatActivity {
//병사가 휴가승인 보내는 것
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacation_request);

        Button vcabutton = (Button) findViewById(R.id.vacabtn);
        vcabutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),VacareqVacationPopup.class);
                startActivity(intent);
            }
        });

        Button waebakbutton = (Button) findViewById(R.id.waebakbtn);
        waebakbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),VacareqWaebakPopup.class);
                startActivity(intent);
            }
        });

        Button waechulbutton = (Button) findViewById(R.id.waechulbtn);
        waechulbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),VacareqWaechulPopup.class);
                startActivity(intent);
            }
        });

        Button checkbutton = (Button) findViewById(R.id.checkbtn);
        checkbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),VacareqCheckPopup.class);
                startActivity(intent);
            }
        });

    }
}
