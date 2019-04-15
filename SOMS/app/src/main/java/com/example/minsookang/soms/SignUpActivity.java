package com.example.minsookang.soms;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userPassword;
    private EditText userPasswordConfirm;
    private EditText troopCode;
    private EditText serialNum;
    private EditText userClass;
    private Button btnDone;
    private Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = (EditText) findViewById(R.id.nameText);
        userPassword = (EditText) findViewById(R.id.passwordText);
        userPasswordConfirm = (EditText) findViewById(R.id.passwordConfText);
        troopCode = (EditText) findViewById(R.id.troopcodeText);
        serialNum = (EditText) findViewById(R.id.serialnumText);
        userClass = (EditText) findViewById(R.id.classText);
        btnDone = (Button) findViewById(R.id.registerButton);
        btnComplete = (Button) findViewById(R.id.completeButton);


        //비밀번호 일치 검사
        userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = userPassword.getText().toString();
                String confirm = userPasswordConfirm.getText().toString();

                if( password.equals(confirm) ) {
                    userPassword.setBackgroundColor(Color.GREEN);
                    userPasswordConfirm.setBackgroundColor(Color.GREEN);
                } else {
                    userPassword.setBackgroundColor(Color.RED);
                    userPasswordConfirm.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //입력안된거잇으면 토스트띄우기
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( userName.getText().toString().length() == 0 ) {
                    Toast.makeText(SignUpActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                    return;
                }

                if( userPassword.getText().toString().length() == 0 ) {
                    Toast.makeText(SignUpActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    userPassword.requestFocus();
                    return;
                }

                if( userPasswordConfirm.getText().toString().length() == 0 ) {
                    Toast.makeText(SignUpActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    userPasswordConfirm.requestFocus();
                    return;
                }


                if( troopCode.getText().toString().length() == 0 ) {
                    Toast.makeText(SignUpActivity.this, "부대코드를 입력하세요!", Toast.LENGTH_SHORT).show();
                    troopCode.requestFocus();
                    return;
                }

                if( serialNum.getText().toString().length() == 0 ) {
                    Toast.makeText(SignUpActivity.this, "군번을 입력하세요!", Toast.LENGTH_SHORT).show();
                    serialNum.requestFocus();
                    return;
                }

                if( userClass.getText().toString().length() == 0 ) {
                    Toast.makeText(SignUpActivity.this, "계급을 입력하세요!(0 : 병사, 1 :간부, 2: 지휘관) ", Toast.LENGTH_SHORT).show();
                    userClass.requestFocus();
                    return;
                }

                if( !userPassword.getText().toString().equals(userPasswordConfirm.getText().toString()) ) {
                    Toast.makeText(SignUpActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    userPassword.setText("");
                    userPasswordConfirm.setText("");
                    userPassword.requestFocus();
                    return;
                }


                Intent result_serialnum = new Intent();
                result_serialnum.putExtra("이름", serialNum.getText().toString());

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result_serialnum);

                //회원가입 버튼누르면 승인대기중입니다 토스트 띄움
                Toast.makeText(SignUpActivity.this, "승인까지 기다려주세요.", Toast.LENGTH_SHORT).show();


                //돌아가기버튼 누르면 로그인화면으로
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivityForResult(intent, 1000);

            }
        });


       btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }




}
