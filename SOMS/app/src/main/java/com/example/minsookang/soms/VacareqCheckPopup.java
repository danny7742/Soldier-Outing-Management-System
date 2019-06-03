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
import static com.example.minsookang.soms.VacareqVacationPopup.sOutingArrive;
import static com.example.minsookang.soms.VacareqVacationPopup.sOutingStart;
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
    int usedRegularVac = 0;
    int usedRewardVac = 0;
    int usedGrantVac = 0;
    String OutingStart;
    String OutingArrive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vacareq_check_popup);
        Intent intent = getIntent();
        String vacTerm = intent.getStringExtra("vacrequest");

        OutingStart = sOutingStart;
        OutingArrive = sOutingArrive;
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
        TextView contentTerm = (TextView)findViewById(R.id.contentTerm);
        contentTerm.setText(OutingStart + " ~ " + OutingArrive + "\n" + "정기휴가 : "+ Integer.toString(usedRegularVac) + "\n포상휴가 : "+ Integer.toString(usedRewardVac)
        + "\n위로휴가 : " + Integer.toString(usedGrantVac));
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
