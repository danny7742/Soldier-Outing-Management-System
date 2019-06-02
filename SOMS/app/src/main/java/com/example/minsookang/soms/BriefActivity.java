package com.example.minsookang.soms;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ListView;

import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;

import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;


import java.lang.reflect.Array;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import static com.example.minsookang.soms.MainActivity.Password;
import static com.example.minsookang.soms.MainActivity.SerialNum;
import static com.example.minsookang.soms.MainActivity.TroopCode;
import static com.example.minsookang.soms.MainActivity.UserClass;
import static com.example.minsookang.soms.MainActivity.UserName;


public class BriefActivity extends AppCompatActivity {
    ArrayList<ChatVO> list = new ArrayList<>();
    ListView lv;
    Button btn;
    EditText edt;
    public String userid = "강민수";
    public int userclass = 1;
    String intentid = "조장연";
    String msgname;
    String msggps = "";
    public int check = 0;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brief);

/* 텍스트뷰만 있을때만 쓸수있어
ArrayAdapter<ChatVO> adapter = new ArrayAdapter<ChatVO>(getApplicationContext(), R.layout.talklist, list);*/

        lv = findViewById(R.id.brieflist);
        edt = findViewById(R.id.brieftext);
        btn = findViewById(R.id.sendbtn);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Log.d("aaaaaaaa", UserName);
        Log.d("aaaaaaa", SerialNum);
        Log.d("aaaaaa", TroopCode);
        Log.d("aaaaa", UserClass);
        Log.d("aaaa", Password);



        if(userclass ==1)
            msgname = userid + "message";
        else
            msgname = intentid + "message";

        myRef = database.getReference(msgname);


//로그인한 아이디
        // id =
//lv.setAdapter(adapter);

//list.add(new ChatVO(R.drawable.profile3, "찡찡이", "안녕", "오후 4:42"));

        final BriefAdapter adapter = new BriefAdapter(getApplicationContext(), R.layout.brief_item, list, userid);
        ((ListView) findViewById(R.id.brieflist)).setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "내용을 입력하세요.", Toast.LENGTH_LONG).show();
                }else {
                    Date today = new Date();
                    SimpleDateFormat timeNow = new SimpleDateFormat("a K:mm");
                    StringBuffer sb = new StringBuffer(edt.getText().toString());
                    if (sb.length() >= 15) {
                        for (int i = 1; i <= sb.length() / 15; i++) {
                            sb.insert(15 * i, "\n");
                        }
                    }
                    myRef.push().setValue(new ChatVO(userid, sb.toString(), timeNow.format(today),0));
                    edt.setText("");
                }
            }
        });


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatVO value = dataSnapshot.getValue(ChatVO.class); // 괄호 안 : 꺼낼 자료 형태
                list.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Button gpsbutton = (Button) findViewById(R.id.gpsbtn);
        gpsbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),MapsActivity.class);///////////////////////클래스 니껄로 바꿔
                String data1 = "2";
                intent.putExtra("data1", data1);
                startActivityForResult(intent, 2);
                Log.d("sdfsdfsdf",msggps);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 팝업창에서 가지고오는 정보로 실행
        if(requestCode == 2){
            if(resultCode == 112){


                msggps = data.getStringExtra("address");

                Date today = new Date();
                SimpleDateFormat timeNow = new SimpleDateFormat("a K:mm");
                StringBuffer sb = new StringBuffer(msggps);
                if (sb.length() >= 15) {
                    for (int i = 1; i <= sb.length() / 15; i++) {
                        sb.insert(15 * i, "\n");
                    }}
                Log.d("aaaaaaa", sb.toString());
                myRef.push().setValue(new ChatVO(userid, sb.toString(), timeNow.format(today),1));
                msggps = "";
            }
        }}
}


