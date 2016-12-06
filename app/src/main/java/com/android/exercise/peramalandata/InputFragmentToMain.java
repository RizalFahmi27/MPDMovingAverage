package com.android.exercise.peramalandata;

import java.util.ArrayList;

/**
 * Created by Rizal Fahmi on 30-Nov-16.
 */
public interface InputFragmentToMain {
    public  void onMessageFromFragmentToActivity(ArrayList<Integer> data);
    public void onMessageToEnableButton(boolean status);
    public void hideNavigationButton(boolean status);
    public void setInputMethod(String choice);
}
