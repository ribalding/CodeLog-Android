package com.example.guest.codelog.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.codelog.R;
import com.example.guest.codelog.models.Post;
import com.example.guest.codelog.models.Project;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewProjectActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private DatabaseReference mUserReference;
    private String mUserKey;
    private FirebaseUser thisUser;


    @Bind(R.id.submitNewProject) Button mSubmitNewButton;
    @Bind(R.id.projectTitleSubmit) EditText mProjectTitleSubmit;
    @Bind(R.id.firstPostSubmit) EditText mFirstPostSubmit;
    @Bind(R.id.firstPostTitleSubmit) EditText mFirstPostTitleSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        thisUser = mAuth.getCurrentUser();
        mUserKey = thisUser.getUid();
        mUserReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mUserKey);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        ButterKnife.bind(this);


        mSubmitNewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mSubmitNewButton){
            String projectTitle = mProjectTitleSubmit.getText().toString().trim();
            String firstPostTitle = mFirstPostTitleSubmit.getText().toString().trim();
            String firstPostBody = mFirstPostSubmit.getText().toString().trim();
            createNewProject(projectTitle, firstPostTitle, firstPostBody);
            Toast.makeText(NewProjectActivity.this, "New Project Created", Toast.LENGTH_SHORT).show();
            Intent accountActivity = new Intent(NewProjectActivity.this, AccountActivity.class);
            startActivity(accountActivity);
        }
    }

    public void createNewProject(String title, String firstPostTitle, String firstPostBody){
        Project newProject = new Project(title, mUserKey);
        DatabaseReference pushRef = mUserReference.child("projects").push();
        String pushId = pushRef.getKey();
        Post newPost = new Post(firstPostTitle, firstPostBody, pushId);
        pushRef.setValue(newProject);
        DatabaseReference postRef = mUserReference.child("projects").child(pushId).child("posts").push();
        String postId = postRef.getKey();
        postRef.setValue(newPost);
    }
}
