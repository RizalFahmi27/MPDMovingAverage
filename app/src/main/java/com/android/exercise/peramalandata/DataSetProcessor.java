package com.android.exercise.peramalandata;

import android.bluetooth.le.ScanFilter;
import android.hardware.SensorManager;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Rizal Fahmi on 06-Dec-16.
 */
public class DataSetProcessor {

    public static HashMap beginCalculation(ArrayList<Integer> data) {
        HashMap processedData = new HashMap();

        ArrayList<ArrayList<Float>> Tvalue = new ArrayList<>();
        ArrayList<ArrayList<Float>> errorValue = new ArrayList<>();
        ArrayList<Float> error = new ArrayList<>();
        int n = data.size();
        for(int i=0,tCounter=2;i<(n-3);i++,tCounter++){
            Float errorTemp =0.0f;
            ArrayList<Float> listT = new ArrayList<>();
            ArrayList<Float> listError = new ArrayList<>();
            for(int j=0;j<n;j++){
                Float dataT= 0.0f;
                Float dataError = 0.0f;
                if(j<tCounter){
                    dataT = null;
                    dataError = null;
                }
                else {
                    for (int k = 0; k < tCounter; k++) {
                        dataT = dataT + data.get((j-tCounter)+k);
                    }
                    DecimalFormat df = new DecimalFormat("#.#");
                    dataT = Float.parseFloat(df.format(dataT/tCounter));
                    errorTemp += Math.abs(dataT-data.get(j));
                    dataError = Math.abs(dataT-data.get(j));
                    //listError.add(Math.abs(dataT-data.get(j)));
                }
                listT.add(dataT);
                listError.add(dataError);
                Log.d("dataT","i : "+(i+1) +" | j : "+(j+1) +" | data : "+dataT);
            }
            errorValue.add(listError);
            Tvalue.add(listT);
            errorTemp/=(n-tCounter);
            Log.d("dataT","error : "+errorTemp);
            error.add(errorTemp);
        }

        Float min= error.get(0);
        int periode = 0;
        for (int i=0;i<error.size();i++){
            if(error.get(i)<min) {
                min = error.get(i);
                periode = i;
            }
        }

        processedData.put("whichPeriod",(periode+2));
        processedData.put("min",min);
        processedData.put("initial",data);
        processedData.put("T",Tvalue);
        processedData.put("error",errorValue);
        processedData.put("errorAvg",error);

        return processedData;

    }

}
