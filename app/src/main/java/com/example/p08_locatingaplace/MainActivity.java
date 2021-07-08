package com.example.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

//    Button btn1, btn2, btn3;
    Spinner spn;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LatLng poi_north = new LatLng(1.441298071560513, 103.77223096441789);
        LatLng poi_central = new LatLng(1.2979457412173014, 103.84743732023901);
        LatLng poi_east = new LatLng(1.3491987129314225, 103.93581286441788);

        spn = findViewById(R.id.spinner);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (map != null){
                    if (position == 0) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north, 15));
                    } else if (position == 1) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 15));
                    } else {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 15));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_sg = new LatLng(1.3516401888557787, 103.88618631682859);

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_sg, 10));

                Marker north = map.addMarker(new MarkerOptions()
                        .position(poi_north)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm Tel:65433456")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                Marker central = map.addMarker(new MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 Operating hours: 11am-8pm Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                Marker east = map.addMarker(new MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 Operating hours: 9am-5pm Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);

                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if(permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        });

//        btn1 = findViewById(R.id.btn1);
//        btn2 = findViewById(R.id.btn2);
//        btn3 = findViewById(R.id.btn3);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north, 15));
//                }
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 15));
//                }
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 15));
//                }
//            }
//        });



    }
}