package com.example.sakydpozrux.githubusers.model;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class Repository {
    public final String name;

    public Repository(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
