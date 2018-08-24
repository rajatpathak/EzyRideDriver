package com.appentus.ezyridedriver;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;

import com.appentus.ezyridedriver.activities.activities.frag.Account;
import com.appentus.ezyridedriver.activities.activities.frag.EarningsTab;
import com.appentus.ezyridedriver.activities.activities.frag.HomeTab;
import com.appentus.ezyridedriver.activities.activities.frag.RatingsTab;


public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }


    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeTab homeFragment = new HomeTab();
                return homeFragment;
            case 1:
                EarningsTab earnings= new EarningsTab();
                return earnings;
            case 2:
                RatingsTab ratings= new RatingsTab();
                return ratings;
            case 3:
                Account account= new Account();
                return account;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}