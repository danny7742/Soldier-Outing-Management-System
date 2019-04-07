package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalMngPopupActivity extends Activity {
    //각종 팝업 관련 Activity 구현 예정

    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calmngpopup);
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


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("vacplan", startYear +"_"+ startMonth +"_" + startDate +"_"+ endYear +"_"+ endMonth +"_"+ endDate);
        setResult(RESULT_OK, intent);
        startActivity(intent);
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
