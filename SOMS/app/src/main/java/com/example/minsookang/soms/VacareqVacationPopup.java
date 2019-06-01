package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VacareqVacationPopup extends Activity {
    // 병사 출타 신청 화면에서 휴가버튼 눌렀을 때

    int routinVacMng;
    int prizeVacMng;
    int comfortVacMng;
    TextView routinTextMng;
    TextView prizeTextMng;
    TextView comfortTextMng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vacareq_vacation_popup);
        routinTextMng =(TextView)findViewById(R.id.routinVacationMng);
        prizeTextMng = (TextView)findViewById(R.id.prizeVacationMng);
        comfortTextMng = (TextView)findViewById(R.id.comfortVacationMng);
        //여기서 병사별 휴가 보유현황 DB에서 가져오기.
        routinVacMng = 21;
        prizeVacMng = 4;
        comfortVacMng = 3;

        routinTextMng.setText("정기휴가 : " + routinVacMng + "일");
        prizeTextMng.setText("포상휴가 : " + prizeVacMng + "일");
        comfortTextMng.setText("위로휴가 : " + comfortVacMng + "일");

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
//
//        Button applyVacationChange = (Button)findViewById(R.id.applyVacationChange);
//        applyVacationChange.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                //DB에 해당 휴가 날짜 적용
//            }
//        });

    }

    public void buttonaccept(View v){ // 등록하기 버튼 눌렀을 경우
        //데이터 전달하기
        EditText startYearText = (EditText) findViewById(R.id.startYear) ;
        String strstartYear = startYearText.getText().toString() ; // 각 editText들을 int형으로 가져와서 저장(휴가시작날짜와 복귀날짜를 저장)
        int startYear = Integer.parseInt(strstartYear);
        EditText startMonthText = (EditText) findViewById(R.id.startMonth) ;
        String strstartMonth = startMonthText.getText().toString() ;
        int startMonth = Integer.parseInt(strstartMonth);
        EditText startDateText = (EditText) findViewById(R.id.startDate) ;
        String strstartDate = startDateText.getText().toString() ;
        int startDate = Integer.parseInt(strstartDate);
        EditText endYearText = (EditText) findViewById(R.id.endYear) ;
        String strendYear = endYearText.getText().toString() ;
        int endYear = Integer.parseInt(strendYear);
        EditText endMonthText = (EditText) findViewById(R.id.endMonth) ;
        String strendMonth = endMonthText.getText().toString() ;
        int endMonth = Integer.parseInt(strendMonth);
        EditText endDateText = (EditText) findViewById(R.id.endDate) ;
        String strendDate = endDateText.getText().toString() ;
        int endDate = Integer.parseInt(strendDate);

        if(startYear>3000 || startYear<0 || endYear>3000 || endYear<0){ // editText에 년도, 월, 일을 잘못입력하였을때의 조건
            Toast.makeText(VacareqVacationPopup.this, "년도를 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }
        else if(startMonth>13 || startMonth<0 || endMonth>13 || endMonth<0){
            Toast.makeText(VacareqVacationPopup.this, "월을 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }
        else if(((startMonth==1 || startMonth==3 || startMonth==5 || startMonth==7 || startMonth==8 || startMonth==10 || startMonth==12) && startDate>31)
                || ((startMonth==4 || startMonth==6 || startMonth==9 || startMonth==11) && startDate>30)
                || startMonth==2 && startDate>29
                || startDate<0
                ||((endMonth==1 || endMonth==3 || endMonth==5 || endMonth==7 || endMonth==8 || endMonth==10 || endMonth==12) && endDate>31)
                || ((endMonth==4 || endMonth==6 || endMonth==9 || endMonth==11) && endDate>30)
                || endMonth==2 && endDate>29
                || endDate<0 ){
            Toast.makeText(VacareqVacationPopup.this, "일을 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(getApplicationContext(), VacareqCheckPopup.class);
        intent.putExtra("vacrequest", startYear +"_"+ startMonth +"_" + startDate +"_"+ endYear +"_"+ endMonth +"_"+ endDate);
        setResult(RESULT_OK, intent);
        startActivity(intent);
//        Log.d("VacReq", "VacReq");

        //액티비티(팝업) 닫기
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
