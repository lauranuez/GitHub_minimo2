package com.vogella.android.gihub_minimo2;

public class Followers {
    private String login;
    private String avatar_url;

    public Followers(String login) {
        this.login = login;
    }
    public Followers() {

    }

    public Followers(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "Followers{" +
                "name='" + login + '\'' +
                '}';
    }
}
