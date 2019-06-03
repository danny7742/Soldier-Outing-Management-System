package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Solvacamng_popup2 extends Activity {
        // 병사 휴가관리 창에서 병사를 눌렀을 때 나타나는 팝업창
    int routinVacMng;
    int prizeVacMng;
    int comfortVacMng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.solvacamng_popup2);

        Intent intent = getIntent();
        routinVacMng = intent.getIntExtra("RoutineVacation", 21);
        prizeVacMng = intent.getIntExtra("PrizeVacation", 0);
        comfortVacMng = intent.getIntExtra("ComfortVacation", 0);

        final TextView routinTextMng =(TextView)findViewById(R.id.routinVacationMng);
        final TextView prizeTextMng = (TextView)findViewById(R.id.prizeVacationMng);
        final TextView comfortTextMng = (TextView)findViewById(R.id.comfortVacationMng);

        routinTextMng.setText("정기휴가 : " + routinVacMng + "일");
        prizeTextMng.setText("포상휴가 : " + prizeVacMng + "일");
        comfortTextMng.setText("위로휴가 : " + comfortVacMng + "일");
        //디비에서 받아와서 해당 병사의 휴가상황을 보여줘야됨

        Button routinMinus = (Button)findViewById(R.id.routinMinusMng);
        routinMinus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                routinVacMng--;
                routinTextMng.setText("정기휴가 : " + routinVacMng + "일");
            }
        });

        Button routinPlus = (Button)findViewById(R.id.routinPlusMng);
        routinPlus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                routinVacMng++;
                routinTextMng.setText("정기휴가 : " + routinVacMng + "일");
            }
        });

        Button prizeMinus = (Button)findViewById(R.id.prizeMinusMng);
        prizeMinus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                prizeVacMng--;
                prizeTextMng.setText("포상휴가 : " + prizeVacMng + "일");
            }
        });

        Button prizePlus = (Button)findViewById(R.id.prizePlusMng);
        prizePlus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                prizeVacMng++;
                prizeTextMng.setText("포상휴가 : " + prizeVacMng + "일");
            }
        });

        Button comfortMinus = (Button)findViewById(R.id.comfortMinusMng);
        comfortMinus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                comfortVacMng--;
                comfortTextMng.setText("위로휴가 : " + comfortVacMng + "일");
            }
        });

        Button comfortPlus = (Button)findViewById(R.id.comfortPlusMng);
        comfortPlus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                comfortVacMng++;
                comfortTextMng.setText("위로휴가 : " + comfortVacMng + "일");
            }
        });

//        Button applyVacChange = (Button)findViewById(R.id.applyVacationChange);
//        applyVacChange.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                }
//        });

    }

    public void buttonaccept(View v){  // 설정버튼을 클릭시 작동
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        //DB에 휴가신청 각각 종류 저장
        Map<String, Object> vacreqmng = new HashMap<>();

        vacreqmng.put("Regular", Integer.toString(routinVacMng));
        vacreqmng.put("Reward", Integer.toString(prizeVacMng));
        vacreqmng.put("Grant", Integer.toString(comfortVacMng));
        db.collection("Soldier").document("1576089852").collection("Vacation").document("Remain").set(vacreqmng);

        Intent intent = new Intent();
        Solvacamng_popup.isChanged = 1;
        intent.putExtra("changedRoutine", routinVacMng);
        intent.putExtra("changedPrize", comfortVacMng);
        intent.putExtra("changedComfort", prizeVacMng);
        setResult(1234, intent);
        finish();
    }
    public void buttonClose(View v){  //닫기 버튼 클릭시 작동
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
