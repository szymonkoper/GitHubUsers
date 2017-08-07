package com.example.sakydpozrux.githubusers.ui.users;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.network.Api;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class UserListPresenterImpl implements UserListPresenter {
    @Inject
    Api api;

    private UserListPresenter.View view;

    public UserListPresenterImpl(Context context) {
        ((GitHubUsersApp)context).getAppComponent().inject(this);
    }

    @Override
    public void setView(UserListPresenter.View view) {
        this.view = view;
    }

    @Override
    public void getUsers(String query) {
        view.showLoading();
        api.doUsersQuery(query, getResponseListener(), getErrorListener());
    }

    @NonNull
    private Response.Listener<JSONObject> getResponseListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                view.hideLoading();

                try {
                    view.showUsers(api.parseUsersResponse(response));
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
