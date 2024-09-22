package com.testing.warking_workoutapp.Activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.testing.warking_workoutapp.Adapters.WorkoutAdapter;
import com.testing.warking_workoutapp.Domain.Lesson;
import com.testing.warking_workoutapp.Domain.Workout;
import com.testing.warking_workoutapp.GeminiPro;
import com.testing.warking_workoutapp.ResponseCallback;
import com.testing.warking_workoutapp.databinding.ActivityMainBinding;
import com.testing.warking_workoutapp.databinding.AiDialogPopupBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;


    private String prompt = "Give a motivational quote related to fitness and hardwork along with the person who said it.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);



        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerView.setAdapter(new WorkoutAdapter(getData()));

        binding.getRoot().post(() -> setQuote());

    }

    private void setQuote(){
        AiDialogPopupBinding popupBinding = AiDialogPopupBinding.inflate(getLayoutInflater());

        // Create a PopupWindow
        final PopupWindow popupWindow = new PopupWindow(popupBinding.getRoot(),
                ViewGroup.LayoutParams.MATCH_PARENT,700,
                true);

        // Show the popup window at the center of the activity
        popupWindow.showAtLocation(binding.getRoot(), Gravity.CENTER, 0, 0);

        GeminiPro model = new GeminiPro();

        String query = prompt;
        //progressBar.setVisibility(View.VISIBLE);

        popupBinding.quoteTxt.setText("");
        //queryEditText.setText("");

        model.getResponse(query, new ResponseCallback() {
            @Override
            public void onResponse(String response) {
                popupBinding.quoteTxt.setText(response);
                //progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MainActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.GONE);
            }
        });

        // Set the AI quote

        // Close the popup when the close button is clicked
        popupBinding.dialogBackBtn.setOnClickListener(v -> popupWindow.dismiss());
    }

    public static ArrayList<Workout> getData() {
        ArrayList<Workout> workouts = new ArrayList<>();
        workouts.add(getWorkout1());
        workouts.add(getWorkout2());
        workouts.add(getWorkout3());
        return workouts;
    }

    public static Workout getWorkout1() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("Warmup", "10 min", "HBPMvFkpNgE", "pic_1_1"));
        lessons.add(new Lesson("Cardio", "20 min", "K6I24WgiiPw", "pic_1_2"));
        lessons.add(new Lesson("Cool down", "5 min", "Zc08v4YY0eA", "pic_1_3"));

        return new Workout(lessons, "35 min", 250, "pic_1", "Beginner full-body workout", "Workout 1");
    }

    public static Workout getWorkout2() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("Warmup", "8 min", "link4", "pic_2_1"));
        lessons.add(new Lesson("Strength", "25 min", "link5", "pic_2_2"));
        lessons.add(new Lesson("Stretching", "7 min", "link6", "pic_2_3"));

        return new Workout(lessons, "40 min", 300, "pic_2", "Intermediate strength workout", "Workout 2");
    }

    public static Workout getWorkout3() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("Warmup", "5 min", "link7", "pic_3_1"));
        lessons.add(new Lesson("HIIT", "30 min", "link8", "pic_3_2"));
        lessons.add(new Lesson("Cool down", "10 min", "link9", "pic_3_3"));

        return new Workout(lessons, "45 min", 400, "pic_3", "Advanced HIIT workout", "Workout 3");
    }

}