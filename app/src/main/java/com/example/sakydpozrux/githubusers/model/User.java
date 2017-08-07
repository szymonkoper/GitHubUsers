package com.example.sakydpozrux.githubusers.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class User implements Parcelable {
    public final String login;
    public final String profileUrl;
    public final String reposUrl;

    public User(String login, String profileUrl, String reposUrl) {
        this.login = login;
        this.profileUrl = profileUrl;
        this.reposUrl = reposUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeString(this.profileUrl);
        dest.writeString(this.reposUrl);
    }

    protected User(Parcel in) {
        this.login = in.readString();
        this.profileUrl = in.readString();
        this.reposUrl = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
