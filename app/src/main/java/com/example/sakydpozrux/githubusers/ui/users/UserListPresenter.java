package com.example.sakydpozrux.githubusers.ui.users;

import com.example.sakydpozrux.githubusers.model.User;

import java.util.List;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public interface UserListPresenter {
    void setView(View view);
    void getUsers(String query);
    void getUsersLastQuery();

    interface View {
        void showLoading();
        void hideLoading();
        void showError(Exception exception);

        void showUsers(List<User> items);
        void launchUserDetail(User item);
    }
}
