package com.example.minsookang.soms;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoldierVacationMngActivity extends AppCompatActivity {
    //보고를 하는 메세지창
    ListView listView;
    IconTextListAdapter adapter;
    ArrayList<Userinfo> userinfomngList = new ArrayList<Userinfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldiervacationmng);
        Button permbutton = (Button) findViewById(R.id.button2);
        final Resources res = getResources();
        userinfomngList = (ArrayList<Userinfo>) getIntent().getSerializableExtra("UserinfoList");
        final FirebaseFirestore db = FirebaseFirestore.getInstance();


        listView = (ListView) findViewById(R.id.listview1);
        adapter = new IconTextListAdapter(this);
        db.collection("Soldier")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(QueryDocumentSnapshot doc : task.getResult()) {
                        ////현재사용자
                        String[] tmpuser = new String[2];
                        tmpuser[0] = doc.getString("UserName");
                        tmpuser[1] = doc.get("SerialNum").toString();
                        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.blueheart), tmpuser));

                }

                listView.setAdapter(adapter);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Solvacamng_popup.class);
                String presentStatus = "1";
                intent.putExtra("presentStatus", presentStatus);
                startActivityForResult(intent, 1);
            }
        });

        permbutton.setOnClickListener(new View.OnClickListener(){ // 휴가등록 버튼 눌렀을 경우
            public void onClick(View v){ //
                Intent intent = new Intent(
                        getApplicationContext(),SoldierVacationMngPermissionActivity.class);
                startActivity(intent);
            }
        });
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