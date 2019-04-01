package com.example.minsookang.soms;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class UsermngActivity extends AppCompatActivity {
    //사용자 관리 구현
    //데이터베이스 연동 필요
    ListView listView;
    ListView listView2;
    IconTextListAdapter adapter;
    IconTextListAdapter2 adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermng);


        String[] song1 = new String[2];//위의 리스트뷰 예시
        song1[0] = "병사 1";
        song1[1] = "19-12345678";


        String[] song2 = new String[2];//위의 리스트뷰 예시
        song2[0] = "병사 2";
        song2[1] = "19-34895678";


        String[] song3 = new String[2];//아래의 리스트뷰 예시
        song3[0] = "간부1";
        song3[1] = "10-123456";

        String[] song4 = new String[2];//아래의 리스트뷰 예시
        song4[0] = "병사3";
        song4[1] = "18-12345644";


        listView = (ListView) findViewById(R.id.list1);
        adapter = new IconTextListAdapter(this);
        listView2 = (ListView) findViewById(R.id.list2);

        adapter2 = new IconTextListAdapter2(this);
        Resources res = getResources();


        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.blueheart),song1));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.blueheart),song2));
        listView.setAdapter(adapter);

        adapter2.addItem(new IconTextItem2(res.getDrawable(R.drawable.blueheart),song3));
        adapter2.addItem(new IconTextItem2(res.getDrawable(R.drawable.blueheart),song4));
        listView2.setAdapter(adapter2);

    }




}
