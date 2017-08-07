package com.example.sakydpozrux.githubusers.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

import com.example.sakydpozrux.githubusers.R;
import com.example.sakydpozrux.githubusers.model.User;

public class UserDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            User user = intent.getParcelableExtra(UserDetailFragment.BUNDLE_KEY_USER);

            Bundle bundle = new Bundle();
            bundle.putParcelable(UserDetailFragment.BUNDLE_KEY_USER, user);

            UserDetailFragment userDetailFragment = new UserDetailFragment();
            userDetailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_user_detail, userDetailFragment)
                    .commit();
        }
    }
}
