package com.example.minsookang.soms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RfwaebakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfwaebak);
        Button alarmbutton = (Button) findViewById(R.id.add_alarmbtn);

        ListView listview = (ListView)findViewById(R.id.alarmlistview);


            //데이터를 저장하게 되는 리스트
            List<String> list = new ArrayList<>();

            //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, list);

            //리스트뷰의 어댑터를 지정해준다.
            listview.setAdapter(adapter);

            //리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView,
                                        View view, int position, long id) {

                    Intent intent = new Intent(
                            getApplicationContext(),Rfvaca_popup2.class);
                    startActivity(intent);

                }
            });

            //리스트뷰에 보여질 아이템을 추가
            list.add("사과");
            list.add("배");
            list.add("귤");
            list.add("바나나");

        alarmbutton.setOnClickListener(new View.OnClickListener(){ // 알람추가 버튼 눌렀을 경우
            public void onClick(View v){ //
                Intent intent = new Intent(
                        getApplicationContext(),Rfvaca_popup.class);
                startActivity(intent);
            }
        });

        }



}
