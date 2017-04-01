package com.example.administrator.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


public class HotelsFragment extends Fragment {

    private View v;
    private ListView mListView;
    private MyListviewAdapter myListviewAdapter;
    private ResourceDB resourceDB;
    private List<ListContentDB> mListContentDBs;

    public HotelsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            v = inflater.inflate(R.layout.fragment_hotel, container, false);
            mListView = (ListView) v.findViewById(R.id.mylistview);
            resourceDB = new ResourceDB();
            mListContentDBs = resourceDB.getHotel();
            myListviewAdapter = new MyListviewAdapter(mListContentDBs, getActivity());
            mListView.setAdapter(myListviewAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }
}
