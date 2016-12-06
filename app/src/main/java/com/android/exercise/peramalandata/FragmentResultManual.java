package com.android.exercise.peramalandata;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class FragmentResultManual extends Fragment implements FragmentCallBacks {

    ListView listT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_ragment_result_manual,null);
        listT = (ListView) frameLayout.findViewById(R.id.listTManual);

        return frameLayout;
    }


    @Override
    public ArrayList<Integer> onMessageFromActivity() {
        return null;
    }

    @Override
    public void setData(ArrayList<Integer> data) {

    }

    private class Adapter extends BaseAdapter{

        Map dataset;

        public Adapter(Map map){
            this.dataset = map;
        }

        @Override
        public int getCount() {
            dataset.get()
            return ;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    private class Holder{

    }
}
