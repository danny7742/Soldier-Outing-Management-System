package com.example.minsookang.soms;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    ImageView stateimage;
    int state = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateimage = (ImageView) findViewById(R.id.stateimage);
        Button reportbutton = (Button) findViewById(R.id.reportbutton);
        Button vcabutton = (Button) findViewById(R.id.vacbutton);



        switch (state) {  // 병사의 상태에 따라 레이아웃이 바뀜
            case 0: stateimage.setImageResource(R.drawable.yellowheart);
                     reportbutton.setVisibility(View.GONE);
                     break;
            case 1: stateimage.setImageResource(R.drawable.blueheart);
                     break;
            case 2: stateimage.setImageResource(R.drawable.greenheart);
                     break;
            case 3: stateimage.setImageResource(R.drawable.pinkheart);
                     break;
        }


        reportbutton.setOnClickListener(new View.OnClickListener(){ // 보고하기 버튼 눌렀을 경우
            public void onClick(View v){
                Intent intent = new Intent(
                        getApplicationContext(),UsermngActivity.class);
                startActivity(intent);
            }
        });


//        });



    }


}
