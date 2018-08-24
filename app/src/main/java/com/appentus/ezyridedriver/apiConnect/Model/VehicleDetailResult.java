package com.appentus.ezyridedriver.apiConnect.Model;

public class VehicleDetailResult {
    private String vehicle_interior_color;

    private String vehicle_color;

    private String vehicle_type_id;

    private String vehicle_year;

    private String vehicle_model;

    private String vehicle_driver_id;

    private String vehicle_brand;

    private String vehicle_id;

    private String vehicle_status;

    public String getVehicle_interior_color ()
    {
        return vehicle_interior_color;
    }

    public void setVehicle_interior_color (String vehicle_interior_color)
    {
        this.vehicle_interior_color = vehicle_interior_color;
    }

    public String getVehicle_color ()
    {
        return vehicle_color;
    }

    public void setVehicle_color (String vehicle_color)
    {
        this.vehicle_color = vehicle_color;
    }

    public String getVehicle_type_id ()
    {
        return vehicle_type_id;
    }

    public void setVehicle_type_id (String vehicle_type_id)
    {
        this.vehicle_type_id = vehicle_type_id;
    }

    public String getVehicle_year ()
    {
        return vehicle_year;
    }

    public void setVehicle_year (String vehicle_year)
    {
        this.vehicle_year = vehicle_year;
    }

    public String getVehicle_model ()
    {
        return vehicle_model;
    }

    public void setVehicle_model (String vehicle_model)
    {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_driver_id ()
    {
        return vehicle_driver_id;
    }

    public void setVehicle_driver_id (String vehicle_driver_id)
    {
        this.vehicle_driver_id = vehicle_driver_id;
    }

    public String getVehicle_brand ()
    {
        return vehicle_brand;
    }

    public void setVehicle_brand (String vehicle_brand)
    {
        this.vehicle_brand = vehicle_brand;
    }

    public String getVehicle_id ()
    {
        return vehicle_id;
    }

    public void setVehicle_id (String vehicle_id)
    {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_status ()
    {
        return vehicle_status;
    }

    public void setVehicle_status (String vehicle_status)
    {
        this.vehicle_status = vehicle_status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [vehicle_interior_color = "+vehicle_interior_color+", vehicle_color = "+vehicle_color+", vehicle_type_id = "+vehicle_type_id+", vehicle_year = "+vehicle_year+", vehicle_model = "+vehicle_model+", vehicle_driver_id = "+vehicle_driver_id+", vehicle_brand = "+vehicle_brand+", vehicle_id = "+vehicle_id+", vehicle_status = "+vehicle_status+"]";
    }
}
