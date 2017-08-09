package com.example.sakydpozrux.githubusers.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.android.volley.Cache;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.sakydpozrux.githubusers.app.GitHubUsersApp;
import com.example.sakydpozrux.githubusers.model.Repository;
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
    @Inject ConnectivityManager mConnectivityManager;
    @Inject RequestQueue requestQueue;

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

    private static final String JSON_KEY_LOGIN = "login";
    private static final String JSON_KEY_PROFILE_URL = "html_url";
    private static final String JSON_KEY_REPOS_URL = "repos_url";

    private static final String JSON_KEY_REPO_NAME = "name";

    public GitHubApi(Context context) {
        ((GitHubUsersApp)context).getAppComponent().inject(this);
    }

    @Override
    public void doUsersQuery(String query,
                             Response.Listener<String> responseListener,
                             Response.ErrorListener errorListener) {
        Uri uri = buildUsersQueryUri(query);
        doQuery(responseListener, errorListener, uri);
    }

    @Override
    public void doReposQuery(String reposUrl,
                             Response.Listener<String> responseListener,
                             Response.ErrorListener errorListener) {
        Uri uri = buildUserReposUri(reposUrl);
        doQuery(responseListener, errorListener, uri);
    }

    private void doQuery(Response.Listener<String> responseListener,
                         Response.ErrorListener errorListener,
                         Uri uri) {
        if (isNetworkAvailable()) {
            doNetworkQuery(responseListener, errorListener, uri);
        } else {
            doCachedQuery(responseListener, errorListener, uri);
        }
    }

    private void doNetworkQuery(Response.Listener<String> responseListener,
                                Response.ErrorListener errorListener,
                                Uri uri) {
        requestQueue.add(new CachingStringRequest(
                Request.Method.GET,
                uri.toString(),
                responseListener,
                errorListener));
    }

    private void doCachedQuery(Response.Listener<String> responseListener,
                               Response.ErrorListener errorListener,
                               Uri uri) {
        if (!isResponseInCache(uri)) {
            errorListener.onErrorResponse(new NoConnectionError());
            return;
        }

        String cachedResponse = getResponseFromCache(uri);
        responseListener.onResponse(cachedResponse);
        errorListener.onErrorResponse(new NoConnectionError());
    }

    @Override
    public List<User> parseUsersResponse(String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
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

    @Override
    public List<Repository> parseReposResponse(String jsonArrayString) throws JSONException {
        JSONArray json =  new JSONArray(jsonArrayString);

        List<Repository> items = new LinkedList<>();
        for (int i = 0; i < json.length(); ++i) {
            JSONObject repo = json.getJSONObject(i);
            String name = repo.getString(JSON_KEY_REPO_NAME);
            items.add(new Repository(name));
        }

        return items;
    }

    private Uri buildUsersQueryUri(String query) {
        return BASE_URI.buildUpon().appendQueryParameter(API_QUERY_PARAM, query).build();
    }

    private Uri buildUserReposUri(String reposUrl) {
        return Uri.parse(reposUrl).buildUpon()
                .appendQueryParameter(API_PER_PAGE_PARAM, API_PER_PAGE_MAX).build();
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean isResponseInCache(Uri uri) {
        Cache cache = requestQueue.getCache();
        return cache.get(uri.toString()) != null;
    }

    private String getResponseFromCache(Uri uri) {
        Cache cache = requestQueue.getCache();
        Cache.Entry entry = cache.get(uri.toString());
        return new String(entry.data);
    }
}
