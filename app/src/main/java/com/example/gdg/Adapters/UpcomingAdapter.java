package com.example.gdg.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gdg.Models.UpcomingModel;
import com.example.gdg.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.myViewHolder> {

    Context context;
    ArrayList<UpcomingModel> list;

    public UpcomingAdapter (Context context, ArrayList<UpcomingModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UpcomingAdapter.myViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext( ) ).inflate( R.layout.upcoming_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull UpcomingAdapter.myViewHolder holder, int position) {
        UpcomingModel upcomingModel = list.get(position);
        holder.name.setText("Name : "+ upcomingModel.getName());
        holder.date.setText("Date: "+upcomingModel.getDate());
        holder.venue.setText("Location: "+upcomingModel.getVenue());
        Glide.with(holder.circleimg.getContext()).load(upcomingModel.getUrl()).into(holder.circleimg);
    }

    @Override
    public int getItemCount ( ) {
        return list.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleimg;
        TextView name,date,venue;

        public myViewHolder (@NonNull View itemView) {
            super( itemView );

            circleimg =itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.txtname);
            date = itemView.findViewById(R.id.txtdate);
            venue = itemView.findViewById(R.id.txtvenue);

        }
    }
}
