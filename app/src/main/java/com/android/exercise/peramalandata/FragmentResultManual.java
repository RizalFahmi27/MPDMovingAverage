package com.android.exercise.peramalandata;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentResultManual extends Fragment implements FragmentCallBacks {

    ListView listT;
    Context mContext;
    MainActivity mainActivity;
    ArrayList<Integer> data;
    HashMap dataSet;
    LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
           mContext = getActivity();
            mainActivity = (MainActivity) getActivity();
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        catch (IllegalStateException ex){
            throw new IllegalStateException("Activity must implement callbacks");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_ragment_result_manual,null);
        listT = (ListView) frameLayout.findViewById(R.id.listTManual);


        dataSet = DataSetProcessor.beginCalculation(data);


        Adapter adapter = new Adapter(dataSet);
        listT.setAdapter(adapter);
        return frameLayout;
    }


    public static FragmentResultManual newInstance(String strArgs){
        Bundle bundle = new Bundle();
        FragmentResultManual fragmentResultManual = new FragmentResultManual();
        bundle.putString("strArgsManual",strArgs);
        fragmentResultManual.setArguments(bundle);
        return fragmentResultManual;
    }


    @Override
    public ArrayList<Integer> onMessageFromActivity() {
        return null;
    }

    @Override
    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }

    private class Adapter extends BaseAdapter{

        HashMap dataset;
        int size;
        ArrayList<ArrayList<Float>> errorList;
        ArrayList<ArrayList<Float>> TvalueList;


        public Adapter(HashMap map){
            this.dataset = map;
            this.errorList = (ArrayList<ArrayList<Float>>) dataset.get("error");
            this.TvalueList = (ArrayList<ArrayList<Float>>) dataset.get("T");
            this.size = TvalueList.size();
        }

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder = new Holder();
            View rootView = inflater.inflate(R.layout.adapter_list_result_manual,null);
                holder.text = (TextView)rootView.findViewById(R.id.textResultBeforeDetail);
                holder.text.setText("T="+(position+2));

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap individualData = new HashMap();
                    individualData.put("iDt",dataset.get("initial"));
                    individualData.put("iError",errorList.get(position));
                    individualData.put("iT",TvalueList.get(position));
                    individualData.put("selectedT",(position+2));
                    individualData.put("iErrorAvg",((ArrayList<Float>)dataset.get("errorAvg")).get(position));
                    Intent intent = new Intent(mainActivity,ActivityResultManual.class);
                    intent.putExtra("map",individualData);
                    startActivity(intent);
                }
            });

            return rootView;
        }
    }

    private class Holder{
        TextView text;
    }
}
