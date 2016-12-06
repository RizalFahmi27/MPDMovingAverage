package com.android.exercise.peramalandata;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rizal Fahmi on 30-Nov-16.
 */
public class FragmentInput extends Fragment implements FragmentCallBacks {

    Context mContext;
    MainActivity mainActivity;
    Button go;
    EditText editTextN;
    LinearLayout listViewInput;
    LinearLayout columnName;
    int n;
    ArrayList<Integer> data = new ArrayList<>();
    RelativeLayout inputGroup;
    LayoutInflater inflater;
    boolean isEditTextFocused = false;
    RadioGroup radioGroup;
    String choice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            mContext = getActivity();
            mainActivity = (MainActivity) getActivity();
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        catch (IllegalStateException e){
            throw new IllegalStateException("Activity must implement callbacks");
        }


    }

    public static FragmentInput newInstance(String strArgs){
        Bundle args = new Bundle();

        FragmentInput fragment = new FragmentInput();
        args.putString("stringArgs1",strArgs);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout linearLayout = (FrameLayout) inflater.inflate(R.layout.fragment_input,null);



        listViewInput = (LinearLayout) linearLayout.findViewById(R.id.listViewInput);
        go = (Button) linearLayout.findViewById(R.id.ButtonGo);
        editTextN = (EditText) linearLayout.findViewById(R.id.editN);
        inputGroup = (RelativeLayout) linearLayout.findViewById(R.id.relativeLayoutInput);
        columnName = (LinearLayout) linearLayout.findViewById(R.id.header);
        radioGroup = (RadioGroup) linearLayout.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                choice = ((RadioButton)group.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                mainActivity.setInputMethod(choice);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = Integer.parseInt(editTextN.getText().toString());
                String selectedOption = ((RadioButton)radioGroup.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                populateEditText(n, selectedOption);
            }
        });

        return linearLayout;

    }

    private void populateEditText(int n, String selectedOption){
        listViewInput.removeAllViews();
        if(n>0) {
            columnName.setVisibility(View.VISIBLE);
            mainActivity.onMessageToEnableButton(false);
        }
        else {
            columnName.setVisibility(View.GONE);
            Toast.makeText(mContext, "Masukkan sedikitnya 1 data", Toast.LENGTH_SHORT).show();
            mainActivity.onMessageToEnableButton(true);
            return;
        }
        for(int i=0;i<n;i++){
            final LinearLayout view = (LinearLayout) inflater.inflate(R.layout.adapter_list_input,null);
            ((TextView)view.findViewById(R.id.textT)).setText(""+(i+1));

            if(!selectedOption.equalsIgnoreCase("Input data manual")){
                int randomInt = new Random().nextInt((100)+1);
                ((EditText)view.findViewById(R.id.editDt)).setText(""+randomInt);
                ((EditText)view.findViewById(R.id.editDt)).setEnabled(false);
            }
            else {
                ((EditText) view.findViewById(R.id.editDt)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        isEditTextFocused = hasFocus;
                    }
                });

                KeyboardVisibilityEvent.setEventListener(mainActivity, new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        Log.d("keyboard", "opened ? : " + isOpen);
                        boolean isFocusOnEdittext = ((EditText) view.findViewById(R.id.editDt)).hasFocus();
                        Log.d("keyboard", "opened editText : " + isFocusOnEdittext);
                        if (isOpen && isEditTextFocused)
                            inputGroup.setVisibility(View.GONE);
                        else inputGroup.setVisibility(View.VISIBLE);
                    }
                });
            }

            listViewInput.addView(view);
        }
    }

    @Override
    public ArrayList<Integer> onMessageFromActivity() {
        Integer dtValue = null;
        data.removeAll(data);
        if(n>0) {
            for (int i = 0; i < n; i++) {
                try {
                    dtValue = Integer.valueOf(((EditText) listViewInput.getChildAt(i).findViewById(R.id.editDt)).getText().toString());
                }
                catch (NumberFormatException ex) {
                    Toast.makeText(mContext, "Pastikan semua field sudah tersisi", Toast.LENGTH_SHORT).show();
                    return null;
                }
                data.add(dtValue);
            }

        }
        return data;
    }

    @Override
    public void setData(ArrayList<Integer> data) {

    }

}
