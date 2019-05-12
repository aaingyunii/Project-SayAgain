package com.example.in_gyunahn.sayagain;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private EditText idText;
    private EditText passwordText;
    private Button registerBtn;
    private  Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

//        if(user != null){
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            //Intent intent = new Intent(getApplicationContext(), Sample.class);
//            startActivity(intent);
//            finish();
//        }

        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        registerBtn =  (Button) findViewById(R.id.registerBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        // 회원가입 버튼
        registerBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        //로그인 버튼
        loginBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
//                LoginActivity.this.startActivity(loginIntent);
                String email = idText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();
                if(email.length()==0){
                    Toast.makeText(LoginActivity.this,"아이디를 확인해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()==0){
                    Toast.makeText(LoginActivity.this,"비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginProcess task = new LoginProcess();
                task.execute(email, password);
//                LoginUser(email,password);
//                LoginActivity.this.startActivity(loginIntent); // 메인화면으로 이동시키는 구문

            }
        });
    }

    public void  LoginUser(final String email, final String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // 로그인 성공
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                LogInSuccess();

                            }else {
                                // 로그인 실패
                                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                            }
                        }

                });
    }
    private void LogInSuccess(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private class LoginProcess extends AsyncTask<String, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                LoginActivity.this);

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("Please wait...");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... args) {
            try {
                LoginUser(args[0], args[1]);
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this,""+e,Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            asyncDialog.dismiss();
        }
    }




}
