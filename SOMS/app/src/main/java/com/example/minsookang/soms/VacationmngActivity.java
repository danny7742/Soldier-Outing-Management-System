package com.example.minsookang.soms;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


public class VacationmngActivity extends FragmentActivity {
    //병사출타관리 Activity 구현
    //바텀바를 사용하여 "휴가", "외박", "외출"을 세가지 항목으로 나누어 구현
    private VacationFragment vacationFragment;
    private VacationFragment2 vacationFragment2;
    private WaebakFragment waebakFragment;
    private WaebakFragment2 waebakFragment2;
    private WaechulFragment waechulFragment;
    private WaechulFragment2 waechulFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacationmng);

        vacationFragment = new VacationFragment();
        vacationFragment2 = new VacationFragment2();
        waebakFragment = new WaebakFragment();
        waebakFragment2 = new WaebakFragment2();
        waechulFragment = new WaechulFragment();
        waechulFragment2 = new WaechulFragment2();



        initFragment();
        initFragment2();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener(){
            @Override
            public void onTabSelected(@IdRes int tabId){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                FragmentTransaction transaction5= getSupportFragmentManager().beginTransaction();
                FragmentTransaction transaction6 = getSupportFragmentManager().beginTransaction();

                if(tabId == R.id.vacation){
                    transaction.replace(R.id.contentContainer, vacationFragment).commit();
                    transaction2.replace(R.id.contentContainer2, vacationFragment2).commit();
                }
                else if(tabId == R.id.waebak){
                    transaction3.replace(R.id.contentContainer, waebakFragment).commit();
                    transaction4.replace(R.id.contentContainer2, waebakFragment2).commit();
                    }
                else if(tabId == R.id.waechul){
                    transaction5.replace(R.id.contentContainer, waechulFragment).commit();
                    transaction6.replace(R.id.contentContainer2, waechulFragment2).commit();
                }
            }

        });
    }


    // App 실행시 보여지는 Fragment 설정
    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer, vacationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void initFragment2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer2, vacationFragment2);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(
                getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
