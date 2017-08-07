package com.example.sakydpozrux.githubusers.ui.users;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.example.sakydpozrux.githubusers.R;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserListActivity extends AppCompatActivity
    implements UsersRecyclerViewAdapter.OnItemClickListener {
    @Inject SearchManager mSearchManager;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ((GitHubUsersApp)getApplication()).getAppComponent().inject(this);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_list, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(mSearchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "TODO: do query: " + query, Toast.LENGTH_LONG).show();
        }
    }
}
