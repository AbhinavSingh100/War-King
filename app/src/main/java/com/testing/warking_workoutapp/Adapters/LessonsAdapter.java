package com.testing.warking_workoutapp.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.testing.warking_workoutapp.Activity.WorkoutActivity;
import com.testing.warking_workoutapp.Domain.Lesson;
import com.testing.warking_workoutapp.databinding.LessonsViewholderBinding;
import com.testing.warking_workoutapp.databinding.WorkoutViewholderBinding;

import java.util.ArrayList;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.ViewHolder> {

    private final ArrayList<Lesson> lessons;
    private Context context;
    public LessonsAdapter(ArrayList<Lesson> lessons) {
        this.lessons = lessons;

    }
    @NonNull
    @Override
    public LessonsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LessonsViewholderBinding binding = LessonsViewholderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new LessonsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsAdapter.ViewHolder holder, int position) {
        holder.binding.lessonTitleTxt.setText(lessons.get(position).getTitle());
        holder.binding.lessonDurationTxt.setText(lessons.get(position).getDuration());
        int resID = context.getResources().getIdentifier(lessons.get(position).getPicPath(),"drawable",context.getPackageName());
        Glide.with(holder.itemView.getContext()).load(resID).into(holder.binding.lessonPic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentApp = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+lessons.get(position).getLink()));
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?"+lessons.get(position).getLink()));
                try {
                    context.startActivity(intentApp);
                }catch (ActivityNotFoundException e) {
                    context.startActivity(intentWeb);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        LessonsViewholderBinding binding;
        public ViewHolder(LessonsViewholderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
