package com.example.guest.codelog.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.codelog.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    @Bind(R.id.logInButton) Button mLogInButton;
    @Bind(R.id.signUpButton) Button mSignUpButton;
    @Bind(R.id.enterEmail) EditText mEnterEmail;
    @Bind(R.id.enterPassword) EditText mEnterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this);

        mLogInButton.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mLogInButton){
            login();
        } else if (view == mSignUpButton){
            Intent signUpIntent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(signUpIntent);
        }
    }

    private void login(){

        final String email = mEnterEmail.getText().toString();
        final String pass = mEnterPassword.getText().toString();


        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent loginIntent = new Intent (MainActivity.this, AccountActivity.class);
                            startActivity(loginIntent);
                            finish();
                        } else if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }

}
