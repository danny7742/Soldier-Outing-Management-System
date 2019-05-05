package com.example.minsookang.soms;
/*
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static android.support.constraint.Constraints.TAG;

public class DBActivity {

    private ArrayList<User> users;

    void getUserInfo(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference user = db.collection("UserInformation");

        user.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>(){
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots){
                if(queryDocumentSnapshots.isEmpty()){
                    Log.d(TAG, "onSuccess: LIST EMPTY");
                }
                else{
                    List<User> types = queryDocumentSnapshots.toObjects(User.class);
                    users.addAll(types);
                    Log.d(TAG, "onSuccess:" + users);


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    void setUserInfo(User users){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbTables = db.collection("UserInformation")

    }
}
*/