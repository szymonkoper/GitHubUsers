package com.example.sakydpozrux.githubusers.ui.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sakydpozrux.githubusers.R;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.model.Repository;
import com.example.sakydpozrux.githubusers.model.User;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailFragment extends Fragment implements UserDetailPresenter.View {
    @Inject UserDetailPresenter mPresenter;

    @BindView(R.id.tv_login) TextView mTextLogin;
    @BindView(R.id.tv_profile_url) TextView mTextProfileUrl;
    @BindView(R.id.tv_repositories) TextView mTextRepositories;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    public static final String BUNDLE_KEY_USER = "BUNDLE_KEY_USER";

    private User mUser;
    public UserDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((GitHubUsersApp)getActivity().getApplication()).getAppComponent().inject(this);

        mUser = getArguments().getParcelable(BUNDLE_KEY_USER);
        mPresenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_detail, container, false);
        ButterKnife.bind(this, rootView);

        mTextLogin.setText(mUser.login);
        mTextProfileUrl.setText(mUser.profileUrl);

        mPresenter.getRepos(mUser.reposUrl);

        return rootView;
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRepositories(List<Repository> items) {
        final String DELIMITER = "\n";
        String concatenated = TextUtils.join(DELIMITER, items);
        mTextRepositories.setText(concatenated);
    }
}
