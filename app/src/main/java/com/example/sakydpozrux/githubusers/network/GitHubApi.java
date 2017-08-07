package com.example.sakydpozrux.githubusers.network;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class GitHubApi implements Api {
    @Inject
    RequestQueue requestQueue;

    private static final String GITHUB_API_URL = "https://api.github.com";
    private static final String API_PATH_SEARCH = "search";
    private static final String API_PATH_USERS = "users";
    private static final String API_QUERY_PARAM = "q";
    private static final String API_PER_PAGE_PARAM = "per_page";
    private static final String API_PER_PAGE_MAX = "100";

    private static final Uri BASE_URI = Uri.parse(GITHUB_API_URL).buildUpon()
            .appendPath(API_PATH_SEARCH)
            .appendPath(API_PATH_USERS)
            .build();

    private static final String JSON_KEY_ITEMS = "items";

    private static final String JSON_KEY_ID = "id";
    private static final String JSON_KEY_LOGIN = "login";
    private static final String JSON_KEY_PROFILE_URL = "html_url";
    private static final String JSON_KEY_REPOS_URL = "repos_url";

    public GitHubApi(Context context) {
        ((GitHubUsersApp)context).getAppComponent().inject(this);
    }

    @Override
    public void doUsersQuery(String query,
                             Response.Listener<JSONObject> responseListener,
                             Response.ErrorListener errorListener) {
        Uri uri = BASE_URI.buildUpon().appendQueryParameter(API_QUERY_PARAM, query).build();

        requestQueue.add(
                new JsonObjectRequest(
                        Request.Method.GET,
                        uri.toString(),
                        null,
                        responseListener,
                        errorListener));
    }

    @Override
    public List<User> parseUsersResponse(JSONObject json) throws JSONException {
        JSONArray array = json.getJSONArray(JSON_KEY_ITEMS);

        List<User> items = new LinkedList<>();
        for (int i = 0; i < array.length(); ++i) {
            JSONObject user = array.getJSONObject(i);

            String login = user.getString(JSON_KEY_LOGIN);
            String profileUrl = user.getString(JSON_KEY_PROFILE_URL);
            String reposUrl = user.getString(JSON_KEY_REPOS_URL);

            items.add(new User(login, profileUrl, reposUrl));
        }

        return items;
    }
}
