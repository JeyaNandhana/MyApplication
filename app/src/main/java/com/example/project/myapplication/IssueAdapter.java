package com.example.project.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project.myapplication.model.MultipleResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {
    List<MultipleResource> multipleResource;
    Context context;
    public IssueAdapter(Context context,List<MultipleResource> multipleResource) {
        this.multipleResource=multipleResource;
        this.context=context;
    }

    @Override
    public IssueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issues_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IssueAdapter.ViewHolder holder, final int position) {
        holder.tv_name.setText(multipleResource.get(position).user.login);
        holder.tv_issue_title.setText(multipleResource.get(position).title);
        holder.tv_desc.setText(multipleResource.get(position).body);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat output = new SimpleDateFormat("MM-dd-yyyy");
        Date d = null;
        try {
            d = sdf.parse(multipleResource.get(position).updated_at);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = output.format(d);
        holder.tv_updated_date.setText(formattedTime);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);
        Glide.with(context).load(multipleResource.get(position).user.avatar_url).apply(options).into(holder.iv_avatar);
        holder.cvIssues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,CommentListActivity.class);
                intent.putExtra("comment_url",multipleResource.get(position).comments_url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return multipleResource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_issue_title,tv_desc,tv_name,tv_updated_date;
        private CardView cvIssues;
        ImageView iv_avatar;
        public ViewHolder(View view) {
            super(view);

            tv_issue_title = (TextView)view.findViewById(R.id.tv_issue_title);
            tv_desc = (TextView)view.findViewById(R.id.tv_desc);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_updated_date=(TextView)view.findViewById(R.id.tv_updated_date);
            iv_avatar=(ImageView)view.findViewById(R.id.iv_avatar);
            cvIssues=(CardView)view.findViewById(R.id.cv_issues);
        }
    }
}
