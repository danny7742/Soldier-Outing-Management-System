package com.example.minsookang.soms;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SoldierVacationMngPermissionActivity extends AppCompatActivity {
    //보고를 하는 메세지창
    ListView listView;
    IconTextListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldiervacationpermission);

        String[] song2 = new String[2];// 리스트뷰 예시 나중에 디비연동해야함
        song2[0] = "병사 2";
        song2[1] = "19-34895678";

        listView = (ListView) findViewById(R.id.listview1);
        adapter = new IconTextListAdapter(this);
        Resources res = getResources();

        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.blueheart),song2));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Solvacamng_popup.class);
                String data1 = "1";
                intent.putExtra("data1", data1);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void vacbutton(View v){ // 휴가제한 등록 버튼을 누르면 실행
        Intent intent = new Intent(this, Solvacamng_popup.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 팝업창에서 가지고오는 정보로 실행
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                String result = data.getStringExtra("result");
            }
        }
    }


}