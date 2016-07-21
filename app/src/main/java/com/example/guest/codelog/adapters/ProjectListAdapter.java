package com.example.guest.codelog.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.codelog.R;
import com.example.guest.codelog.models.Project;
import com.example.guest.codelog.ui.ProjectDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/20/16.
 */
public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder> {
    private ArrayList<Project> mProjects = new ArrayList<>();
    private Context mContext;

    public ProjectListAdapter(Context context, ArrayList<Project> projects){
        mContext = context;
        mProjects = projects;

    }
    @Override
    public ProjectListAdapter.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent, false);
        ProjectViewHolder viewHolder = new ProjectViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProjectListAdapter.ProjectViewHolder holder, int position) {
        holder.bindProject(mProjects.get(position));
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.projectListItemTitle) TextView mProjectListItemTitle;

        private Context mContext;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindProject (Project project){
            mProjectListItemTitle.setText(project.getProjectName());
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ProjectDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("projects", Parcels.wrap(mProjects));
            mContext.startActivity(intent);
        }
    }
}
