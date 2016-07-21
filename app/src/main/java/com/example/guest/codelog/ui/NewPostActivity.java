package com.example.guest.codelog.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.codelog.R;
import com.example.guest.codelog.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private DatabaseReference mProjectReference;
    private String mProjectKey;
    @Bind(R.id.newPostBody) EditText mNewPostBody;
    @Bind(R.id.newPostHeadline) TextView mNewPostHeadline;
    @Bind(R.id.newPostTitle) EditText mNewPostTitle;
    @Bind(R.id.submitNewPost) Button mSubmitNewPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();

        mProjectKey = getIntent().getStringExtra("projectKey");
        mProjectReference = FirebaseDatabase.getInstance().getReference().child(uid).child("projects").child(mProjectKey).child("posts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        ButterKnife.bind(this);
        mSubmitNewPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mSubmitNewPost){
            createNewPost();
        }
    }

    public void createNewPost(){
        String postBody = mNewPostBody.getText().toString();
        String postTitle = mNewPostTitle.getText().toString();
        Post newPost = new Post(postTitle, postBody, mProjectKey);
        DatabaseReference pushRef = mProjectReference.push();
        pushRef.setValue(newPost);
    }
}
