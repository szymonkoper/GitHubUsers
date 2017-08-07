package com.example.sakydpozrux.githubusers.dagger;

import com.example.sakydpozrux.githubusers.network.GitHubApi;
import com.example.sakydpozrux.githubusers.ui.users.UserListActivity;
import com.example.sakydpozrux.githubusers.ui.users.UserListPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, UiModule.class})
public interface AppComponent {
    void inject(UserListActivity target);
    void inject(UserListPresenterImpl target);
    void inject(GitHubApi target);
}
