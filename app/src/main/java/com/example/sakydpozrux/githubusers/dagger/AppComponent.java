package com.example.sakydpozrux.githubusers.dagger;

import com.example.sakydpozrux.githubusers.ui.users.UserListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(UserListActivity target);
}
