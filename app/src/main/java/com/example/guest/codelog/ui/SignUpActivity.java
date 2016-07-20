package com.example.guest.codelog.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.codelog.R;
import com.example.guest.codelog.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private DatabaseReference mUserReference;

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
        mUserReference = FirebaseDatabase.getInstance().getReference().child("Users");

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
        final String name = mNewUserNameInput.getText().toString().trim();
        final String email = mNewUserEmailInput.getText().toString().trim();
        final String pass = mNewUserPasswordInput.getText().toString().trim();
        String confirm = mNewUserConfirmInput.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "New User Created Successfully", Toast.LENGTH_SHORT).show();
                    String key = task.getResult().getUser().getUid();
                    createUserFirebaseReference(name, email, pass, key);
                }
            }
        });
    }

    private void createUserFirebaseReference(String name, String email, String password, String key){
        createNewUser();
        User newUser = new User(name, email, password);
        mUserReference.child(key).setValue(newUser);
    }
}
