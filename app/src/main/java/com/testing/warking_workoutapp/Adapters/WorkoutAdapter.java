package com.testing.warking_workoutapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.testing.warking_workoutapp.Activity.WorkoutActivity;
import com.testing.warking_workoutapp.Domain.Workout;
import com.testing.warking_workoutapp.databinding.WorkoutViewholderBinding;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private final ArrayList<Workout> workouts;
    private Context context;

    public WorkoutAdapter(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        WorkoutViewholderBinding binding = WorkoutViewholderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.ViewHolder holder, int position) {
        holder.binding.title.setText(workouts.get(position).getTitle());
        int resID = context.getResources().getIdentifier(workouts.get(position).getPicPath(),"drawable", context.getPackageName());
        Glide.with(holder.itemView.getContext()).load(resID).into(holder.binding.workoutPic);

        holder.binding.duration.setText(workouts.get(position).getDurationAll());
        holder.binding.exerciseCount.setText(workouts.get(position).getLessons().size() +" Exercises");
        holder.binding.kcalCount.setText(workouts.get(position).getKcal() + " kcal");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WorkoutActivity.class);
                intent.putExtra("object", workouts.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        WorkoutViewholderBinding binding;
        public ViewHolder(WorkoutViewholderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
