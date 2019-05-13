package com.example.minsookang.soms;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RfVacationActivity extends AppCompatActivity {
    String FirstorEnd = null;
    String numHour = null;
    String numMinute = null;
    String contentReason = null;
    ListView listview = null;
    ArrayList<String> RfVacMngFirstorEnd = new ArrayList<String>();
    ArrayList<String> RfVacMngnumHour = new ArrayList<String>();
    ArrayList<String> RfVacMngnumMinute = new ArrayList<String>();
    ArrayList<String> RfVacMngContentReason = new ArrayList<String>();
    static int getchecked = 0;

    //데이터를 저장하게 되는 리스트
    List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfvacation);
        Button alarmbutton = (Button) findViewById(R.id.add_alarmbtn);
        listview = (ListView) findViewById(R.id.alarmlistview);

        //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        //리스트뷰의 어댑터를 지정해준다.
        listview.setAdapter(adapter);
        if(FirstorEnd !=  null){
            list.add(contentReason + ": 출발일 " + numHour + "시 " + numMinute + "분");
            adapter.add("test");

        }
        //리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                Intent intent = new Intent(
                        getApplicationContext(), Rfvaca_popup2.class);
                intent.putExtra("listFirstorEnd", RfVacMngFirstorEnd.get(position));
                intent.putExtra("listnumHour", RfVacMngnumHour.get(position));
                intent.putExtra("listnumMinute", RfVacMngnumMinute.get(position));
                intent.putExtra("listContentReason", RfVacMngContentReason.get(position));
                startActivityForResult(intent, 1);
                getchecked = position;
            }
        });

        //리스트뷰에 보여질 아이템을 추가

        alarmbutton.setOnClickListener(new View.OnClickListener() { // 알람추가 버튼 눌렀을 경우
            public void onClick(View v) { //
                Intent intent = new Intent(
                        getApplicationContext(), Rfvaca_popup.class);
                startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 팝업창에서 가지고오는 정보로 실행
        if (requestCode == 1) {
            if (resultCode == 1234) {

                Intent intent = data;
                FirstorEnd = intent.getStringExtra("checkFirstorEnd");
                numHour = intent.getStringExtra("numHour");
                numMinute = intent.getStringExtra("numMinute");
                contentReason = intent.getStringExtra("reportReason");
                Log.d("data success", "data success");
                Log.d("testFirstorEnd", FirstorEnd);
                Log.d("testFirstorEnd", numHour);
                Log.d("testFirstorEnd", numMinute);
                Log.d("testFirstorEnd", contentReason);
                RfVacMngFirstorEnd.add(FirstorEnd);
                RfVacMngnumHour.add(numHour);
                RfVacMngnumMinute.add(numMinute);
                RfVacMngContentReason.add(contentReason);
                //DB에 정보 입력
                list.add(contentReason + ": " + FirstorEnd + " " + numHour + "시 " + numMinute + "분");
                listview.clearChoices();
                // listview 갱신.
                adapter.notifyDataSetChanged();

            }
            else if(resultCode == 12345){
                Intent intent = data;
                FirstorEnd = intent.getStringExtra("checkFirstorEnd");
                numHour = intent.getStringExtra("numHour");
                numMinute = intent.getStringExtra("numMinute");
                contentReason = intent.getStringExtra("reportReason");
                list.set(getchecked, contentReason + ": " + FirstorEnd + " " + numHour + "시 " + numMinute + "분");
                RfVacMngFirstorEnd.set(getchecked, FirstorEnd);
                RfVacMngnumHour.set(getchecked, numHour);
                RfVacMngnumMinute.set(getchecked, numMinute);
                RfVacMngContentReason.set(getchecked, contentReason);
                listview.clearChoices();
                // listview 갱신.
                adapter.notifyDataSetChanged();
            }
        }
    }

}
