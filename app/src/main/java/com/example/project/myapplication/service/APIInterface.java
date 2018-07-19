package com.example.project.myapplication.service;

import com.example.project.myapplication.CommentListActivity;
import com.example.project.myapplication.model.CommentResponse;
import com.example.project.myapplication.model.MultipleResource;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIInterface {
    @GET("/repos/crashlytics/secureudid/issues")
    Call<List<MultipleResource>> doGetIssuesList();

    @GET
    Call<List<CommentResponse>> doGetCommentList(@Url String url);
}
