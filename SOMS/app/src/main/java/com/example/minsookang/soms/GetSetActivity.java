package com.example.minsookang.soms;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.google.firebase.firestore.FirebaseFirestore;
import static android.support.constraint.Constraints.TAG;


public class GetSetActivity implements Serializable {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final DocumentReference docRef = db.collection("UserInformation").document("UserInfo");


    private String UserName = "";
    private String Password = "";

    private int UserClass = 0;     // 0은 병사, 1은 간부 2는 지휘관
    private int TroopCode = 0;
    private int SerialNum = 0;     //10자리는 병사 8자리는 간부, 지휘관
    private int OutingType = 0;    //0이면 정기휴가 ,1이면 포상, 2면 위로
    private int OutingState = 0;   //0은 잔류, 1은 휴가 , 2는 외출, 3은 외박

    private int OutingRemainRegular = 21;
    private int OutingRemainReward = 0;
    private int OutingRemainGrant = 0;

    private boolean Authority = false;     //지휘관은 true 나머지는 false
    private boolean AccessAuthrity = false;//회원가입하면 처음에 false, 지휘관이 승인하면 true

    private SimpleDateFormat OutingStartDate = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private SimpleDateFormat OutingArriveDate = new SimpleDateFormat("yyyy.MM.dd HH:mm");


    public GetSetActivity(String UserName){
        this.UserName = UserName;
    }

    String getUserName(){
        db.collection("UserInformation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG,"이름 " + document.getString("UserName"));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return UserName;
    }

    String getPassword(){ return Password; }

    int getUserClass(){ return UserClass; }

    int getTroopCode(){ return TroopCode; }

    int getSerialNum(){ return SerialNum; }

    int getOutingType(){ return OutingType; }

    int getOutingState(){ return OutingState; }

    boolean getAuthority(){ return Authority; }

    boolean getAccessAuthority(){ return AccessAuthrity; }

    int getOutingRemainRegular(){ return OutingRemainRegular; }

    int getOutingRemainReward(){ return OutingRemainReward; }

    int getOutingRemainGrant(){ return OutingRemainGrant; }

    SimpleDateFormat getOutingStartDate(){
        return OutingStartDate;
    }

    SimpleDateFormat getOutingArriveDate(){
        return OutingArriveDate;
    }
}