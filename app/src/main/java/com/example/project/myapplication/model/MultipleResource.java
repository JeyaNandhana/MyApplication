package com.example.project.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class MultipleResource {
    @SerializedName("body")
    public String body;
    @SerializedName("closed_at")
    public String closed_at;
    @SerializedName("assignees")
    public String[] assignees;
    @SerializedName("labels")
    public String[] labels;
    @SerializedName("state")
    public String state;
    @SerializedName("author_association")
    public String author_association;
    @SerializedName("assignee")
    public String assignee;
    @SerializedName("number")
    public String number;
    @SerializedName("url")
    public String url;
    @SerializedName("node_id")
    public String node_id;
    @SerializedName("milestone")
    public String milestone;
    @SerializedName("html_url")
    public String html_url;
    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public String title;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("repository_url")
    public String repository_url;
    @SerializedName("pull_request")
    public Pull_request pull_request;
    @SerializedName("comments_url")
    public String comments_url;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("events_url")
    public String events_url;
    @SerializedName("labels_url")
    public String labels_url;
    @SerializedName("locked")
    public String locked;
    @SerializedName("user")
    public User user;
    @SerializedName("comments")
    public String comments;

    public class User {
        @SerializedName("received_events_url")
        public String received_events_url;
        @SerializedName("organizations_url")
        public String organizations_url;
        @SerializedName("avatar_url")
        public String avatar_url;
        @SerializedName("gravatar_id")
        public String gravatar_id;
        @SerializedName("gists_url")
        public String gists_url;
        @SerializedName("starred_url")
        public String starred_url;
        @SerializedName("site_admin")
        public String site_admin;
        @SerializedName("type")
        public String type;
        @SerializedName("url")
        public String url;
        @SerializedName("node_id")
        public String node_id;
        @SerializedName("id")
        public String id;
        @SerializedName("html_url")
        public String html_url;
        @SerializedName("following_url")
        public String following_url;
        @SerializedName("events_url")
        public String events_url;
        @SerializedName("login")
        public String login;
        @SerializedName("subscriptions_url")
        public String subscriptions_url;
        @SerializedName("repos_url")
        public String repos_url;
        @SerializedName("followers_url")
        public String followers_url;
    }

    public class Pull_request {
        @SerializedName("patch_url")
        public String patch_url;
        @SerializedName("diff_url")
        public String diff_url;
        @SerializedName("html_url")
        public String html_url;
        @SerializedName("url")
        public String url;
    }
}