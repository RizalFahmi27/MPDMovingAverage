//package com.android.exercise.peramalandata;
//
//import android.app.Activity;
//import android.app.Fragment;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by Rizal Fahmi on 30-Nov-16.
// */
//public class FragmentInputBak extends Fragment implements FragmentCallBacks{
//
//    Context mContext;
//    Activity mainActivity;
//    Button go;
//    EditText editTextN;
//    ListView listViewInput;
//    int n;
//    ArrayList data;
//    RelativeLayout inputGroup;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        try{
//            mContext = getActivity();
//            mainActivity = (MainActivity) getActivity();
//        }
//        catch (IllegalStateException e){
//            throw new IllegalStateException("Activity must implement callbacks");
//        }
//
//
//    }
//
//    public static FragmentInputBak newInstance(String strArgs){
//        Bundle args = new Bundle();
//
//        FragmentInputBak fragment = new FragmentInputBak();
//        args.putString("stringArgs1",strArgs);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        FrameLayout linearLayout = (FrameLayout) inflater.inflate(R.layout.fragment_input,null);
//
//        listViewInput = (ListView) linearLayout.findViewById(R.id.listViewInput);
//        go = (Button) linearLayout.findViewById(R.id.ButtonGo);
//        editTextN = (EditText) linearLayout.findViewById(R.id.editN);
//        inputGroup = (RelativeLayout) linearLayout.findViewById(R.id.relativeLayoutInput);
//
//        go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                n = Integer.parseInt(editTextN.getText().toString());
//                InputAdapter adapter = new InputAdapter(mContext,n);
//                listViewInput.setAdapter(adapter);
//            }
//        });
//
//        return linearLayout;
//
//    }
//
//    @Override
//    public void onMessageFromActivity() {
//
//    }
//
//    private class InputAdapter extends BaseAdapter{
//
//        private int n;
//        private Context listContext;
//        LayoutInflater inflater = null;
//
//        public InputAdapter(Context context, int n){
//            this.n = n;
//            this.listContext = context;
//            this.inflater = (LayoutInflater) listContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public int getCount() {
//            return n;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            Holder holder = new Holder();
//            View root;
//            root = inflater.inflate(R.layout.adapter_list_result,null);
//                holder.t = (TextView) root.findViewById(R.id.textT);
//                holder.dt = (EditText) root.findViewById(R.id.editDt);
////                holder.dt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
////                    @Override
////                    public void onFocusChange(View v, boolean hasFocus) {
////                        if(hasFocus)
////                            inputGroup.setVisibility(View.GONE);
////                        else inputGroup.setVisibility(View.VISIBLE);
////                    }
////                });
////                holder.dt.addTextChangedListener(new CustomTextWatcher());
//            holder.t.setText("" + (position+1));
//
//            return root;
//        }
//
//
//        public class Holder{
//            TextView t;
//            EditText dt;
//        }
//
//        private class CustomTextWatcher implements TextWatcher{
//
//            private int position;
//
//            public void updatePostion(int position){
//                this.position = position;
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.toString().equals(""))
//                    data.add(position,0);
//                else data.add(position,Integer.valueOf(s.toString()));
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        }
//    }
//}
