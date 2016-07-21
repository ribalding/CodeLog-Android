package com.example.guest.codelog.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.guest.codelog.R;
import com.example.guest.codelog.models.Project;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectDetailActivity extends AppCompatActivity {
    @Bind(R.id.projectDetailName) TextView mProjectDetailName;
    private ArrayList<Project> mProjects;
    private DatabaseReference mUserReference;
    private FirebaseAuth mAuth;
    private String projectKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        mUserReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        mProjects = Parcels.unwrap(getIntent().getParcelableExtra("projects"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        Project thisProject = mProjects.get(startingPosition);
        projectKey = thisProject.getProjectKey();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        ButterKnife.bind(this);

        mUserReference.child("projects").child(projectKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              String projectName = dataSnapshot.child("projectName").getValue().toString();
                mProjectDetailName.setText(projectName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}