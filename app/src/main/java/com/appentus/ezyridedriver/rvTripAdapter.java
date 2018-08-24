package com.appentus.ezyridedriver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class rvTripAdapter extends RecyclerView.Adapter<rvTripAdapter.MyViewHolder> {

    private List<EarningModel> todaysList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvTime, tvCashAmt;

        public MyViewHolder(View view) {
            super(view);
            tvName= (TextView) view.findViewById(R.id.tvName);
            tvCashAmt = (TextView) view.findViewById(R.id.tvCashAmt);
            tvTime= (TextView) view.findViewById(R.id.tvTime);
        }
    }


    public rvTripAdapter(List<EarningModel> todaysList) {
        this.todaysList = todaysList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todays_trip_history_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EarningModel earningTrip= todaysList.get(position);
        holder.tvName.setText(earningTrip.getName());
        holder.tvTime.setText(earningTrip.getTime());
        holder.tvCashAmt.setText(earningTrip.getAmount());
    }

    @Override
    public int getItemCount() {
        return todaysList.size();
    }
}