package com.example.sakydpozrux.githubusers.persistence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sakydpozrux on 09/08/2017.
 */

public class AppPreferences {
    public static final String KEY_DEFAULT_QUERY = "DEFAULT_QUERY";

    private SharedPreferences mPreferences;

    private static final String NAME_APP_SHARED_PREFERENCES = "APP_SHARED_PREFERENCES";

    public AppPreferences(Context context) {
        mPreferences =
                context.getSharedPreferences(NAME_APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean hasValue(String key) {
        return mPreferences.getString(key, null) != null;
    }

    public String getValue(String key) {
        return mPreferences.getString(key, null);
    }
}
