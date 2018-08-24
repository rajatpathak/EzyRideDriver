package com.appentus.ezyridedriver.apiConnect.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TodayAllTripModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }



    public class Result {

        @SerializedName("booking_id")
        @Expose
        private String bookingId;
        @SerializedName("pick_location")
        @Expose
        private String pickLocation;
        @SerializedName("pick_lat")
        @Expose
        private String pickLat;
        @SerializedName("pick_long")
        @Expose
        private String pickLong;
        @SerializedName("drop_location")
        @Expose
        private String dropLocation;
        @SerializedName("drop_lat")
        @Expose
        private String dropLat;
        @SerializedName("drop_long")
        @Expose
        private String dropLong;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("driver_id")
        @Expose
        private String driverId;
        @SerializedName("vehicle_id")
        @Expose
        private String vehicleId;
        @SerializedName("booking_date")
        @Expose
        private String bookingDate;
        @SerializedName("pickup_time")
        @Expose
        private String pickupTime;
        @SerializedName("drop_time")
        @Expose
        private String dropTime;
        @SerializedName("trip_fare")
        @Expose
        private String tripFare;
        @SerializedName("trip_tolls")
        @Expose
        private String tripTolls;
        @SerializedName("distance")
        @Expose
        private String distance;
        @SerializedName("booking_status")
        @Expose
        private String bookingStatus;
        @SerializedName("rider_id")
        @Expose
        private String riderId;
        @SerializedName("rider_name")
        @Expose
        private String riderName;
        @SerializedName("rider_email")
        @Expose
        private String riderEmail;
        @SerializedName("rider_mobile")
        @Expose
        private String riderMobile;
        @SerializedName("rider_password")
        @Expose
        private String riderPassword;
        @SerializedName("rider_balance")
        @Expose
        private String riderBalance;
        @SerializedName("rider_signup_date")
        @Expose
        private String riderSignupDate;
        @SerializedName("rider_status")
        @Expose
        private String riderStatus;
        @SerializedName("rider_otp")
        @Expose
        private String riderOtp;
        @SerializedName("rider_verify")
        @Expose
        private String riderVerify;
        @SerializedName("rider_device_token")
        @Expose
        private String riderDeviceToken;
        @SerializedName("rider_device_type")
        @Expose
        private String riderDeviceType;

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public String getPickLocation() {
            return pickLocation;
        }

        public void setPickLocation(String pickLocation) {
            this.pickLocation = pickLocation;
        }

        public String getPickLat() {
            return pickLat;
        }

        public void setPickLat(String pickLat) {
            this.pickLat = pickLat;
        }

        public String getPickLong() {
            return pickLong;
        }

        public void setPickLong(String pickLong) {
            this.pickLong = pickLong;
        }

        public String getDropLocation() {
            return dropLocation;
        }

        public void setDropLocation(String dropLocation) {
            this.dropLocation = dropLocation;
        }

        public String getDropLat() {
            return dropLat;
        }

        public void setDropLat(String dropLat) {
            this.dropLat = dropLat;
        }

        public String getDropLong() {
            return dropLong;
        }

        public void setDropLong(String dropLong) {
            this.dropLong = dropLong;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }

        public String getDropTime() {
            return dropTime;
        }

        public void setDropTime(String dropTime) {
            this.dropTime = dropTime;
        }

        public String getTripFare() {
            return tripFare;
        }

        public void setTripFare(String tripFare) {
            this.tripFare = tripFare;
        }

        public String getTripTolls() {
            return tripTolls;
        }

        public void setTripTolls(String tripTolls) {
            this.tripTolls = tripTolls;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getBookingStatus() {
            return bookingStatus;
        }

        public void setBookingStatus(String bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

        public String getRiderId() {
            return riderId;
        }

        public void setRiderId(String riderId) {
            this.riderId = riderId;
        }

        public String getRiderName() {
            return riderName;
        }

        public void setRiderName(String riderName) {
            this.riderName = riderName;
        }

        public String getRiderEmail() {
            return riderEmail;
        }

        public void setRiderEmail(String riderEmail) {
            this.riderEmail = riderEmail;
        }

        public String getRiderMobile() {
            return riderMobile;
        }

        public void setRiderMobile(String riderMobile) {
            this.riderMobile = riderMobile;
        }

        public String getRiderPassword() {
            return riderPassword;
        }

        public void setRiderPassword(String riderPassword) {
            this.riderPassword = riderPassword;
        }

        public String getRiderBalance() {
            return riderBalance;
        }

        public void setRiderBalance(String riderBalance) {
            this.riderBalance = riderBalance;
        }

        public String getRiderSignupDate() {
            return riderSignupDate;
        }

        public void setRiderSignupDate(String riderSignupDate) {
            this.riderSignupDate = riderSignupDate;
        }

        public String getRiderStatus() {
            return riderStatus;
        }

        public void setRiderStatus(String riderStatus) {
            this.riderStatus = riderStatus;
        }

        public String getRiderOtp() {
            return riderOtp;
        }

        public void setRiderOtp(String riderOtp) {
            this.riderOtp = riderOtp;
        }

        public String getRiderVerify() {
            return riderVerify;
        }

        public void setRiderVerify(String riderVerify) {
            this.riderVerify = riderVerify;
        }

        public String getRiderDeviceToken() {
            return riderDeviceToken;
        }

        public void setRiderDeviceToken(String riderDeviceToken) {
            this.riderDeviceToken = riderDeviceToken;
        }

        public String getRiderDeviceType() {
            return riderDeviceType;
        }

        public void setRiderDeviceType(String riderDeviceType) {
            this.riderDeviceType = riderDeviceType;
        }

    }

}
