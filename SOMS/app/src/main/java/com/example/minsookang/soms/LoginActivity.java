package com.example.minsookang.soms;

import android.content.Context;
import android.content.Intent;
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

//import com.amazonaws.mobile.auth.core.IdentityManager;
//import com.amazonaws.mobile.auth.core.SignInStateChangeListener;
//import com.amazonaws.mobile.auth.ui.AuthUIConfiguration;
//import com.amazonaws.mobile.auth.ui.SignInUI;
//import com.amazonaws.mobile.client.AWSMobileClient;
//import com.amazonaws.mobile.client.AWSStartupHandler;
//import com.amazonaws.mobile.client.AWSStartupResult;
//import com.amazonaws.mobile.config.AWSConfiguration;
//import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;

public class LoginActivity extends AppCompatActivity {
    //AWS연동 LoginActivity 구현

    private Button btnRegist;
    private Button btnLogin;
    private EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.nameText);
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
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
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
