package com.example.sakydpozrux.githubusers.dagger;

import android.app.Application;
import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;

import com.example.sakydpozrux.githubusers.persistence.AppPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public AppPreferences provideAppPreferences(Context context) {
        return new AppPreferences(context);
    }

    @Provides
    @Singleton
    public SearchManager provideSearchManager(Context context) {
        return (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
    }

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
