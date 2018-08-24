package com.appentus.ezyridedriver.apiConnect.Model;

public class VehicleDetailModel {

    private String message;

    private VehicleDetailResult result;

    private String status;

    public String getMessage ()
    {
        return message;
    }

    public VehicleDetailResult getResult() {
        return result;
    }

    public void setResult(VehicleDetailResult result) {
        this.result = result;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }



    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", result = "+result+", status = "+status+"]";
    }
}
