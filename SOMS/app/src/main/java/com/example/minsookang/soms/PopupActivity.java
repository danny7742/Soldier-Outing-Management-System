package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PopupActivity extends Activity {
    //각종 팝업 관련 Activity 구현 예정
    private int type;
    TextView txtText;
    private String datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        Intent data = getIntent();
        datas = data.getStringExtra("data1");
        type = Integer.parseInt(datas);

        txtText = (TextView)findViewById(R.id.popupText);

        if(type==1){
            txtText.setText("승인 하시겠습니까?");
            Button button = (Button)findViewById(R.id.popup_button1);
            button.setText("승인하기");
            Button button2 = (Button)findViewById(R.id.popup_button2);
            button2.setText("닫기");
        }

        else if(type==2){
            txtText.setText("제거 하시겠습니까?");
            Button button = (Button)findViewById(R.id.popup_button1);
            button.setText("제거하기");
            Button button2 = (Button)findViewById(R.id.popup_button2);
            button2.setText("닫기");
        }
    }
    public void buttonLeft(View v){  // 승인하기 혹은 제거하기 버튼 클릭시 작동

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
