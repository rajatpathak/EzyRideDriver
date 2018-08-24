package com.appentus.ezyridedriver.activities.activities.frag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appentus.ezyridedriver.MyAdapter;
import com.appentus.ezyridedriver.R;

public class EarningsTab extends Fragment {
    private TabLayout tabLayout;
    private ViewPager mViewPager;

    public EarningsTab() {
    }


    View earningTabView;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        earningTabView= inflater.inflate(R.layout.earnings_tab, container, false);

        return earningTabView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabInit();

    }


    public void tabInit(){


        //Initializing the tablayout
        tabLayout = (TabLayout) earningTabView.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("Weekly"));

        mViewPager = (ViewPager) earningTabView.findViewById(R.id.pagerChild);
        mViewPager.setAdapter(new MyAdapter(getChildFragmentManager(),2));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static class MyAdapter extends FragmentPagerAdapter {
        int totalTabs;
        public MyAdapter(FragmentManager fm, int totalTabs) {
            super(fm);
            this.totalTabs = totalTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    TodayTab todayTab= new TodayTab();
                    return todayTab;
                case 1:
                    WeeklyTab weeklyTab= new WeeklyTab();
                    return weeklyTab;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return totalTabs;
        }
    }
}