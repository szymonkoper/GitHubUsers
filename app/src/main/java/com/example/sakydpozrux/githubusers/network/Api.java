package com.example.sakydpozrux.githubusers.network;

import com.android.volley.Response;
import com.example.sakydpozrux.githubusers.model.Repository;
import com.example.sakydpozrux.githubusers.model.User;

import org.json.JSONException;

import java.util.List;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public interface Api {
    void doUsersQuery(String query,
                      Response.Listener<String> responseListener,
                      Response.ErrorListener errorListener);
    void doReposQuery(String reposUrl,
                      Response.Listener<String> responseListener,
                      Response.ErrorListener errorListener);

    List<User> parseUsersResponse(String jsonString) throws JSONException;
    List<Repository> parseReposResponse(String jsonArrayString) throws JSONException;
}
