package com.example.minsookang.soms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class BriefActivity extends AppCompatActivity {
    // 보고를 하는 메세지창

    ListView m_ListView;
    BriefAdapter m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brief);


        // 커스텀 어댑터 생성
        m_Adapter = new BriefAdapter();

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.brieflist);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        m_Adapter.add("2017/04/29",2);
        m_Adapter.add("병사 보고내용1 입니다",1);
        m_Adapter.add("병사 보고내용2 입니다",1);
        m_Adapter.add("병사 보고내용3 입니다",1);
        m_Adapter.add("간부 전송 내용입니다.",0);



        findViewById(R.id.sendbtn).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                                          EditText editText = (EditText) findViewById(R.id.brieftext) ;
                                          String inputValue = editText.getText().toString() ;
                                          editText.setText("");
                                          refresh(inputValue,0);
                                          }
        }
        );
    }

    private void refresh (String inputValue, int _str) {
        m_Adapter.add(inputValue,_str) ;
        m_Adapter.notifyDataSetChanged();
    }
}
