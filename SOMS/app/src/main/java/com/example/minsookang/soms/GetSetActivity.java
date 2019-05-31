package com.example.minsookang.soms;
/*
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
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
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
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
    CollectionReference userInfo = db.collection("UserInformation");

    ArrayList<Object> soldiers = new ArrayList<Object>();
    ArrayList<Object> dates = new ArrayList<Object>();

    void getSoldierInfo(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userInfo = db.collection("Soldier");
        userInfo.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                soldiers.add(document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    void getDateInformation(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userInfo = db.collection("UserInformation");
        userInfo.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmm");
                        dates.add(df.format(document.getTimestamp("OutingStartDate")));
                        dates.add(df.format(document.getTimestamp("OutingArriveDate")));
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
}*/