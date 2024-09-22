package com.testing.warking_workoutapp;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(Throwable throwable);
}
