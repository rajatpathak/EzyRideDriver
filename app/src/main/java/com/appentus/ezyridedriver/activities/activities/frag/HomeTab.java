package com.appentus.ezyridedriver.activities.activities.frag;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.ActivityHome;
import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.TodayAllTripModel;
import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.SharedPrefData;
import com.appentus.ezyridedriver.activities.activities.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeTab extends Fragment implements  OnMapReadyCallback{
    ActivityHome activityHome;
    private MapView mMapView;
    private ApiInterface apiService;

    @BindView(R.id.tvTotalTrip)
    TextView tvTotalTrip;



    public HomeTab() {
        // Required empty public constructor
    }

    View homeTabview;
    private GoogleMap mMap;
    MapFragment mapFragment;
    GPSTracker gpsTracker;
    String riderId="";
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (homeTabview != null) {
            ViewGroup parent = (ViewGroup) homeTabview.getParent();
            if (parent != null)
                parent.removeView(homeTabview);

        }
        try {
            homeTabview= inflater.inflate(R.layout.home_tab, container, false);
            MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.fragment_view_map);
            mapFragment.getMapAsync(HomeTab.this);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        riderId=SharedPrefData.GetStringPref(getContext(),Constant.Driver_Id,"");
        apiService= ApiClient.getClient().create(ApiInterface.class);
        Log.e("loginModel", riderId+"");
        getAllTripApi("1");
        return homeTabview;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("ONCreated","onCreatedView");
        gpsTracker=new GPSTracker(getContext());


//        runRequest();
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume","onResume");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("Onrestore","restore");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("saveState","saveState");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng latLng=new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        addMarker(latLng);
    }


    private void addMarker(LatLng destination) {
        MarkerOptions options = new MarkerOptions();
        options.position(destination);
        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.driver_marker);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
//        options.icon(BitmapDescriptorFactory.fromResource((R.drawable.driver_marker)));
        options.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        mMap.addMarker(options);

    }


    public void runRequest(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                dialogShow();
            }
        }, 5000);
    }


    public void dialogShow(){

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.pickup_request_dialog);
        dialog.setCancelable(false);
           TextView dialogButtonCancel = (TextView) dialog.findViewById(R.id.dialogCancel);
        TextView dialogButtonOk = (TextView) dialog.findViewById(R.id.dialogAccept);
        final TextView tvAddress = (TextView) dialog.findViewById(R.id.tvAddress);
        dialog.show();

        GoogleMap googleMap;


        MapView mMapView = (MapView) dialog.findViewById(R.id.mapView);
        MapsInitializer.initialize(getActivity());

        mMapView = (MapView) dialog.findViewById(R.id.mapView);
        mMapView.onCreate(dialog.onSaveInstanceState());
        mMapView.onResume();// needed to get the map to display immediately
        // if button is clicked, close the custom dialog

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                LatLng posisiabsen = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()); ////your lat lng
                Log.e("Address",gpsTracker.getAddress()+"");
                tvAddress.setText(""+gpsTracker.getAddress());
                googleMap.getUiSettings().setScrollGesturesEnabled(false);
                googleMap.addMarker(new MarkerOptions().position(posisiabsen).title("Pick up"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(posisiabsen));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
        });


        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // if button is clicked, close the custom dialog
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public void getAllTripApi(String driverId){

        Call<TodayAllTripModel> call = apiService.totalTrip(driverId);
        call.enqueue(new Callback<TodayAllTripModel>() {
            @Override
            public void onResponse(Call<TodayAllTripModel>call, Response<TodayAllTripModel> response) {

                if (response.isSuccessful()) {

                    TodayAllTripModel todayAllTripModel=response.body();
                    if (response.body().getStatus().equals(Constant.API_STATUS)) {
                        Log.e("TodayTrip", response.body().getStatus()+"");
                        Log.e("Trip", response.body().getResult().size()+"");

                    }
                    else {

                    }
                }
                else
                {

                    Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TodayAllTripModel>call, Throwable t) {
                // Log error here since request failed

                Toast.makeText(getContext(),"Network issue",Toast.LENGTH_SHORT).show();
            }
        });

    }



}