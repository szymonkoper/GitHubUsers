package com.example.sakydpozrux.githubusers.ui.user;

import com.example.sakydpozrux.githubusers.model.Repository;

import java.util.List;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public interface UserDetailPresenter {
    void setView(View view);
    void getRepos(String url);

    interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);

        void showRepositories(List<Repository> items);
    }
}
