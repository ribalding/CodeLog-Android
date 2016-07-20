package com.example.guest.codelog;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    @Bind(R.id.newUserNameInput) EditText mNewUserNameInput;
    @Bind(R.id.newUserEmailInput) EditText mNewUserEmailInput;
    @Bind(R.id.newUserPasswordInput) EditText mNewUserPasswordInput;
    @Bind(R.id.newUserConfirmInput) EditText mNewUserConfirmInput;
    @Bind(R.id.signUpSubmitButton) Button mSignUpSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this);
        mSignUpSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mSignUpSubmitButton){
            createNewUser();

        }
    }

    private void createNewUser(){
        String name = mNewUserNameInput.getText().toString().trim();
        String email = mNewUserEmailInput.getText().toString().trim();
        String pass = mNewUserPasswordInput.getText().toString().trim();
        String confirm = mNewUserConfirmInput.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "New User Created Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
