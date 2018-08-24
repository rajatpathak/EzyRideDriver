package com.appentus.ezyridedriver.apiConnect.Model;

import java.util.List;

public class VehicleModel {

    private String message;

    private List<VehicleResult> result;

    private String status;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public List<VehicleResult> getResult ()
    {
        return result;
    }

    public void setResult (List<VehicleResult> result)
    {
        this.result = result;
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

    public static class VehicleResult {

        private String base_fare;

        private String vehicle_type_icon;

        private String vehicle_type_id;

        private String vehicle_type;

        private String person_capacity;

        private String commission;

        private String price_per_min;

        private String price_per_km;

        private String vehicle_type_status;
        private String vehicle_interior_color;

        private String vehicle_color;


        private String vehicle_year;

        private String vehicle_model;

        private String vehicle_driver_id;

        private String vehicle_brand;

        private String vehicle_id;

        private String vehicle_status;

        public String getBase_fare ()
        {
            return base_fare;
        }

        public String getVehicle_interior_color() {
            return vehicle_interior_color;
        }

        public void setVehicle_interior_color(String vehicle_interior_color) {
            this.vehicle_interior_color = vehicle_interior_color;
        }

        public String getVehicle_color() {
            return vehicle_color;
        }

        public void setVehicle_color(String vehicle_color) {
            this.vehicle_color = vehicle_color;
        }

        public String getVehicle_year() {
            return vehicle_year;
        }

        public void setVehicle_year(String vehicle_year) {
            this.vehicle_year = vehicle_year;
        }

        public String getVehicle_model() {
            return vehicle_model;
        }

        public void setVehicle_model(String vehicle_model) {
            this.vehicle_model = vehicle_model;
        }

        public String getVehicle_driver_id() {
            return vehicle_driver_id;
        }

        public void setVehicle_driver_id(String vehicle_driver_id) {
            this.vehicle_driver_id = vehicle_driver_id;
        }

        public String getVehicle_brand() {
            return vehicle_brand;
        }

        public void setVehicle_brand(String vehicle_brand) {
            this.vehicle_brand = vehicle_brand;
        }

        public String getVehicle_id() {
            return vehicle_id;
        }

        public void setVehicle_id(String vehicle_id) {
            this.vehicle_id = vehicle_id;
        }

        public String getVehicle_status() {
            return vehicle_status;
        }

        public void setVehicle_status(String vehicle_status) {
            this.vehicle_status = vehicle_status;
        }

        public void setBase_fare (String base_fare)
        {
            this.base_fare = base_fare;
        }

        public String getVehicle_type_icon ()
        {
            return vehicle_type_icon;
        }

        public void setVehicle_type_icon (String vehicle_type_icon)
        {
            this.vehicle_type_icon = vehicle_type_icon;
        }

        public String getVehicle_type_id ()
        {
            return vehicle_type_id;
        }

        public void setVehicle_type_id (String vehicle_type_id)
        {
            this.vehicle_type_id = vehicle_type_id;
        }

        public String getVehicle_type ()
        {
            return vehicle_type;
        }

        public void setVehicle_type (String vehicle_type)
        {
            this.vehicle_type = vehicle_type;
        }

        public String getPerson_capacity ()
        {
            return person_capacity;
        }

        public void setPerson_capacity (String person_capacity)
        {
            this.person_capacity = person_capacity;
        }

        public String getCommission ()
        {
            return commission;
        }

        public void setCommission (String commission)
        {
            this.commission = commission;
        }

        public String getPrice_per_min ()
        {
            return price_per_min;
        }

        public void setPrice_per_min (String price_per_min)
        {
            this.price_per_min = price_per_min;
        }

        public String getPrice_per_km ()
        {
            return price_per_km;
        }

        public void setPrice_per_km (String price_per_km)
        {
            this.price_per_km = price_per_km;
        }

        public String getVehicle_type_status ()
        {
            return vehicle_type_status;
        }

        public void setVehicle_type_status (String vehicle_type_status)
        {
            this.vehicle_type_status = vehicle_type_status;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [base_fare = "+base_fare+", vehicle_type_icon = "+vehicle_type_icon+", vehicle_type_id = "+vehicle_type_id+", vehicle_type = "+vehicle_type+", person_capacity = "+person_capacity+", commission = "+commission+", price_per_min = "+price_per_min+", price_per_km = "+price_per_km+", vehicle_type_status = "+vehicle_type_status+"]";
        }

    }
}
