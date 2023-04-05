package com.example.gdg.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gdg.Models.PastModel;
import com.example.gdg.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PastAdapter extends RecyclerView.Adapter<PastAdapter.myViewHolder> {

    Context context;
    ArrayList<PastModel> list;

    public PastAdapter (Context context, ArrayList<PastModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PastAdapter.myViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext( ) ).inflate( R.layout.pastevents_item, parent, false);
        return new myViewHolder( view);
    }

    @Override
    public void onBindViewHolder (@NonNull PastAdapter.myViewHolder holder, int position) {
        PastModel pastModel = list.get(position);
        holder.pastname.setText("Name : "+ pastModel.getName());
        holder.pastdate.setText("Date: "+pastModel.getDate());
        holder.pastvenue.setText("Location: "+pastModel.getVenue());
        Glide.with( holder.pastcircleimg.getContext()).load( pastModel.getUrl()).into( holder.pastcircleimg);
    }

    @Override
    public int getItemCount ( ) {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView pastcircleimg;
        TextView pastname,pastdate,pastvenue;
        public myViewHolder (@NonNull View itemView) {
            super( itemView );

            pastcircleimg = itemView.findViewById(R.id.pastprofile_image);
            pastname = itemView.findViewById(R.id.pasttxtname);
            pastdate = itemView.findViewById(R.id.pasttxtdate);
            pastvenue = itemView.findViewById(R.id.pasttxtvenue);

        }
    }
}
