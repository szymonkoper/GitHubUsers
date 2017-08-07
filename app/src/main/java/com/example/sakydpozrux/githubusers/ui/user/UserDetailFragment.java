package com.example.sakydpozrux.githubusers.ui.user;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sakydpozrux.githubusers.R;
import com.example.sakydpozrux.githubusers.dummy.DummyContent;
import com.example.sakydpozrux.githubusers.model.User;

public class UserDetailFragment extends Fragment {

    public static final String BUNDLE_KEY_USER = "BUNDLE_KEY_USER";

    private User user;

    public UserDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = getArguments().getParcelable(BUNDLE_KEY_USER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail, container, false);

        return rootView;
    }
}
