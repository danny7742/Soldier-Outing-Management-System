package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Rfvaca_popup extends Activity {

    String checkFirstorEnd = null;
    String numHour;
    String numMinute;
    String reportReason;
    EditText checkHour = null;
    EditText checkMinute = null;
    EditText reasonForm = null;
    CheckBox optionFirst = null;
    CheckBox optionEnd = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rfvaca_popup);

        optionFirst = (CheckBox)findViewById(R.id.check_firstday);
        optionEnd = (CheckBox)findViewById(R.id.check_endday);
        checkHour = (EditText)findViewById(R.id.rf_hour);
        checkMinute = (EditText)findViewById(R.id.rf_minute);
        reasonForm = (EditText)findViewById(R.id.starYear);



    }
    public void mOnPush(View vi){
        Intent intent = new Intent();
        if(checkHour.getText().toString().length() != 0)
        {
            if(checkMinute.getText().toString().length() != 0 )
            {

                if(optionEnd.isChecked() == true)
                    checkFirstorEnd = "복귀일";
                else if(optionFirst.isChecked() == true)
                    checkFirstorEnd = "출발일";
                intent.putExtra("checkFirstorEnd", checkFirstorEnd);
                intent.putExtra("numHour", checkHour.getText().toString());
                intent.putExtra("numMinute", checkMinute.getText().toString());
                intent.putExtra("reportReason", reasonForm.getText().toString());
                setResult(1234, intent);
                finish();
            }
            else
            {
                return;
            }
        }
        else
        {
            return;
            //시 입력 X
        }
    }


    public void mOnClose(View v){  //닫기 버튼 클릭시 작동
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
