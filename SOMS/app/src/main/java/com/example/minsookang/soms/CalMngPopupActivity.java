package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.example.minsookang.soms.LoginActivity.SN;
import static com.example.minsookang.soms.LoginActivity.UC;
import static com.example.minsookang.soms.LoginActivity.banendYear;
import static com.example.minsookang.soms.LoginActivity.banstartYear;
import static com.example.minsookang.soms.LoginActivity.banstartDate;
import static com.example.minsookang.soms.LoginActivity.banstartMonth;
import static com.example.minsookang.soms.LoginActivity.banendDate;
import static com.example.minsookang.soms.LoginActivity.banendMonth;
import static com.example.minsookang.soms.LoginActivity.banEnd;
import static com.example.minsookang.soms.LoginActivity.banStart;
import static com.example.minsookang.soms.MainActivity.outingArrive;
import static com.example.minsookang.soms.MainActivity.outingStart;
import static com.example.minsookang.soms.VacareqVacationPopup.usedsGrantVac;
import static com.example.minsookang.soms.VacareqVacationPopup.usedsRegularVac;
import static com.example.minsookang.soms.VacareqVacationPopup.usedsRewardVac;

public class CalMngPopupActivity extends Activity {
    //각종 팝업 관련 Activity 구현 예정

    TextView txtText;
    int testok = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calmngpopup);
        if(Integer.parseInt(UC) == 0){
            TextView VacPlan = findViewById(R.id.VacPlan);
            VacPlan.setText("휴가 계획하기");
        }
    }

    public void mOnPush(View v){ // 등록하기 버튼 눌렀을 경우
        //데이터 전달하기
        EditText editText1 = (EditText) findViewById(R.id.startYear) ;
        String strText1 = editText1.getText().toString() ; // 각 editText들을 int형으로 가져와서 저장(휴가시작날짜와 복귀날짜를 저장)
        int startYear = Integer.parseInt(strText1);
        EditText editText2 = (EditText) findViewById(R.id.startMonth) ;
        String strText2 = editText2.getText().toString() ;
        int startMonth = Integer.parseInt(strText2);
        EditText editText3 = (EditText) findViewById(R.id.startDate) ;
        String strText3 = editText3.getText().toString() ;
        int startDate = Integer.parseInt(strText3);
        EditText editText4 = (EditText) findViewById(R.id.endYear) ;
        String strText4 = editText4.getText().toString() ;
        int endYear = Integer.parseInt(strText4);
        EditText editText5 = (EditText) findViewById(R.id.endMonth) ;
        String strText5 = editText5.getText().toString() ;
        int endMonth = Integer.parseInt(strText5);
        EditText editText6 = (EditText) findViewById(R.id.endDate) ;
        String strText6 = editText6.getText().toString() ;
        int endDate = Integer.parseInt(strText6);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Commander").document("Ban");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task3) {
                DocumentSnapshot doc3 = task3.getResult();
                banStart = doc3.getString("BanStart");
                banEnd = doc3.getString("BanEnd");
                String[] startTime = banStart.split("_");
                String[] arriveTime = banEnd.split("_");
                banstartYear = startTime[0];
                Log.d("banstartYear", banstartYear);

                banstartMonth = startTime[1];
                banstartDate = startTime[2];
                banendYear = arriveTime[0];
                Log.d("banendYear", banendYear);
                banendMonth = arriveTime[1];
                banendDate = arriveTime[2];
            }
        });
        if(startYear>3000 || startYear<0 || endYear>3000 || endYear<0){ // editText에 년도, 월, 일을 잘못입력하였을때의 조건
            Toast.makeText(CalMngPopupActivity.this, "년도를 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }
        else if(startMonth>13 || startMonth<0 || endMonth>13 || endMonth<0){
            Toast.makeText(CalMngPopupActivity.this, "월을 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }
        else if(((startMonth==1 || startMonth==3 || startMonth==5 || startMonth==7 || startMonth==8 || startMonth==10 || startMonth==12) && startDate>31)
                || ((startMonth==4 || startMonth==6 || startMonth==9 || startMonth==11) && startDate>30)
                || startMonth==2 && startDate>29
                || startDate<0
                ||((endMonth==1 || endMonth==3 || endMonth==5 || endMonth==7 || endMonth==8 || endMonth==10 || endMonth==12) && endDate>31)
                || ((endMonth==4 || endMonth==6 || endMonth==9 || endMonth==11) && endDate>30)
                || endMonth==2 && endDate>29
                || endDate<0 ){
            Toast.makeText(CalMngPopupActivity.this, "일을 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }
        if(startDate < Integer.parseInt(banendDate)){
            Toast.makeText(CalMngPopupActivity.this, "휴가 제한 날짜와 겹칩니다.",Toast.LENGTH_SHORT).show();
            Log.d("banendYear", banendYear);
            testok = 0;
        }
        else
            testok = 1;

        if(Integer.parseInt(UC)== 2){
            Map<String, Object> banplan = new HashMap<>();
            banplan.put("BanStart", Integer.toString(startYear) + "_" + Integer.toString(startMonth) + "_" + Integer.toString(startDate));
            db.collection("Commander").document("Ban").set(banplan);
            banplan.put("BanEnd", Integer.toString(endYear) + "_" + Integer.toString(endMonth) + "_" + Integer.toString(endDate));
            db.collection("Commander").document("Ban").set(banplan);


        }


        if(testok == 1) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("vacplan", Integer.toString(startYear) + "_" + Integer.toString(startMonth) + "_" + Integer.toString(startDate)
                    + "_" + Integer.toString(endYear) + "_" + Integer.toString(endMonth) + "_" + Integer.toString(endDate));
            setResult(RESULT_OK, intent);
            startActivity(intent);
        }
        //액티비티(팝업) 닫기
        finish();
    }

    public void mOnClose(View v){ // 닫기 버튼 눌렀을 경우
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
