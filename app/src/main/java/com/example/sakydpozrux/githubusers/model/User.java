package com.example.sakydpozrux.githubusers.model;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class User {
    public final String login;
    public final String profileUrl;
    public final String reposUrl;

    public User(String login, String profileUrl, String reposUrl) {
        this.login = login;
        this.profileUrl = profileUrl;
        this.reposUrl = reposUrl;
    }
}
