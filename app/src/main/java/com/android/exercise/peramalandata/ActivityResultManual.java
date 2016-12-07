package com.android.exercise.peramalandata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityResultManual extends AppCompatActivity {

    ListView table;
    TextView hasilPeramalan;
    TextView periode;
    TextView error;
    LayoutInflater inflater=null;
    HashMap dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_manual);

        table = (ListView) findViewById(R.id.listViewResultManual);
        hasilPeramalan = (TextView) findViewById(R.id.textResultManualPeramalan);
        periode = (TextView) findViewById(R.id.textPeriodeManual);
        error = (TextView) findViewById(R.id.textErrorManual);

        inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        dataSet = (HashMap) getIntent().getSerializableExtra("map");

        Adapter adapter = new Adapter(dataSet);
        table.setAdapter(adapter);

        periode.setText(""+(int)dataSet.get("selectedT"));
        error.setText(""+(Float)dataSet.get("iErrorAvg"));
        hasilPeramalan.setText(""+(float)dataSet.get("n+1"));


    }

    private class Adapter extends BaseAdapter{

        ArrayList<Integer> dt;
        ArrayList<Float> error;
        ArrayList<Float> T;

        public Adapter(HashMap map){
            dt = (ArrayList<Integer>) map.get("iDt");
            error = (ArrayList<Float>) map.get("iError");
            T = (ArrayList<Float>) map.get("iT");
        }

        @Override
        public int getCount() {
            return dt.size();
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
            Holder holder = new Holder();
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.adapter_list_result_manual_detail,null);

            holder.t = (TextView) linearLayout.findViewById(R.id.texttResultDetail);
            holder.dt = (TextView) linearLayout.findViewById(R.id.textDTResultDetail);
            holder.T = (TextView) linearLayout.findViewById(R.id.textTResultDetail);
            holder.error = (TextView) linearLayout.findViewById(R.id.textErrorResultDetail);

            holder.t.setText(""+(position+1));
            holder.dt.setText(""+dt.get(position));
            if(T.get(position)==null)
                holder.T.setText(" ");
            else holder.T.setText(""+T.get(position));

            if(error.get(position)==null)
                holder.error.setText(" ");
            else holder.error.setText(""+error.get(position));

            return linearLayout;
        }


    }

    private class Holder{
        TextView t;
        TextView dt;
        TextView T;
        TextView error;
    }

}
