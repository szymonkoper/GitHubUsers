package com.example.sakydpozrux.githubusers.dagger;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.sakydpozrux.githubusers.network.Api;
import com.example.sakydpozrux.githubusers.network.GitHubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public RequestQueue provideRequestQueue(Context context) {
        return Volley.newRequestQueue(context);
    }

    @Provides
    @Singleton
    public Api provideApi(Context context) {
        return new GitHubApi(context);
    }
}
