package com.android.exercise.peramalandata;

import android.hardware.SensorManager;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rizal Fahmi on 06-Dec-16.
 */
public class DataSetProcessor {

    public static Map beginCalculation(ArrayList<Integer> data) {
        Map processedData = new HashMap();
        processedData.put("data",data);
        ArrayList<ArrayList<Float>> Tvalue = new ArrayList<>();
        ArrayList<ArrayList<Float>> errorValue = new ArrayList<>();
        Float cumulativeAvgError = 0.0f;
        ArrayList<Float> error = new ArrayList<>();
        int n = data.size();
        for(int i=0,tCounter=2;i<(n-3);i++,tCounter++){
            Float errorTemp =0.0f;
            ArrayList<Float> listT = new ArrayList<>();
            ArrayList<Float> listError = new ArrayList<>();
            for(int j=0;j<n;j++){
                Float dataT= 0.0f;

                if(j<tCounter){
                    dataT = null;
                }
                else {
                    for (int k = 0; k < tCounter; k++) {
                        dataT = dataT + data.get((j-tCounter)+k);
                    }
                    DecimalFormat df = new DecimalFormat("#.#");
                    dataT = Float.parseFloat(df.format(dataT/tCounter));
                    listT.add(dataT);
                    errorTemp += Math.abs(dataT-data.get(j));
                    listError.add(Math.abs(dataT-data.get(j)));
                }

                Log.d("dataT","i : "+(i+1) +" | j : "+(j+1) +" | data : "+errorTemp);
            }
            errorValue.add(listError);
            Tvalue.add(listT);
            errorTemp/=(n-tCounter);
            Log.d("dataT","error : "+errorTemp);
            error.add(errorTemp);
        }

        return error;

    }

}
