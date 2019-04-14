package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;



public class Solvacamng_popup extends Activity {
    // 병사 휴가관리 창에서 병사를 눌렀을 때 나타나는 팝업창
    static int routinVac;
    static int prizeVac;
    static int comfortVac;
    static int isChanged = 0;
    TextView routinText;
    TextView prizeText;
    TextView comfortText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.solvacamng_popup);
        routinText =(TextView)findViewById(R.id.routinVacation);
        prizeText = (TextView)findViewById(R.id.prizeVacation);
        comfortText = (TextView)findViewById(R.id.comfortVacation);
        if(isChanged == 0){
            routinVac = 21;
            prizeVac = 0;
            comfortVac = 0;
            routinText.setText("정기휴가 : " + routinVac + "일");
            prizeText.setText("포상휴가 : " + prizeVac + "일");
            comfortText.setText("위로휴가 : " + comfortVac + "일");
        }
        //디비에서 받아와서 해당 병사의 휴가상황을 보여줘야됨

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode == 123){
            if(resultCode == 1234){
                routinVac = intent.getIntExtra("changedRoutine", 21);
                prizeVac = intent.getIntExtra("changedPrize", 0);
                comfortVac = intent.getIntExtra("changedComfort", 0);
                routinText.setText("정기휴가 : " + routinVac + "일");
                prizeText.setText("포상휴가 : " + prizeVac + "일");
                comfortText.setText("위로휴가 : " + comfortVac + "일");
            }
        }
    }


    public void buttonSetting(View v){  // 설정버튼을 클릭시 작동
        Intent intent = new Intent(this, Solvacamng_popup2.class);
        intent.putExtra("RoutineVacation", routinVac);
        intent.putExtra("PrizeVacation", prizeVac);
        intent.putExtra("ComfortVacation", comfortVac);
        startActivityForResult(intent, 123);

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
