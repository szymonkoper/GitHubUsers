package com.example.sakydpozrux.githubusers.network;

import android.content.Context;

import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.example.sakydpozrux.githubusers.R;

/**
 * Created by sakydpozrux on 09/08/2017.
 */

public abstract class NetworkErrorDescriptor {
    public static String getDescription(Context context, Exception error) {
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            return context.getString(R.string.error_could_not_connect);
        } else {
            return context.getString(R.string.error_during_load);
        }
    }
}
