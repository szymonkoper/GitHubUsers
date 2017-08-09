package com.example.sakydpozrux.githubusers.ui.users;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.network.Api;
import com.example.sakydpozrux.githubusers.persistence.AppPreferences;

import org.json.JSONException;

import javax.inject.Inject;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class UserListPresenterImpl implements UserListPresenter {
    @Inject Api mApi;
    @Inject AppPreferences mPreferences;
    private Context mContext;

    private UserListPresenter.View view;

    public UserListPresenterImpl(Context context) {
        mContext = context;
        ((GitHubUsersApp)mContext).getAppComponent().inject(this);
    }

    @Override
    public void setView(UserListPresenter.View view) {
        this.view = view;
    }

    @Override
    public void getUsers(String query) {
        view.showLoading();
        String cleanedQuery = query.trim().toLowerCase();
        mPreferences.put(AppPreferences.KEY_DEFAULT_QUERY, cleanedQuery);
        mApi.doUsersQuery(cleanedQuery, getResponseListener(), getErrorListener());
    }

    @Override
    public void getUsersLastQuery() {
        if (mPreferences.hasValue(AppPreferences.KEY_DEFAULT_QUERY)) {
            getUsers(mPreferences.getValue(AppPreferences.KEY_DEFAULT_QUERY));
        }
    }

    @NonNull
    private Response.Listener<String> getResponseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                view.hideLoading();

                try {
                    view.showUsers(mApi.parseUsersResponse(response));
                } catch (JSONException error) {
                    view.showError(error);
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
                view.showError(error);
            }
        };
    }
}
