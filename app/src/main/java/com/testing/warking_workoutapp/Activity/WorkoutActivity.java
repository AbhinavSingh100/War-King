package com.testing.warking_workoutapp.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.testing.warking_workoutapp.Adapters.LessonsAdapter;
import com.testing.warking_workoutapp.Domain.Workout;
import com.testing.warking_workoutapp.R;
import com.testing.warking_workoutapp.databinding.ActivityWorkoutBinding;

public class WorkoutActivity extends AppCompatActivity {
ActivityWorkoutBinding binding;
private Workout workout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWorkoutData();
        setVariables();

    }
    void getWorkoutData(){
        workout = (Workout) getIntent().getSerializableExtra("object");
    }
    void setVariables(){
        int resID = getResources().getIdentifier(workout.getPicPath(),"drawable",getPackageName());
        Glide.with(this).load(resID).into(binding.workoutPic);
        binding.titleTxt.setText(workout.getTitle());
        binding.descriptionTxt.setText(workout.getDescription());
        binding.durationTxt.setText(workout.getDurationAll());
        binding.kcalCountTxt.setText(workout.getKcal()+" kcal");
        binding.exerciseCountTxt.setText(workout.getLessons().size()+ " Exercises");
        binding.exercisesView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.exercisesView.setAdapter(new LessonsAdapter(workout.getLessons()));

    }
}