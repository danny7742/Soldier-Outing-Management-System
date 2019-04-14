package com.example.minsookang.soms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PermissionPopup extends Activity { //병사 관리화면에서 해당 병사를 클릭하였을때 승인/제거 팝업창

    private int type;
    TextView txtText;
    private String datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.permission_popup);

        Intent data = getIntent();
        datas = data.getStringExtra("data1");
        type = Integer.parseInt(datas);

        txtText = (TextView)findViewById(R.id.popupText);

    }

    public void buttonLeft(View v){  // 승인하기 버튼 클릭시 작동

    }


    public void buttonCenter(View v){  // 제거하기 버튼 클릭시 작동

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
