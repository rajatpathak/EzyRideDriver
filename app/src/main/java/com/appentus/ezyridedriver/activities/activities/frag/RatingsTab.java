package com.appentus.ezyridedriver.activities.activities.frag;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.RatingModel;
import com.appentus.ezyridedriver.rvAdapterRating;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingsTab extends Fragment {
    private RecyclerView recyclerView;

    @BindView(R.id.pb1)
    ProgressBar pb1;

    @BindView(R.id.pb2)
    ProgressBar pb2;

    @BindView(R.id.pb3)
    ProgressBar pb3;

    @BindView(R.id.pb4)
    ProgressBar pb4;

    @BindView(R.id.pb5)
    ProgressBar pb5;


    public RatingsTab() {
    }
    private List<RatingModel> commentsList= new ArrayList<>();
    View ratingTabView;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        ratingTabView=inflater.inflate(R.layout.rating_tab, container, false);
        return ratingTabView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,ratingTabView);
        pb5.setProgress(100);
        pb4.setProgress(75);
        pb3.setProgress(50);
        pb2.setProgress(40);
        pb1.setProgress(25);
        recyclerView = (RecyclerView) ratingTabView.findViewById(R.id.recycler_view);

        rvAdapterRating mAdapter = new rvAdapterRating(commentsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        prepareMovieData();
    }

    private void prepareMovieData() {
        for (int i=0;i<=8;i++){


            RatingModel data= new RatingModel("Jessica","30 Sep 2018","Refrence site about lorem ipsum, giving information on its orings, as we  ll as a random Lipsum generator.","3.5");
            commentsList.add(data);
        }

    }
}