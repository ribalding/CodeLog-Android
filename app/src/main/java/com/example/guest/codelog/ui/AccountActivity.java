package com.example.guest.codelog.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.codelog.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.newProject) Button mNewProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);

        mNewProject.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mNewProject){
            Intent newProjectIntent = new Intent(AccountActivity.this, NewProjectActivity.class);
            startActivity(newProjectIntent);
        }
    }
}
