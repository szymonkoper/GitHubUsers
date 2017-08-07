package com.example.sakydpozrux.githubusers.ui.user;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.network.Api;

import org.json.JSONArray;
import org.json.JSONException;

import javax.inject.Inject;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class UserDetailPresenterImpl implements UserDetailPresenter {
    @Inject Api api;
    private View view;

    public UserDetailPresenterImpl(Context context) {
        ((GitHubUsersApp)context).getAppComponent().inject(this);
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void getRepos(String url) {
        view.showLoading();
        api.doReposQuery(url, getResponseListener(), getErrorListener());

    }

    @NonNull
    private Response.Listener<JSONArray> getResponseListener() {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                view.hideLoading();

                try {
                    view.showRepositories(api.parseReposResponse(response));
                } catch (JSONException error) {
                    view.showError(error.getLocalizedMessage());
                }
            }
        };
    }

    @NonNull
    private Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.hideLoading();
                view.showError(error.getLocalizedMessage());
            }
        };
    }
}
