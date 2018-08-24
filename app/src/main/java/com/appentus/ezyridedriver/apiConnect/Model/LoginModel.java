package com.appentus.ezyridedriver.apiConnect.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ApiLoginResult result;

    @SerializedName("otp_temp")
    @Expose
    private Integer otpTemp;

    public Integer getOtpTemp() {
        return otpTemp;
    }

    public void setOtpTemp(Integer otpTemp) {
        this.otpTemp = otpTemp;
    }

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

    public ApiLoginResult getResult() {
        return result;
    }

    public void setResult(ApiLoginResult result) {
        this.result = result;
    }

    public static class ApiLoginResult {

        @SerializedName("driver_id")
        @Expose
        private String driverId;
        @SerializedName("driver_mobile")
        @Expose
        private String driverMobile;
        @SerializedName("driver_first_name")
        @Expose
        private String driverFirstName;
        @SerializedName("driver_last_name")
        @Expose
        private String driverLastName;
        @SerializedName("driver_email")
        @Expose
        private String driverEmail;
        @SerializedName("driver_country_code")
        @Expose
        private String driverCountryCode;
        @SerializedName("driver_country")
        @Expose
        private String driverCountry;
        @SerializedName("driver_state")
        @Expose
        private String driverState;
        @SerializedName("driver_city")
        @Expose
        private String driverCity;
        @SerializedName("driver_address")
        @Expose
        private String driverAddress;
        @SerializedName("driver_zipcode")
        @Expose
        private String driverZipcode;
        @SerializedName("driver_currency")
        @Expose
        private String driverCurrency;
        @SerializedName("driver_ac_name")
        @Expose
        private String driverAcName;
        @SerializedName("driver_ac_number")
        @Expose
        private String driverAcNumber;
        @SerializedName("driver_bank")
        @Expose
        private String driverBank;
        @SerializedName("driver_bank_location")
        @Expose
        private String driverBankLocation;
        @SerializedName("driver_bankcode")
        @Expose
        private String driverBankcode;
        @SerializedName("driver_lat")
        @Expose
        private String driverLat;
        @SerializedName("driver_long")
        @Expose
        private String driverLong;
        @SerializedName("driver_password")
        @Expose
        private String driverPassword;
        @SerializedName("driver_otp")
        @Expose
        private String driverOtp;
        @SerializedName("driver_device_token")
        @Expose
        private String driverDeviceToken;
        @SerializedName("driver_device_type")
        @Expose
        private String driverDeviceType;
        @SerializedName("driver_reg_date")
        @Expose
        private String driverRegDate;
        @SerializedName("driver_balance")
        @Expose
        private String driverBalance;
        @SerializedName("driver_spend_time")
        @Expose
        private String driverSpendTime;
        @SerializedName("live_status")
        @Expose
        private String liveStatus;
        @SerializedName("off_screen")
        @Expose
        private String offScreen;
        @SerializedName("otp_varify")
        @Expose
        private String otpVarify;
        @SerializedName("driver_status")
        @Expose
        private String driverStatus;

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getDriverMobile() {
            return driverMobile;
        }

        public void setDriverMobile(String driverMobile) {
            this.driverMobile = driverMobile;
        }

        public String getDriverFirstName() {
            return driverFirstName;
        }

        public void setDriverFirstName(String driverFirstName) {
            this.driverFirstName = driverFirstName;
        }

        public String getDriverLastName() {
            return driverLastName;
        }

        public void setDriverLastName(String driverLastName) {
            this.driverLastName = driverLastName;
        }

        public String getDriverEmail() {
            return driverEmail;
        }

        public void setDriverEmail(String driverEmail) {
            this.driverEmail = driverEmail;
        }

        public String getDriverCountryCode() {
            return driverCountryCode;
        }

        public void setDriverCountryCode(String driverCountryCode) {
            this.driverCountryCode = driverCountryCode;
        }

        public String getDriverCountry() {
            return driverCountry;
        }

        public void setDriverCountry(String driverCountry) {
            this.driverCountry = driverCountry;
        }

        public String getDriverState() {
            return driverState;
        }

        public void setDriverState(String driverState) {
            this.driverState = driverState;
        }

        public String getDriverCity() {
            return driverCity;
        }

        public void setDriverCity(String driverCity) {
            this.driverCity = driverCity;
        }

        public String getDriverAddress() {
            return driverAddress;
        }

        public void setDriverAddress(String driverAddress) {
            this.driverAddress = driverAddress;
        }

        public String getDriverZipcode() {
            return driverZipcode;
        }

        public void setDriverZipcode(String driverZipcode) {
            this.driverZipcode = driverZipcode;
        }

        public String getDriverCurrency() {
            return driverCurrency;
        }

        public void setDriverCurrency(String driverCurrency) {
            this.driverCurrency = driverCurrency;
        }

        public String getDriverAcName() {
            return driverAcName;
        }

        public void setDriverAcName(String driverAcName) {
            this.driverAcName = driverAcName;
        }

        public String getDriverAcNumber() {
            return driverAcNumber;
        }

        public void setDriverAcNumber(String driverAcNumber) {
            this.driverAcNumber = driverAcNumber;
        }

        public String getDriverBank() {
            return driverBank;
        }

        public void setDriverBank(String driverBank) {
            this.driverBank = driverBank;
        }

        public String getDriverBankLocation() {
            return driverBankLocation;
        }

        public void setDriverBankLocation(String driverBankLocation) {
            this.driverBankLocation = driverBankLocation;
        }

        public String getDriverBankcode() {
            return driverBankcode;
        }

        public void setDriverBankcode(String driverBankcode) {
            this.driverBankcode = driverBankcode;
        }

        public String getDriverLat() {
            return driverLat;
        }

        public void setDriverLat(String driverLat) {
            this.driverLat = driverLat;
        }

        public String getDriverLong() {
            return driverLong;
        }

        public void setDriverLong(String driverLong) {
            this.driverLong = driverLong;
        }

        public String getDriverPassword() {
            return driverPassword;
        }

        public void setDriverPassword(String driverPassword) {
            this.driverPassword = driverPassword;
        }

        public String getDriverOtp() {
            return driverOtp;
        }

        public void setDriverOtp(String driverOtp) {
            this.driverOtp = driverOtp;
        }

        public String getDriverDeviceToken() {
            return driverDeviceToken;
        }

        public void setDriverDeviceToken(String driverDeviceToken) {
            this.driverDeviceToken = driverDeviceToken;
        }

        public String getDriverDeviceType() {
            return driverDeviceType;
        }

        public void setDriverDeviceType(String driverDeviceType) {
            this.driverDeviceType = driverDeviceType;
        }

        public String getDriverRegDate() {
            return driverRegDate;
        }

        public void setDriverRegDate(String driverRegDate) {
            this.driverRegDate = driverRegDate;
        }

        public String getDriverBalance() {
            return driverBalance;
        }

        public void setDriverBalance(String driverBalance) {
            this.driverBalance = driverBalance;
        }

        public String getDriverSpendTime() {
            return driverSpendTime;
        }

        public void setDriverSpendTime(String driverSpendTime) {
            this.driverSpendTime = driverSpendTime;
        }

        public String getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(String liveStatus) {
            this.liveStatus = liveStatus;
        }

        public String getOffScreen() {
            return offScreen;
        }

        public void setOffScreen(String offScreen) {
            this.offScreen = offScreen;
        }

        public String getOtpVarify() {
            return otpVarify;
        }

        public void setOtpVarify(String otpVarify) {
            this.otpVarify = otpVarify;
        }

        public String getDriverStatus() {
            return driverStatus;
        }

        public void setDriverStatus(String driverStatus) {
            this.driverStatus = driverStatus;
        }

    }
}
