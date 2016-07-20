package com.example.guest.codelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            Intent loginIntent = new Intent(MainActivity.this, AccountActivity.class);
            startActivity(loginIntent);
        } else if (view == mSignUpButton){
            Intent signUpIntent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(signUpIntent);
        }
    }

}
