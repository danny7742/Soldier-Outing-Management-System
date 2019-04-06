package com.example.minsookang.soms;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView stateimage;
    int state = 1;
    String startYear;
    String startMonth;
    String startDay;
    String endYear;
    String endMonth;
    String endDay;
    String[] datas;
    MaterialCalendarView materialCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        stateimage = (ImageView) findViewById(R.id.stateimage);
        Button reportbutton = (Button) findViewById(R.id.reportbutton);
        Button vcabutton = (Button) findViewById(R.id.vacbutton);


        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        Intent intent = getIntent();
        if(intent.getStringExtra("vacplan")!=null) {
            String vacplanresult = intent.getStringExtra("vacplan");

            datas = vacplanresult.split("_");
            startYear = datas[0];
            startMonth = datas[1];
            startDay = datas[2];
            endYear = datas[3];
            endMonth = datas[4];
            endDay = datas[5];
            Log.d(startYear, "mingtest");

            String[] exhibitdates = new String[2];
            exhibitdates[0] = startYear + ',' + startMonth + ',' + startDay;
            exhibitdates[1] = endYear + ',' + endMonth + ',' + endDay;

            Log.d("testming3", "ApiSimulator");
            new ApiSimulator(exhibitdates).executeOnExecutor(Executors.newSingleThreadExecutor());

        }

        switch (state) {  // 병사의 상태에 따라 레이아웃이 바뀜
            case 0: stateimage.setImageResource(R.drawable.yellowheart);
                reportbutton.setVisibility(View.GONE);
                break;
            case 1: stateimage.setImageResource(R.drawable.blueheart);
                break;
            case 2: stateimage.setImageResource(R.drawable.greenheart);
                break;
            case 3: stateimage.setImageResource(R.drawable.pinkheart);
                break;
        }


        reportbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){

            }
        });


        vcabutton.setOnClickListener(new View.OnClickListener(){ // 휴가등록 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),CalMngPopupActivity.class);
                startActivity(intent);
            }
        });











        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }
        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();
            Log.d("testming2", Time_Result[0]);
            Log.d("testming1", Time_Result[1]);
            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0 ; i < Time_Result.length ; i ++){
                CalendarDay day = CalendarDay.from(calendar);

                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
            }



            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays,MainActivity.this));
        }
    }



    public void vacbutton(View v){ // 휴가제한 등록 버튼을 누르면 실행
        Intent intent = new Intent(this, PopupActivity.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 팝업창에서 가지고오는 정보로 실행
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                String result = data.getStringExtra("result");
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_userMng) {
            Intent intent = new Intent(
                    getApplicationContext(),UsermngActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_vacationMng) {

        } else if (id == R.id.nav_solVacMng) {
            Intent intent3 = new Intent(
                    getApplicationContext(),SoldierVacationMngActivity.class);
            startActivity(intent3);
        } else if (id == R.id.nav_timeMng) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
