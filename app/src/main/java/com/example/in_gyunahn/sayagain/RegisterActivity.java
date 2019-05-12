package com.example.in_gyunahn.sayagain;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity extends AppCompatActivity  {
    private FirebaseAuth mAuth;
    private EditText idText;
    private EditText passwordText;
    private EditText passwordText2;
    private Button checkBtn;
    private Button registerBtn;
    private DatabaseReference mDatabase;




    String email = "";
    String password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();


        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        passwordText2 = (EditText) findViewById(R.id.passwordText2);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        checkBtn = (Button) findViewById(R.id.checkBtn);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        //아이디 중복체크 확인 버튼
        checkBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                email = idText.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    idText.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(RegisterActivity.this, "아이디를 이메일형식에 맞게 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    idText.requestFocus();
                    return;
                 }

            }
        });

        // 회원가입버튼
        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RegisterUser(idText.getText().toString(), passwordText.getText().toString());

            }
        });


        //비밀번호 일치 확인 초록:일치, 빨강:불일치
        passwordText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = passwordText.getText().toString();
                String confirm = passwordText2.getText().toString();

                if(password.equals(confirm)){
                    passwordText.setBackgroundColor(Color.GREEN);
                    passwordText2.setBackgroundColor(Color.GREEN);
                }
                else {
                    passwordText.setBackgroundColor(Color.RED);
                    passwordText2.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//비밀번호 확인
    }


//    public void isValidEmail() {
//        if (email.isEmpty()) {
//            // 이메일 공백
//            Toast.makeText(this, "아이디를 입력해 주세요.", Toast.LENGTH_SHORT).show();
//            return;
//        } if (password.isEmpty()){
//            Toast.makeText(this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
//        }
//        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Toast.makeText(this, "아이디를 이메일형식에 맞게 입력해 주세요.", Toast.LENGTH_SHORT).show();
//        }
//        else if(password.length()<6){
//            Toast.makeText(this, "비밀번호는 6자리 이상입니다.", Toast.LENGTH_SHORT).show();
//        }
//
//    }


    private void RegisterUser(final String email, final String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            Toast.makeText(RegisterActivity.this, "아이디를 이메일형식에 맞게 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        }else {
                            if (task.isSuccessful()) {
                                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(RegisterActivity.this, "회원가입 성공.", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();


                            } else {

                                Log.w("signInWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "회원가입 실패.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        // ...
                    }
                });

    }


}



