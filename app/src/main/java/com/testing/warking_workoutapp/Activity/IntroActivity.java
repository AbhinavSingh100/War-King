package com.testing.warking_workoutapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.testing.warking_workoutapp.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding.startBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Pressed", Toast.LENGTH_SHORT).show();
            Log.d("Button", "onCreate: Pressed");
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
        });
    }
}