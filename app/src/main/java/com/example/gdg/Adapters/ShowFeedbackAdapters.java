package com.example.gdg.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdg.Models.ShowFeedbackModel;
import com.example.gdg.R;

import java.util.ArrayList;

public class ShowFeedbackAdapters extends RecyclerView.Adapter<ShowFeedbackAdapters.myViewHolder> {
    Context context;
    ArrayList<ShowFeedbackModel> list;

    public ShowFeedbackAdapters (Context context, ArrayList<ShowFeedbackModel> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ShowFeedbackAdapters.myViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext( ) ).inflate( R.layout.showfeedbacksingle, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull ShowFeedbackAdapters.myViewHolder holder, int position) {
        ShowFeedbackModel showFeedbackModel = list.get(position);
        holder.email.setText(showFeedbackModel.getName());
        holder.review.setText(showFeedbackModel.getReview().toUpperCase());
        holder.bar.setRating( Float.parseFloat( String.valueOf( showFeedbackModel.getRating() ) ));
    }

    @Override
    public int getItemCount ( ) {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView email,review;
        RatingBar bar;

        public myViewHolder (@NonNull View itemView) {
            super( itemView );

            email = itemView.findViewById(R.id.user_email);
            review = itemView.findViewById(R.id.user_review);
            bar = itemView.findViewById(R.id.ratingBar);
            bar.setIsIndicator(true);
        }
    }
}
