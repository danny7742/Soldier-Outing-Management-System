package com.example.minsookang.soms;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

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
        song1[0] = "My Way";
        song1[1] = "이수";


        String[] song2 = new String[2];//아래의 리스트뷰 예시
        song2[0] = "My Wa222222y";
        song2[1] = "이22222222수";


        listView = (ListView) findViewById(R.id.list1);
        adapter = new IconTextListAdapter(this);
        listView2 = (ListView) findViewById(R.id.list2);

        adapter2 = new IconTextListAdapter2(this);
        Resources res = getResources();


        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.blueheart),song1));
        listView.setAdapter(adapter);

        adapter2.addItem(new IconTextItem(res.getDrawable(R.drawable.blueheart),song2));
        listView2.setAdapter(adapter2);

    }
}
