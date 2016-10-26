package edu.yang.coolweather.util;

public interface HttpCallbackLisener{
    void onFinish(String response);

    void onError(Exception e);
}