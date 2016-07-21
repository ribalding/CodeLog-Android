package com.example.guest.codelog.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.codelog.R;
import com.example.guest.codelog.models.Post;
import com.example.guest.codelog.models.Project;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {
    private ArrayList<Post> mPosts = new ArrayList<>();
    private Context mContext;

    @Override
    public PostListAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        PostViewHolder viewHolder = new PostViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostListAdapter.PostViewHolder holder, int position) {
        holder.bindPost(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.postBodyDisplay) TextView mPostBodyDisplay;
        @Bind(R.id.postTitleDisplay) TextView mPostTitleDisplay;

        private Context mContext;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindPost (Post post){
            mPostBodyDisplay.setText(post.getPostBody());
            mPostTitleDisplay.setText(post.getTitle());
        }


        @Override
        public void onClick(View view) {

        }
    }
}
