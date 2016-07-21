package com.example.guest.codelog.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.codelog.R;
import com.example.guest.codelog.adapters.ProjectListAdapter;
import com.example.guest.codelog.models.Project;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.newProject) Button mNewProject;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.accountNameHeader) TextView mAccountNameHeader;

    private DatabaseReference mUserProjectsReference;
    private FirebaseAuth mAuth;
    private String uid;
    private ProjectListAdapter mAdapter;
    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        mUserName = mAuth.getCurrentUser().getDisplayName();

        mUserProjectsReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("projects");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);
        mUserProjectsReference.addValueEventListener(new ValueEventListener() {
            private ArrayList<Project> mProjects = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot projectSnapshot: dataSnapshot.getChildren()){
                   String projectTitle = projectSnapshot.child("projectName").getValue().toString();
                   Project newProject = new Project(projectTitle, uid);
                   mProjects.add(newProject);
               }
                mAdapter = new ProjectListAdapter(getApplicationContext(), mProjects);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AccountActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAccountNameHeader.setText("Welcome Back " + mUserName);

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
