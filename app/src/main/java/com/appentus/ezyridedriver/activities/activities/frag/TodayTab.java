package com.appentus.ezyridedriver.activities.activities.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appentus.ezyridedriver.EarningModel;
import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.rvTripAdapter;

import java.util.ArrayList;
import java.util.List;

public class TodayTab extends Fragment {
    private RecyclerView recyclerView;
    private View todayTabView;
    private rvTripAdapter mAdapter;
    private List<EarningModel> todaysList = new ArrayList<>();
    public TodayTab() {
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        todayTabView =inflater.inflate(R.layout.today_tab, container, false);
        return todayTabView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) todayTabView.findViewById(R.id.rvTripHisroty);

        mAdapter = new rvTripAdapter(todaysList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        for (int i=0;i<=10;i++){

            EarningModel rv= new EarningModel("John","05.1","$5");
            todaysList.add(rv);
        }
    }
}