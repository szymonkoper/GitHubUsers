package com.example.sakydpozrux.githubusers.ui.user;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.network.Api;

import org.json.JSONException;

import javax.inject.Inject;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class UserDetailPresenterImpl implements UserDetailPresenter {
    @Inject Api mApi;
    private View mView;

    public UserDetailPresenterImpl(Context context) {
        ((GitHubUsersApp)context).getAppComponent().inject(this);
    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }

    @Override
    public void getRepos(String url) {
        mView.showLoading();
        mApi.doReposQuery(url, getResponseListener(), getErrorListener());

    }

    @NonNull
    private Response.Listener<String> getResponseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mView.hideLoading();

                try {
                    mView.showRepositories(mApi.parseReposResponse(response));
                } catch (JSONException error) {
                    mView.showError(error);
                }
            }
        };
    }

    @NonNull
    private Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mView.hideLoading();
                mView.showError(error);
            }
        };
    }
}
