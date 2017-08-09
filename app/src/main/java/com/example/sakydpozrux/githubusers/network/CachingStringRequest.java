package com.example.sakydpozrux.githubusers.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


/**
 * Created by sakydpozrux on 09/08/2017.
 */

public class CachingStringRequest extends StringRequest {
    private static final String CACHE_CONTROL = "Cache-Control";

    public CachingStringRequest(int method,
                                String url,
                                Response.Listener<String> listener,
                                Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // I do this to ignore "no-cache" header in GitHub API
        // and use built-in cache from Volley.
        if (response.headers.containsKey(CACHE_CONTROL)) {
            response.headers.remove(CACHE_CONTROL);
        }

        return super.parseNetworkResponse(response);
    }
}
