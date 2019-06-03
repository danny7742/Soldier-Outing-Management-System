package com.example.minsookang.soms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class LoginActivity extends AppCompatActivity {

    public static String UN;
    public static String SN;
    public static String TC;
    public static String UC;
    public static String state;



    private Button btnRegist;
    private Button btnLogin;
    private EditText serialNum;
    private EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        serialNum = (EditText) findViewById(R.id.Serialnum);
        Password = (EditText) findViewById(R.id.password);
        btnRegist = (Button) findViewById(R.id.btnRegist);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivityForResult(intent, 1000);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                final DocumentReference docRef = db.collection("Soldier").document(serialNum.getText().toString());
                final DocumentReference docRef2 = db.collection("Soldier").document(serialNum.getText().toString()).collection("Vacation").document("State");
                docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    }
                });

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot doc = task.getResult();

                        if(serialNum.getText().toString().equals(doc.get("SerialNum").toString()) && (Password.getText().toString().equals(doc.getString("Password")))){
                            Log.d("Login Success", "Login Success");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            UN = doc.getString("UserName");
                            SN = doc.get("SerialNum").toString();
                            TC = doc.get("TroopCode").toString();
                            UC = doc.get("UserClass").toString();
                            state = docRef.collection("Vacation").document("State").get().toString();

//                            intent.putExtra("UserSerialNum", doc.get("SerialNum").toString());
//                            intent.putExtra("UserTroopCode", doc.get("TroopCode").toString());
//                            intent.putExtra("UserClass", doc.get("UserClass").toString());
//                            intent.putExtra("UserName", doc.getString("UserName"));
                            startActivity(intent);
                        }else if(serialNum.getText().toString().equals(doc.get("SerialNum").toString()) && (Password.getText().toString().equals(doc.getString("Password"))) && doc.getString("Wait").equals(0)){
                            //로그인 됬는데 허가가 안떨어졌다.
                        }else{
                            //로그인 안됬다
                        }
                    }
                });


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // setResult를 통해 받아온 요청번호, 상태, 데이터
        Log.d("RESULT", requestCode + "");
        Log.d("RESULT", resultCode + "");
        Log.d("RESULT", data + "");

        if(requestCode == 1000 && resultCode == RESULT_OK) {
            Toast.makeText(LoginActivity.this, "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();

        }
    }




}
