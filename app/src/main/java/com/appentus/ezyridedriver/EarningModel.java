package com.appentus.ezyridedriver;

public class EarningModel {


    private String name, time, amount;

    public EarningModel(String name, String time, String amount) {
        this.name = name;
        this.time = time;
        this.amount = amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
