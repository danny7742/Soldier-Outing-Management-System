package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Rfvaca_popup2 extends Activity {

    Button Editbutton = null;
    String getFirstorEnd = null;
    String getnumHour = null;
    String getnumMinute = null;
    String getContentReason = null;
    TextView alarmtime = null;
    TextView alarmtext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rfvaca_popup2);
        Editbutton = (Button)findViewById(R.id.popup_button1);
        alarmtime = (TextView)findViewById(R.id.alarmtime);
        alarmtext = (TextView)findViewById(R.id.alarmtext);
        Intent data = getIntent();

        getFirstorEnd = data.getStringExtra("listFirstorEnd");
        getnumHour = data.getStringExtra("listnumHour");
        getnumMinute = data.getStringExtra("listnumMinute");
        getContentReason = data.getStringExtra("listContentReason");
        alarmtime.setText(getnumHour + "시 " +getnumMinute+ "분");
        alarmtext.setText(getContentReason);
        Editbutton.setOnClickListener(new View.OnClickListener() { // 알람수정 버튼 눌렀을 경우
            public void onClick(View v) { //
                Intent intent = new Intent(
                        getApplicationContext(), Rfvaca_popup3.class);
                intent.putExtra("listFirstorEnd", getFirstorEnd);
                intent.putExtra("listnumHour", getnumHour);
                intent.putExtra("listnumMinute", getnumMinute);
                intent.putExtra("listContentReason", getContentReason);

                startActivity(intent);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 팝업창에서 가지고오는 정보로 실행
        if (requestCode == 1) {
            if (resultCode == 12345) {

                Intent intent = data;
                Intent putintent = new Intent();
                getFirstorEnd = intent.getStringExtra("checkFirstorEnd");
                getnumHour = intent.getStringExtra("numHour");
                getnumMinute = intent.getStringExtra("numMinute");
                getContentReason = intent.getStringExtra("reportReason");
                Log.d("data success", "data success");
                putintent.putExtra("checkFirstorEnd", getFirstorEnd);
                putintent.putExtra("numHour", getnumHour);
                putintent.putExtra("numMinute",getnumMinute);
                putintent.putExtra("reportReason", getContentReason);

                setResult(12345, putintent);
                finish();
            }
        }
    }

}
