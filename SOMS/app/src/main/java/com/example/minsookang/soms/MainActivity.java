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
import android.widget.Toast;


import static com.example.minsookang.soms.LoginActivity.SN;
import static com.example.minsookang.soms.LoginActivity.TC;
import static com.example.minsookang.soms.LoginActivity.UC;
import static com.example.minsookang.soms.LoginActivity.UN;
import static com.example.minsookang.soms.LoginActivity.OS;
import static com.example.minsookang.soms.LoginActivity.regularVac;
import static com.example.minsookang.soms.LoginActivity.grantVac;
import static com.example.minsookang.soms.LoginActivity.rewardVac;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.google.firebase.firestore.FirebaseFirestore;
import static android.support.constraint.Constraints.TAG;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    public static String UserName;
    public static String outingArrive;
    public static String outingStart;
    public static String planArrive;
    public static String planStart;

    ImageView stateimage;

    String topic;
    String startYear;
    String startMonth;
    String startDay;
    String endYear;
    String endMonth;
    String endDay;
    ArrayList<Userinfo> userinfoList = new ArrayList<Userinfo>();
    String[] datas;
    String result;
    MaterialCalendarView materialCalendarView;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("Main SN", SN);
        DocumentReference docRef = db.collection("Soldier").document(SN);
        if(Integer.parseInt(UC) == 0) {
            docRef.collection("Vacation").document("OutingDate").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task3) {
                    DocumentSnapshot doc3 = task3.getResult();
                    outingArrive = doc3.getString("OutingArrive");
                    outingStart = doc3.getString("OutingStart");

                    Log.d("OutingStart", outingArrive);
                    Log.d("OutingArrive", outingStart);
                }
            });
            docRef.collection("Vacation").document("PlanDate").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task2) {
                    DocumentSnapshot doc2 = task2.getResult();

                    planArrive = doc2.getString("planArrive");
                    planStart = doc2.getString("planStart");
                    Log.d("planArrive", planArrive);
                    Log.d("planStart", planStart);
                }
            });
        }
        //Username = UC
        //SerialNum = SN
        //TroopCode = TC
        //Userclass = UC


//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "getInstanceId failed", task.getException());
//                            return;
//                        }
//
//                        // Get new Instance ID token
//                        String token = task.getResult().getToken();
//
//                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
//                        Log.d(TAG, msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });

//        if(outingstate==1)//병사가 휴가중일때
//        {
//            if()//오늘이 병사의 휴가 시작날짜일경우
//            {
//                topic="startvacation";
//            }else
//            {
//                topic = "null";
//            }
//        } else if(outstate==2)//병사가 외박중일때
//        {
//            if()//외박 첫날인 경우
//            {
//                topic="startbak";
//            }else if()//외박 마지막 날인경우
//            {
//                topic="endbak";
//            }
//        } else if(outstate==3)//병사가 외출중일때
//        {
//            topic="startchul";
//        }

        FirebaseMessaging.getInstance().subscribeToTopic("startvacation")/////토픽별로 구독. 푸시알림받음
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d("vacation message", msg);
                            }
                });

        stateimage = (ImageView) findViewById(R.id.stateimage);
        Button reportbutton = (Button) findViewById(R.id.reportbutton);
        Button vcabutton = (Button) findViewById(R.id.vacbutton);
        TextView classtext = (TextView)findViewById(R.id.text);
        if(Integer.parseInt(UC) == 0){
            reportbutton.setText("보고하기");
            vcabutton.setText("휴가계획하기");
            classtext.setText("병사모드");
        }

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        Intent intent = getIntent();
        intent.putExtra("vacplan", "1980_01_01_1980_02_02");

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

//        switch (Integer.parseInt(OS)) {  // 병사의 상태에 따라 레이아웃이 바뀜(현재는 병사의 상태를 나타내는 하트색이 바뀌고 출타중이 아닐시 보고하기버튼 X)
//            case 0: {
//                stateimage.setImageResource(R.drawable.yellowheart);//출타 X
//                reportbutton.setVisibility(View.GONE);
//                break;
//            }
//            case 1: {
//                stateimage.setImageResource(R.drawable.blueheart);
//                break;
//            }
//            case 2: {
//                stateimage.setImageResource(R.drawable.greenheart);
//                break;
//            }
//            case 3: {
//                stateimage.setImageResource(R.drawable.pinkheart);
//                break;
//            }
//        }


        reportbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),BriefActivity.class);
                startActivity(intent);
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);//////////사용자의 계급에 따라 네비게이션바 메뉴구성 변경
        if(Integer.parseInt(UC)==0)
        {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.soldier_menu);
        }

        navigationView.setNavigationItemSelectedListener(this);
        View nav_header_view = navigationView.getHeaderView(0);

        TextView nav_header_id_text = (TextView) nav_header_view.findViewById(R.id.userid);
        nav_header_id_text.setText(SN);
        TextView nav_header_id_text2 = (TextView) nav_header_view.findViewById(R.id.username);
        nav_header_id_text2.setText(UN);
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

            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays,MainActivity.this, UC));
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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { // 네비게이션바에서 눌렀을 때 각각의 항목 레이아웃으로 들어가는거
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_userMng) {
            Intent intent = new Intent(
                    getApplicationContext(),UsermngActivity.class);

            intent.putExtra("UserinfoList", userinfoList);
            startActivity(intent);
        } else if (id == R.id.nav_vacationMng) {
            Intent intent = new Intent(
                    getApplicationContext(),VacationmngActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_solVacMng) {
            Intent intent3 = new Intent(
                    getApplicationContext(),SoldierVacationMngActivity.class);
            intent3.putExtra("UserinfoList", userinfoList);
            startActivity(intent3);
        } else if (id == R.id.nav_timeMng) {
            Intent intent = new Intent(
                    getApplicationContext(),ReportformActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_vacationreq) {
            Intent intent = new Intent(
                    getApplicationContext(),VacationRequestActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_logout) {
////////////////여기에 로그아웃 코드!!




        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
