package com.example.project.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class CommentResponse {
    @SerializedName("id")
    public String id;
    @SerializedName("issue_url")
    public String issue_url;
    @SerializedName("html_url")
    public String html_url;
    @SerializedName("body")
    public String body;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("author_association")
    public String author_association;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("user")
    public MultipleResource.User user;
    @SerializedName("url")
    public String url;
    @SerializedName("node_id")
    public String node_id;
}
