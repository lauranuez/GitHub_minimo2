package com.vogella.android.gihub_minimo2;

public class User {
    private String login; //id
    private String followers;
    private String following;
    private String public_repos;
    private String owner;
    private String avatar_url;

    public User(String login, String followers, String following, String public_repos, String owner) {
        this.login = login;
        this.followers = followers;
        this.following = following;
        this.public_repos = public_repos;
        this.owner = owner;
    }

    public User(String login) {
        this.login = login;
    }

    public User() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", public_repos=" + public_repos +
                '}';
    }
}
