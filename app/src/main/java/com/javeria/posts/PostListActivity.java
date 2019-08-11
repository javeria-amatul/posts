package com.javeria.posts;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.javeria.posts.adapter.PostRecyclerAdapter;
import com.javeria.posts.model.Post;
import com.javeria.posts.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.Calendar;

public class PostListActivity extends AppCompatActivity implements PostRecyclerAdapter.OnPostClickListener {

    //UI
    private RecyclerView        recyclerView;
    private PostRecyclerAdapter postRecyclerAdapter;
    private PopupWindow         popWindow;

    //conts
    private static final String TAG = PostListActivity.class.getCanonicalName();

    //vars
    ArrayList<Post> mPosts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewPosts);
        initRecyclerView();
        insertPosts();
    }

    public void insertPosts() {
        for (int i = 0; i < 100; i++) {
            Post post = new Post();
            post.setmPostText("Post #" + i);
            mPosts.add(post);
        }
        postRecyclerAdapter.notifyDataSetChanged();

    }

    public void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        postRecyclerAdapter = new PostRecyclerAdapter(mPosts, this);
        recyclerView.setAdapter(postRecyclerAdapter);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(20);
        recyclerView.addItemDecoration(itemDecorator);
    }

    @Override
    public void onPostClick(View v, int position) {
        Log.i(TAG, "Made it");
        popUpInitialization(v);
    }

    public void popUpInitialization(View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View inflatedView = layoutInflater.inflate(R.layout.popup_layout, null, false);

        ListView listView = inflatedView.findViewById(R.id.commentsListView);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        setSimpleList(listView);

        popWindow = new PopupWindow(inflatedView, size.x - 50, size.y - 250, true);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_bg));
        popWindow.showAtLocation(v, Gravity.BOTTOM, 0, 150);  // 0 - X postion and 150 - Y position
        popWindow.setAnimationStyle(R.style.Animation_Design_BottomSheetDialog);

    }

    void setSimpleList(ListView listView) {

        ArrayList<String> contactsList = new ArrayList<String>();

        for (int index = 0; index < 10; index++) {
            contactsList.add("I am @ index " + index + " today " + Calendar.getInstance().getTime().toString());
        }

        listView.setAdapter(new ArrayAdapter<String>(PostListActivity.this,
                R.layout.comments_list_item, R.id.commentsTextView, contactsList));
    }

}
