package com.example.guest.codelog.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.guest.codelog.R;

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
        }
    }
}
