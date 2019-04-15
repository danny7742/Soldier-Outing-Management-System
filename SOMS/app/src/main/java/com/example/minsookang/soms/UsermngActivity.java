package com.example.minsookang.soms;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UsermngActivity extends AppCompatActivity {
    //사용자 관리 구현
    //데이터베이스 연동 필요
    ListView listView;
    ListView listView2;
    IconTextListAdapter adapter;
    IconTextListAdapter2 adapter2;
    ArrayList<Userinfo> userinfomngList = new ArrayList<Userinfo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermng);


        listView = (ListView) findViewById(R.id.list1);
        adapter = new IconTextListAdapter(this);

        listView2 = (ListView) findViewById(R.id.list2);
        adapter2 = new IconTextListAdapter2(this);

        Resources res = getResources();


        userinfomngList = (ArrayList<Userinfo>) getIntent().getSerializableExtra("UserinfoList");
        for(int i = 0; i < userinfomngList.size(); i++){
            if(userinfomngList.get(i).getAccess_authority()==0){
                String[] noUser = new String[2];
                noUser[0] = userinfomngList.get(i).getUser_Name();
                noUser[1] = String.valueOf(userinfomngList.get(i).getUser_serialNum());
                adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.blueheart),noUser));
            }
            else{
                String[] yesUser = new String[2];
                yesUser[0] = userinfomngList.get(i).getUser_Name();
                yesUser[1] = String.valueOf(userinfomngList.get(i).getUser_serialNum());
                adapter2.addItem(new IconTextItem2(res.getDrawable(R.drawable.blueheart),yesUser));
            }
        }
//        song1[0] = "병사 1";
//        song1[1] = "19-12345678";
//
//
//        String[] song2 = new String[2];//위의 리스트뷰 예시
//        song2[0] = "병사 2";
//        song2[1] = "19-34895678";
//
//
//        String[] song3 = new String[2];//아래의 리스트뷰 예시
//        song3[0] = "간부1";
//        song3[1] = "10-123456";
//
//        String[] song4 = new String[2];//아래의 리스트뷰 예시
//        song4[0] = "병사3";
//        song4[1] = "18-12345644";

        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                String data1 = "1";
                intent.putExtra("data1", data1);
                startActivityForResult(intent, 1);
            }
        });


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                String data1 = "2";
                intent.putExtra("data1", data1);
                startActivityForResult(intent, 1);
            }
        });


    }

    public void vacbutton(View v){ // 휴가제한 등록 버튼을 누르면 실행
        Intent intent = new Intent(this, PopupActivity.class);
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
