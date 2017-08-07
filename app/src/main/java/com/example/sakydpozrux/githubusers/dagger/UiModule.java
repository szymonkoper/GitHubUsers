package com.example.sakydpozrux.githubusers.dagger;

import android.content.Context;

import com.example.sakydpozrux.githubusers.ui.users.UserListPresenter;
import com.example.sakydpozrux.githubusers.ui.users.UserListPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

@Module
public class UiModule {
    @Provides
    @Singleton
    public UserListPresenter provideUsersPresenter(Context context) {
        return new UserListPresenterImpl(context);
    }
}
