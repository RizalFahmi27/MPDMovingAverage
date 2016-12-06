package com.android.exercise.peramalandata;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Rizal Fahmi on 01-Dec-16.
 */
public class FragmentResult extends Fragment implements FragmentCallBacks {

    MainActivity mainActivity;
    Context mContext;
    LayoutInflater inflater = null;
    ListView listViewResultSet;
    ArrayList<Integer> data;
    ArrayList<Float> processedData;
    TextView error;
    TextView periode;

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

    public static FragmentResult newInstance(String strArgs){
        Bundle args = new Bundle();

        FragmentResult fragment = new FragmentResult();
        args.putString("stringArgs2",strArgs);
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_result,null);

        listViewResultSet = (ListView) frameLayout.findViewById(R.id.listViewResult);
        periode = (TextView) frameLayout.findViewById(R.id.textPeriodeManual);
        error  = (TextView) frameLayout.findViewById(R.id.textErrorManual);

        processedData = DataSetProcessor.beginCalculation(data);

        Adapter adapter = new Adapter(processedData);
        Log.d("listview",""+processedData.size());
        listViewResultSet.setAdapter(adapter);

        Collections.sort(processedData);

        error.setText(""+processedData.get(0));
        //periode.setText(""+);

        return frameLayout;

    }



    @Override
    public void onAttach(Context context) {
        Log.d("listresult","went here");
        super.onAttach(context);
    }



    @Override
    public ArrayList<Integer> onMessageFromActivity() {
        return data;
    }

    @Override
    public void setData(ArrayList<Integer> data) {

        this.data = data;
    }

    private class Adapter extends BaseAdapter{

        ArrayList<Float> dataSet;

        public Adapter(ArrayList<Float> dataSet){
            this.dataSet = dataSet;
        }

        @Override
        public int getCount() {
            return dataSet.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            View rootView;
            rootView = inflater.inflate(R.layout.adapter_list_result,null);
                holder.T = (TextView) rootView.findViewById(R.id.textTResultManual);
                holder.error = (TextView) rootView.findViewById(R.id.textErrorResultManual);

                holder.T.setText("T="+(position+2));
                holder.error.setText(""+dataSet.get(position));
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return rootView;
        }
    }

    private class ViewHolder{
        TextView T;
        TextView error;
    }
}
