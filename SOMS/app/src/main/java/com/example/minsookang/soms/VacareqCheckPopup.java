package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.minsookang.soms.LoginActivity.SN;
import static com.example.minsookang.soms.MainActivity.outingArrive;
import static com.example.minsookang.soms.MainActivity.outingStart;
import static com.example.minsookang.soms.VacareqVacationPopup.usedsGrantVac;
import static com.example.minsookang.soms.VacareqVacationPopup.usedsRegularVac;
import static com.example.minsookang.soms.VacareqVacationPopup.usedsRewardVac;

public class VacareqCheckPopup extends Activity {
        // 병사 출타 신청 화면에서 외박버튼 눌렀을 때
    int vacStartYear;
    int vacStartMonth;
    int vacStartDate;
    int vacEndYear;
    int vacEndMonth;
    int vacEndDate;
    int usedRegularVac;
    int usedRewardVac;
    int usedGrantVac;
    String enroll = "1";
    String OutingStart;
    String OutingArrive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vacareq_check_popup);
        Intent intent = getIntent();
        String vacTerm = intent.getStringExtra("vacrequest");
        TextView contentTerm = (TextView)findViewById(R.id.contentTerm);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Soldier").document(SN);
        docRef.collection("Vacation").document("OutingDate").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task3) {
                DocumentSnapshot doc3 = task3.getResult();
                outingArrive = doc3.getString("OutingArrive");
                outingStart = doc3.getString("OutingStart");
                usedsRegularVac = Integer.parseInt(doc3.get("Regular").toString());
                usedsRewardVac = Integer.parseInt(doc3.get("Reward").toString());
                usedsGrantVac = Integer.parseInt(doc3.get("Grant").toString());
            }
        });


        OutingStart = outingStart;
        OutingArrive = outingArrive;
        usedRegularVac = usedsRegularVac;
        usedRewardVac = usedsRewardVac;
        usedGrantVac = usedsGrantVac;

        String[] startTime = OutingStart.split("_");
        String[] arriveTime = OutingArrive.split("_");
        vacStartYear = Integer.parseInt(startTime[0]);
        vacStartMonth = Integer.parseInt(startTime[1]);
        vacStartDate = Integer.parseInt(startTime[2]);
        vacEndYear = Integer.parseInt(arriveTime[0]);
        vacEndMonth = Integer.parseInt(arriveTime[1]);
        vacEndDate = Integer.parseInt(arriveTime[2]);

        contentTerm.setText(OutingStart + " ~ " + OutingArrive + "\n" + "정기휴가 : "+ Integer.toString(usedsRegularVac) + "\n포상휴가 : "+ Integer.toString(usedsRewardVac)
        + "\n위로휴가 : " + Integer.toString(usedsGrantVac));
    }
    public void buttonLeft(View v){
        Intent intent = new Intent(getApplicationContext(), VacareqVacationPopup.class);
        intent.putExtra("startYear", Integer.toString(vacStartYear));
        intent.putExtra("startMonth", Integer.toString(vacStartMonth));
        intent.putExtra("startDate", Integer.toString(vacStartDate));
        intent.putExtra("endYear", Integer.toString(vacEndYear));
        intent.putExtra("endMonth", Integer.toString(vacEndMonth));
        intent.putExtra("endDate", Integer.toString(vacEndDate));

        startActivity(intent);
        finish();
    }

    public void buttonCenter(View v){

    }

    public void buttonRight(View v){  //닫기 버튼 클릭시 작동
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
