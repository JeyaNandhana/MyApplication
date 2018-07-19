package com.example.project.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project.myapplication.model.CommentResponse;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    List<CommentResponse> commentResponseList;
    Context context;
    public CommentAdapter(Context context,List<CommentResponse> commentResponseList) {
        this.commentResponseList=commentResponseList;
        this.context=context;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(commentResponseList.get(position).user.login);
        holder.tv_desc.setText(commentResponseList.get(position).body);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);
        Glide.with(context).load(commentResponseList.get(position).user.avatar_url).apply(options).into(holder.iv_avatar);
    }

    @Override
    public int getItemCount() {
        return commentResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_desc,tv_name;
        ImageView iv_avatar;
        public ViewHolder(View view) {
            super(view);

            tv_desc = (TextView)view.findViewById(R.id.tv_desc);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            iv_avatar=(ImageView)view.findViewById(R.id.iv_avatar);
        }
    }
}

