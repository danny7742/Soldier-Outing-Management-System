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
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.w3c.dom.Text;

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

            Log.d("testming3", exhibitdates[0]);

            Log.d("testming4", exhibitdates[1]);
            new ApiSimulator(exhibitdates).executeOnExecutor(Executors.newSingleThreadExecutor());
        }

        switch (state) {  // 병사의 상태에 따라 레이아웃이 바뀜(현재는 병사의 상태를 나타내는 하트색이 바뀌고 출타중이 아닐시 보고하기버튼 X)
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
            public void onClick(View v){ //
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
            int[] exhibitYear= new int[46];
            int[] exhibitMonth = new int[46];
            int[] exhibitDay = new int[46];
            int[] exhibitYearRange= new int[2];
            int[] exhibitMonthRange = new int[2];
            int[] exhibitDayRange = new int[2];

            Log.d("testming2", Time_Result[0]);
            Log.d("testming1", Time_Result[1]);

            CalendarDay eventDay = CalendarDay.from(calendar);
            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0 ; i < Time_Result.length; i ++){

                String[] time = Time_Result[i].split(",");
                exhibitYearRange[i] = Integer.parseInt(time[0]);
                exhibitMonthRange[i] = Integer.parseInt(time[1]);
                exhibitDayRange[i] = Integer.parseInt(time[2]);
            }

            if(exhibitYearRange[0] == exhibitYearRange[1]){
                if(exhibitMonthRange[0] == exhibitMonthRange[1]){
                    if(exhibitDayRange[0] == exhibitDayRange[1]){ // 하루만 선택한 경우
                        eventDay = CalendarDay.from(exhibitYearRange[0], exhibitMonthRange[0], exhibitDayRange[0]);
                        dates.add(eventDay);
                        calendar.set(exhibitYearRange[0], exhibitMonthRange[0] - 1, exhibitDayRange[0]);
                    }
                    else{                                       //같은 달 내에 두 날짜를 선택한 경우
                        for(int i = exhibitDayRange[0]; i < exhibitDayRange[1] + 1; i++) {
                            exhibitYear[i] = exhibitYearRange[0];
                            exhibitMonth[i] = exhibitMonthRange[0];
                            exhibitDay[i] = i;

                            eventDay = CalendarDay.from(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                            dates.add(eventDay);
                            calendar.set(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                        }
                    }
                }
                else{                                           //달을 넘어간 경우
                    if(exhibitMonthRange[0] == 1 || exhibitMonthRange[0] == 3 || exhibitMonthRange[0] == 5 || exhibitMonthRange[0] == 7
                            || exhibitMonthRange[0] == 9 || exhibitMonthRange[0] == 11){
                        for(int i = exhibitDayRange[0]; i<32; i++){
                            exhibitYear[i] = exhibitYearRange[0];
                            exhibitMonth[i] = exhibitMonthRange[0];
                            exhibitDay[i] = i;

                            eventDay = CalendarDay.from(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                            dates.add(eventDay);
                            calendar.set(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                        }
                        for(int i = 1; i<exhibitDayRange[1] + 1; i++){
                            exhibitDay[i] = i;

                            eventDay = CalendarDay.from(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                            dates.add(eventDay);
                            calendar.set(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                        }
                    }
                    else if(exhibitMonthRange[0] == 4 || exhibitMonthRange[0] == 6 || exhibitMonthRange[0] == 8 || exhibitMonthRange[0] == 10
                            || exhibitMonthRange[0] == 12){
                        for(int i = exhibitDayRange[0]; i<31; i++){
                            exhibitYear[i] = exhibitYearRange[0];
                            exhibitMonth[i] = exhibitMonthRange[0];
                            exhibitDay[i] = i;

                            eventDay = CalendarDay.from(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                            dates.add(eventDay);
                            calendar.set(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                        }
                        for(int i = 1; i<exhibitDayRange[1] + 1; i++){
                            exhibitDay[i] = i;

                            eventDay = CalendarDay.from(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                            dates.add(eventDay);
                            calendar.set(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                        }
                    }
                }
            }
            else{
                for(int i = exhibitDayRange[0]; i<32; i++){
                    exhibitYear[i] = exhibitYearRange[0];
                    exhibitMonth[i] = exhibitMonthRange[0];
                    exhibitDay[i] = i;

                    eventDay = CalendarDay.from(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                    dates.add(eventDay);
                    calendar.set(exhibitYear[i], exhibitMonth[i] - 1, exhibitDay[i]);
                }
                for(int i = 1; i<exhibitDayRange[1] + 1; i++){
                    exhibitDay[i] = i;
                    exhibitYear[i] = exhibitYearRange[1];
                    eventDay = CalendarDay.from(exhibitYear[i], 0, exhibitDay[i]);
                    dates.add(eventDay);
                    calendar.set(exhibitYear[i] + 1, 0, exhibitDay[i]);
                }
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
    public boolean onNavigationItemSelected(MenuItem item) { // 네비게이션바에서 눌렀을 때 각각의 항목 레이아웃으로 들어가는거
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_userMng) {
            Intent intent = new Intent(
                    getApplicationContext(),UsermngActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_vacationMng) {
            Intent intent = new Intent(
                    getApplicationContext(),VacationmngActivity.class);
            startActivity(intent);
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
