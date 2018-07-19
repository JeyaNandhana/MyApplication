package com.example.project.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.project.myapplication.model.CommentResponse;
import com.example.project.myapplication.model.MultipleResource;
import com.example.project.myapplication.service.APIInterface;
import com.example.project.myapplication.util.CommonFunc;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentListActivity extends AppCompatActivity {
    private RecyclerView commentRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private APIInterface apiInterface;
    private List<CommentResponse> commentResponse;
    private ProgressDialog progressDialog;
    private String commentUrl="";
    private TextView tv_no_comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent=getIntent();
        commentUrl=intent.getStringExtra("comment_url");
        initViews();
    }

    private void initViews(){
        commentRecyclerView = (RecyclerView)findViewById(R.id.comment_recycler_view);
        tv_no_comments=(TextView)findViewById(R.id.tv_no_comments);
        commentRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        commentRecyclerView.setLayoutManager(layoutManager);
        if(CommonFunc.isInternetAvailable(CommentListActivity.this)) {
            loadJSON();
        }else {
            displayErrorMessageDialog(getString(R.string.error_no_internet));
        }
    }

    private void displaySuccessMessageDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.setMessage(message);
    }

    private void displayErrorMessageDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        progressDialog.show();
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.setMessage(message);

    }
    private void loadJSON(){
        displaySuccessMessageDialog(getString(R.string.loading));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(APIInterface.class);
        Call<List<CommentResponse>> call = apiInterface.doGetCommentList(commentUrl);
        call.enqueue(new Callback<List<CommentResponse>>() {
            @Override
            public void onResponse(Call<List<CommentResponse>> call, Response<List<CommentResponse>> response) {
                commentResponse = response.body();
                if (progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                if(commentResponse.size()>0) {
                    tv_no_comments.setVisibility(View.GONE);
                    commentRecyclerView.setVisibility(View.VISIBLE);
                    //data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                    mAdapter = new CommentAdapter(CommentListActivity.this, commentResponse);
                    commentRecyclerView.setAdapter(mAdapter);
                }else{
                    tv_no_comments.setVisibility(View.VISIBLE);
                    commentRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<CommentResponse>> call, Throwable t) {
                Log.d("Error",t.getMessage());
                if (progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });
    }
}
