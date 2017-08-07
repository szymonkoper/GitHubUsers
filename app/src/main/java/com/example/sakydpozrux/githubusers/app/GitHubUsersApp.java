package com.example.sakydpozrux.githubusers.app;

import android.app.Application;

import com.example.sakydpozrux.githubusers.dagger.AppComponent;
import com.example.sakydpozrux.githubusers.dagger.AppModule;
import com.example.sakydpozrux.githubusers.dagger.DaggerAppComponent;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class GitHubUsersApp extends Application {
    protected AppComponent appComponent;

    public AppComponent getAppComponent() { return appComponent; }

    protected AppComponent initDagger(GitHubUsersApp application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }
}
