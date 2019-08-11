package com.javeria.posts.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javeria.posts.R;
import com.javeria.posts.model.Post;

import java.util.ArrayList;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {


    ArrayList<Post>     mPosts = new ArrayList<>();
    OnPostClickListener mOnPostClickListener;

    private String TAG = PostRecyclerAdapter.class.getSimpleName();

    public PostRecyclerAdapter(ArrayList<Post> posts, OnPostClickListener onPostClickListener) {
        this.mPosts = posts;
        this.mOnPostClickListener = onPostClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_post_list, viewGroup, false);
        return new ViewHolder(view, mOnPostClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.postTextView.setText(mPosts.get(i).getmPostText());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView            postTextView;
        OnPostClickListener IonPostClickListener;
        View view;

        public ViewHolder(@NonNull View itemView, OnPostClickListener onPostClickListener) {
            super(itemView);
            view = itemView;
            postTextView = itemView.findViewById(R.id.postTextView);
            IonPostClickListener = onPostClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            view = v;
            Log.i(TAG, "position: " + getAdapterPosition());
            IonPostClickListener.onPostClick(view, getAdapterPosition());
        }
    }

    public interface OnPostClickListener {
        void onPostClick(View v, int position);
    }
}
