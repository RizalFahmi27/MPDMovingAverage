package com.android.exercise.peramalandata;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InputFragmentToMain{

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    FragmentInput fragmentInput;
    FragmentResult fragmentResult;
    FragmentResultManual fragmentResultManual;
    Button buttonNext, buttonBack;
    ArrayList<Integer> data;
    String inputMethod="Input data manual";
  //  RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fm = getSupportFragmentManager();

 //       radio = (RadioGroup) findViewById(R.id.radioGroup);
//        final String selectedOption = (String) ((RadioButton)radio.findViewById(radio.getCheckedRadioButtonId())).getText();


        fragmentInput = FragmentInput.newInstance("fragment_input");
        fragmentResult = FragmentResult.newInstance("fragment_result");
        fragmentResultManual = FragmentResultManual.newInstance("fragment_result_manual");
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);

        fragmentTransaction.replace(R.id.fragmentInput,fragmentInput);
        fragmentTransaction.commit();

        buttonNext = (Button) findViewById(R.id.nextButton);
        buttonBack = (Button) findViewById(R.id.backButton);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = fragmentInput.onMessageFromActivity();
                if(data!=null) {
                    if(inputMethod.equals("Input data default")) {
                        fragmentResult.setData(data);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                        fragmentTransaction.replace(R.id.fragmentInput, fragmentResult);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        hideNavigationButton(true);
                    }
                    else {
                        fragmentResultManual.setData(data);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                        fragmentTransaction.replace(R.id.fragmentInput,fragmentResultManual);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        hideNavigationButton(true);
                    }
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
    public void setInputMethod(String choice) {
        this.inputMethod = choice;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onMessageToEnableButton(true);
        hideNavigationButton(false);
    }
}
