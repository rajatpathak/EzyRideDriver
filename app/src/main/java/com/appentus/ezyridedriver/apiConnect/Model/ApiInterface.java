package com.appentus.ezyridedriver.apiConnect.Model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    //Login method
    @FormUrlEncoded
    @POST("api/login_driver")
    Call<LoginModel> login(
            @Field("auth_token") String auth_token,
            @Field("device_token") String device_token,
            @Field("device_type") String device_type,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("driver_lat") String driver_lat,
            @Field("driver_long") String driver_long
    );

    //Login drive signup
    @FormUrlEncoded
    @POST("api/driver_signup")
    Call<LoginModel> registerDriver(
            @Field("auth_token") String auth_token,
            @Field("device_token") String device_token,
            @Field("device_type") String device_type,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("country_code") String country_code,
            @Field("country") String country,
            @Field("state") String state,
            @Field("city") String city,
            @Field("zipcode") String zipcode,
            @Field("password") String password,
            @Field("driver_lat") String driver_lat,
            @Field("driver_long") String driver_long
    );

    //Otp verification
    @FormUrlEncoded
    @POST("api/varify_otp")
    Call<LoginModel> otpVerificaiton(
            @Field("otp") String otp,
            @Field("driver_id") String driver_id
    );


    //Bank submit details
    @FormUrlEncoded
    @POST("api/driver_signup_next")
    Call<LoginModel> bankDetails(
            @Field("auth_token") String auth_token,
            @Field("driver_id") String driver_id,
            @Field("currency") String currency,
            @Field("ac_name") String ac_name,
            @Field("ac_number") String ac_number,
            @Field("bank") String bank,
            @Field("bank_code") String bank_code
    );




    //    vehicle type
    @POST("api/vehicle_type")
    Call<VehicleModel> vehicleType();

    //vehicle details submit
 @FormUrlEncoded
    @POST("api/add_driver_vehicle")
    Call<VehicleDetailModel> vehicleDetails(
            @Field("vehicle_type_id") String vehicle_type_id,
            @Field("driver_id") String driver_id,
            @Field("brand") String brand,
            @Field("model") String model,
            @Field("year") String year,
            @Field("color") String color,
            @Field("interior_color") String interior_color
    );
    @Multipart
    @POST("api/upload_driving_licence")
    Call<VehicleDetailModel> uploadDL(
            @Part("driver_id") RequestBody driver_id,
            @Part("licence_number") RequestBody licence_number,
            @Part("valid_vehicle_type") RequestBody valid_vehicle_type,
            @Part("issue_on") RequestBody issue_on,
            @Part("expiry_date") RequestBody expiry_date,
            @Part MultipartBody.Part image
//            @Part("driver_licence") RequestBody name
//            @Field("") String driver_licence,
    );



    //Driver duty status
    @FormUrlEncoded
    @POST("api/live_driver_status")
    Call<LoginModel> live_driver_status(
            @Field("driver_id") String driver_id,
            @Field("status_now") String status_now
    );

    //Driver multiple docs upload
    @Multipart
    @POST("api/upload_driver_doc")
    Call<VehicleDetailModel> uploadDriverDoc(
            @Part("driver_id") RequestBody driver_id,
            @Part("vehicle_id") RequestBody vehicle_id,
            @Part MultipartBody.Part[] files
    );

    //Driver request accept
    @FormUrlEncoded
    @POST("api/booking_request_accept")
    Call<VehicleDetailModel> requestAccept(
            @Field("driver_id") String driver_id,
            @Field("booking_id") String  vehicle_id
    );

    //Driver request reject
    @FormUrlEncoded
    @POST("api/booking_request_reject")
    Call<VehicleDetailModel> requestReject(
            @Field("driver_id") String driver_id,
            @Field("booking_id") String vehicle_id
    );

    //Trip completed
    @FormUrlEncoded
    @POST("api/complete_trip")
    Call<VehicleDetailModel> tripCompleted(
            @Field("driver_id") String driver_id,
            @Field("booking_id") String vehicle_id
    );

    //driver Total trip
    @FormUrlEncoded
    @POST("api/driver_total_trip_today")
    Call<TodayAllTripModel> totalTrip(
            @Field("driver_id") String driver_id
    );
    // show_driver_details
    @FormUrlEncoded
    @POST("api/show_driver_details")
    Call<VehicleDetailModel> totalSpendTime(
            @Field("driver_id") String driver_id
    );

    //driver udpated spend time
    @FormUrlEncoded
    @POST("api/update_driver_spendtime")
    Call<VehicleDetailModel> totalSpendTime(
            @Field("driver_id") String driver_id,
            @Field("spend_time") String spend_time
    );


}
