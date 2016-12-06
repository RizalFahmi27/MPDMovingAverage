package com.android.exercise.peramalandata;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InputFragmentToMain{

    FragmentManager fm;
    android.app.FragmentTransaction fragmentTransaction;
    FragmentInput fragmentInput;
    FragmentResult fragmentResult;
    Button buttonNext, buttonBack;
    ArrayList<Integer> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTransaction = getFragmentManager().beginTransaction();
        fm = getSupportFragmentManager();

        fragmentInput = FragmentInput.newInstance("fragment_input");
        fragmentResult = FragmentResult.newInstance("fragment_result");

        fragmentTransaction.replace(R.id.fragmentInput,fragmentInput);
        fragmentTransaction.commit();

        buttonNext = (Button) findViewById(R.id.nextButton);
        buttonBack = (Button) findViewById(R.id.backButton);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = fragmentInput.onMessageFromActivity();
                if(data!=null) {
                    fragmentResult.setData(data);
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentInput, fragmentResult);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    hideNavigationButton(true);
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onMessageFromFragmentToActivity(ArrayList<Integer> data) {
        this.data = data;
    }

    @Override
    public void onMessageToEnableButton(boolean status) {
        if(status)
            buttonNext.setEnabled(false);
        else buttonNext.setEnabled(true);

    }

    @Override
    public void hideNavigationButton(boolean status) {
        if(status){
            buttonBack.setVisibility(View.VISIBLE);
            buttonNext.setVisibility(View.INVISIBLE);
        }
        else {
            buttonBack.setVisibility(View.INVISIBLE);
            buttonNext.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onMessageToEnableButton(true);
        hideNavigationButton(false);
    }
}
