package com.example.guest.codelog.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.codelog.R;
import com.example.guest.codelog.adapters.PostListAdapter;
import com.example.guest.codelog.adapters.ProjectListAdapter;
import com.example.guest.codelog.models.Post;
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

public class ProjectDetailActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.postRecyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.projectDetailName) TextView mProjectDetailName;
    @Bind(R.id.addNewPost) Button mAddNewPost;
    private ArrayList<Project> mProjects;
    private DatabaseReference mUserReference;
    private FirebaseAuth mAuth;
    private String projectKey;
    private PostListAdapter mAdapter;
    private ArrayList<Post> mPosts = new ArrayList<>();

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
        mAddNewPost.setOnClickListener(this);

        mUserReference.child("projects").child(projectKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              String projectName = dataSnapshot.child("projectName").getValue().toString();
                mProjectDetailName.setText(projectName);
                for(DataSnapshot postSnapshot : dataSnapshot.child("posts").getChildren()){
                    String postTitle = postSnapshot.child("title").getValue().toString();
                    Log.d("title", postTitle);
                    String postBody = postSnapshot.child("postBody").getValue().toString();
                    Log.d("postBody", postBody);
                    Post newPost = new Post(postTitle, postBody, projectKey);
                    mPosts.add(newPost);
                }
                mAdapter = new PostListAdapter(getApplicationContext(), mPosts);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProjectDetailActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        if(view == mAddNewPost){
            Intent newPostIntent = new Intent(ProjectDetailActivity.this, NewPostActivity.class);
            newPostIntent.putExtra("projectKey", projectKey);
            startActivity(newPostIntent);
        }
    }
}