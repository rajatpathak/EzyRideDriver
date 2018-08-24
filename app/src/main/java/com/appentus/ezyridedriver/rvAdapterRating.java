package com.appentus.ezyridedriver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class rvAdapterRating extends RecyclerView.Adapter<rvAdapterRating.MyViewHolder> {

    protected List<RatingModel> commentList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, time, comment;
        RatingBar rating;

        public MyViewHolder(View view) {
            super(view);
            name= (TextView) view.findViewById(R.id.tvName);
            rating= (RatingBar) view.findViewById(R.id.tvRating);
            time= (TextView) view.findViewById(R.id.tvTime);
            comment= (TextView) view.findViewById(R.id.tvComment);
        }
    }


    public rvAdapterRating(List<RatingModel> commentList) {
        this.commentList = commentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RatingModel ratingModel= commentList.get(position);
        holder.name.setText(ratingModel.getName());
//        holder.rating.setText(ratingModel.getRatings());
        holder.time.setText(ratingModel.getTime());
        holder.comment.setText(ratingModel.getComment());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}