package com.example.sakydpozrux.githubusers.ui.users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sakydpozrux.githubusers.R;
import com.example.sakydpozrux.githubusers.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity
    implements UsersRecyclerViewAdapter.OnItemClickListener {
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        View recyclerView = findViewById(R.id.rv_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.user_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        List<User> temporaryList = new ArrayList<User>();
        temporaryList.add(new User("sakydpozrux"));
        temporaryList.add(new User("andrzej"));
        recyclerView.setAdapter(new UsersRecyclerViewAdapter(temporaryList, this));
    }

    @Override
    public void onItemClick(User item) {
        Toast.makeText(this, "TODO: launch detail view: " + item.login, Toast.LENGTH_SHORT).show();
    }
}
