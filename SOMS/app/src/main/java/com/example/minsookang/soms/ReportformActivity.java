package com.example.minsookang.soms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReportformActivity extends AppCompatActivity {
    //지휘관이 설정하는 보고체계 설정 Activity
    //휴가, 외출, 외박으로 분류 예정
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportform);
    }
}
