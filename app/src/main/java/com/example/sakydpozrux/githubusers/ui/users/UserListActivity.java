package com.example.sakydpozrux.githubusers.ui.users;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sakydpozrux.githubusers.R;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.model.User;
import com.example.sakydpozrux.githubusers.ui.user.UserDetailActivity;
import com.example.sakydpozrux.githubusers.ui.user.UserDetailFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListActivity extends AppCompatActivity
    implements UsersRecyclerViewAdapter.OnItemClickListener, UserListPresenter.View {
    @Inject UserListPresenter mPresenter;
    @Inject SearchManager mSearchManager;

    @BindView(R.id.rv_list) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private UsersRecyclerViewAdapter mAdapter;
    private boolean mTwoPane;

    private static final String KEY_USERS_LIST = "KEY_USERS_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ((GitHubUsersApp)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        List<User> users;
        if (savedInstanceState == null) {
            users = new ArrayList<>();
        } else {
            users = savedInstanceState.getParcelableArrayList(KEY_USERS_LIST);
        }

        setupRecyclerView(users);

        mPresenter.setView(this);

        if (findViewById(R.id.container_user_detail) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }
    }

    private void setupRecyclerView(List<User> users) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UsersRecyclerViewAdapter(users, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(User item) {
        launchUserDetail(item);
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
            mPresenter.getUsers(query);
        }
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUsers(List<User> items) {
        mAdapter = new UsersRecyclerViewAdapter(items, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();

        if (mTwoPane && !items.isEmpty()) {
            User first = items.get(0);
            launchUserDetail(first);
        }
    }

    @Override
    public void launchUserDetail(User item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(UserDetailFragment.BUNDLE_KEY_USER, item);

        if (mTwoPane) {
            UserDetailFragment userDetailFragment = new UserDetailFragment();
            userDetailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_user_detail, userDetailFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, UserDetailActivity.class);
            intent.putExtra(UserDetailFragment.BUNDLE_KEY_USER, item);

            startActivity(intent);
        }
    }
}
