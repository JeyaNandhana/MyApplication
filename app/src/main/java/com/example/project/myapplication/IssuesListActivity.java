package com.example.project.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.example.project.myapplication.model.MultipleResource;
import com.example.project.myapplication.service.APIClient;
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

public class IssuesListActivity extends AppCompatActivity {
    private RecyclerView issueRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private APIInterface apiInterface;
    private List<MultipleResource> multipleResource;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        initViews();
    }

    private void initViews(){
        issueRecyclerView = (RecyclerView)findViewById(R.id.issue_recycler_view);
        issueRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        issueRecyclerView.setLayoutManager(layoutManager);
        if(CommonFunc.isInternetAvailable(IssuesListActivity.this)) {
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
        Call<List<MultipleResource>> call = apiInterface.doGetIssuesList();
        call.enqueue(new Callback<List<MultipleResource>>() {
            @Override
            public void onResponse(Call<List<MultipleResource>> call, Response<List<MultipleResource>> response) {
                multipleResource = response.body();
                if (progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                //data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                mAdapter = new IssueAdapter(IssuesListActivity.this,multipleResource);
                issueRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<MultipleResource>> call, Throwable t) {
                Log.d("Error",t.getMessage());
                if (progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });
    }
}