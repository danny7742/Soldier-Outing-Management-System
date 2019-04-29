package com.example.minsookang.soms;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsermngActivity extends AppCompatActivity {
    //사용자 관리 구현
    //데이터베이스 연동 필요
    ListView listView;
    ListView listView2;
    IconTextListAdapter adapter;
    IconTextListAdapter2 adapter2;
    ArrayList<Userinfo> userinfomngList = new ArrayList<Userinfo>();
    int selecteditempos;
    int count;
    ArrayList<userList> userlistwait = new ArrayList<userList>();
    ArrayList<userList> userlistapprove = new ArrayList<userList>();
    static int isChanged = 0;
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

        userList userlist = new userList();


        for (int i = 0; i < userinfomngList.size(); i++) {
            if(userinfomngList.get(i).getAccess_authority() == 1){
                String[] tmpuser = new String[2];
                userlist.name = userinfomngList.get(i).getUser_Name();
                tmpuser[0] = userinfomngList.get(i).getUser_Name();
                userlist.id = String.valueOf(userinfomngList.get(i).getUser_serialNum());
                tmpuser[1] = String.valueOf(userinfomngList.get(i).getUser_serialNum());
                userlistapprove.add(userlist);
                adapter2.addItem(new IconTextItem2(res.getDrawable(R.drawable.blueheart), tmpuser));
            }
        }

        listView2.setAdapter(adapter2);

        for (int i = 0; i < userinfomngList.size(); i++) {
            if (userinfomngList.get(i).getAccess_authority() == 0) {
                String[] tmpuser = new String[2];
                userlist.name = userinfomngList.get(i).getUser_Name();
                tmpuser[0] = userinfomngList.get(i).getUser_Name();
                userlist.id = String.valueOf(userinfomngList.get(i).getUser_serialNum());
                tmpuser[1] = String.valueOf(userinfomngList.get(i).getUser_serialNum());
                userlistwait.add(userlist);
                adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.blueheart), tmpuser));

            }
        }

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                count = adapter.getCount() ;
                if (count > 0) {
                    // 현재 선택된 아이템의 position 획득.
                    selecteditempos = listView.getPositionForView(v);

                    Log.d("When selected", String.valueOf(selecteditempos));
                    Log.d("that time count", String.valueOf(count));
                }
                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                String data1 = "1";
                intent.putExtra("data1", data1);
                startActivityForResult(intent, 1);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                count = adapter2.getCount() ;

                if (count > 0) {
                    // 현재 선택된 아이템의 position 획득.
                    selecteditempos = listView2.getPositionForView(v);
                    //Log.d("When selected", String.valueOf(selecteditempos));
                    //Log.d("that time count", String.valueOf(count));
                }
                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                String data1 = "2";
                intent.putExtra("data1", data1);
                startActivityForResult(intent, 2);
            }
        });


    }

        public void vacbutton(View v){ // 휴가제한 등록 버튼을 누르면 실행
        Intent intent = new Intent(this, PopupActivity.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 팝업창에서 가지고오는 정보로 실행
        if(requestCode == 1){
            if(resultCode == 112){

                Log.d("Checkuserlistwait4", userlistwait.get(0).name);
                if (selecteditempos > -1 && selecteditempos < count) {
                    // 아이템 삭제
                    for(int i = 0 ; i < userinfomngList.size(); i ++) {
                        if(userinfomngList.get(i).getUser_Name() == userlistwait.get(selecteditempos).name)
                        {
                            userinfomngList.get(i).setAccess_authority(1);
                        }
                    }
                    String[] tmp2user = new String[2];
                    tmp2user[0] = userlistwait.get(selecteditempos).name;
                    Log.d("errortest", userlistwait.get(selecteditempos).name);

                    Log.d("errortest", userlistwait.get(selecteditempos).id);
                    tmp2user[1] = userlistwait.get(selecteditempos).id;
                    Resources res = getResources();
                    adapter2.addItem(new IconTextItem2(res.getDrawable(R.drawable.blueheart), tmp2user));
                    userlistapprove.add(userlistwait.get(selecteditempos));
                    userlistwait.remove(selecteditempos);

                    adapter.remove(selecteditempos);

                    // listview 선택 초기화.
                    listView.clearChoices();
                    // listview 갱신.
                    adapter.notifyDataSetChanged();
                }
                // 가입 승인
            }
        }
        else if(requestCode == 2){
            if(resultCode == 112){
                Log.d("delete test2", "delete test2");
                Log.d("selecteditempos", String.valueOf(selecteditempos));
                Log.d("count", String.valueOf(count));
                if (selecteditempos > -1 && selecteditempos < count) {
                    // 아이템 삭제
                    for(int i = 0 ; i < userinfomngList.size(); i ++) {
                        if(userinfomngList.get(i).getUser_Name() == userlistapprove.get(selecteditempos).name){
                            userinfomngList.remove(i);
                        }
                    }
                    // listview 선택 초기화.
                    adapter2.remove(selecteditempos);
                    userlistapprove.remove(selecteditempos);

                    listView2.clearChoices();

                    Log.d("delete test", "delete test");
                    // listview 갱신.
                    adapter2.notifyDataSetChanged();
                }
                //전역 or 전출. listview item제거
            }
        }
    }

    public class userList{
        String name;
        String id;
    }

}
