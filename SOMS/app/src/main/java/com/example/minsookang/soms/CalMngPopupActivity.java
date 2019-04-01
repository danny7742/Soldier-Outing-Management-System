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
        EditText editText1 = (EditText) findViewById(R.id.editText1) ;
        String strText1 = editText1.getText().toString() ; // 각 editText들을 int형으로 가져와서 저장
        int strInt1 = Integer.parseInt(strText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2) ;
        String strText2 = editText2.getText().toString() ;
        int strInt2 = Integer.parseInt(strText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3) ;
        String strText3 = editText3.getText().toString() ;
        int strInt3 = Integer.parseInt(strText3);
        EditText editText4 = (EditText) findViewById(R.id.editText4) ;
        String strText4 = editText4.getText().toString() ;
        int strInt4 = Integer.parseInt(strText4);
        EditText editText5 = (EditText) findViewById(R.id.editText5) ;
        String strText5 = editText5.getText().toString() ;
        int strInt5 = Integer.parseInt(strText5);
        EditText editText6 = (EditText) findViewById(R.id.editText6) ;
        String strText6 = editText6.getText().toString() ;
        int strInt6 = Integer.parseInt(strText6);
        if(strInt1>3000 || strInt1<0 || strInt4>3000 || strInt4<0){ // editText에 년도, 월, 일을 잘못입력하였을때의 조건
            Toast.makeText(CalMngPopupActivity.this, "년도를 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }
        else if(strInt2>13 || strInt2<0 || strInt5>13 || strInt5<0){
            Toast.makeText(CalMngPopupActivity.this, "월을 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }
        else if(((strInt2==1 || strInt2==3 || strInt2==5 || strInt2==7 || strInt2==8 || strInt2==10 || strInt2==12) && strInt3>31)
                || ((strInt2==4 || strInt2==6 || strInt2==9 || strInt2==11) && strInt3>30)
                || strInt2==2 && strInt3>29
                || strInt3<0
                ||((strInt5==1 || strInt5==3 || strInt5==5 || strInt5==7 || strInt5==8 || strInt5==10 || strInt5==12) && strInt6>31)
                || ((strInt5==4 || strInt5==6 || strInt5==9 || strInt5==11) && strInt6>30)
                || strInt5==2 && strInt6>29
                || strInt6<0 ){
            Toast.makeText(CalMngPopupActivity.this, "일을 잘못 입력하였습니다.",Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("vacplan", strInt1 +"_"+ strInt2 +"_" + strInt3 +"_"+ strInt4 +"_"+ strInt5 +"_"+ strInt6);
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
