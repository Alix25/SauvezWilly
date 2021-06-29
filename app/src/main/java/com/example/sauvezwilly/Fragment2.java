package com.example.sauvezwilly;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;


import com.here.PermissionsRequestor;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.*;



public class Fragment2 extends AppCompatActivity {
    private static final String TAG = Fragment2.class.getSimpleName();
    private PermissionsRequestor permissionsRequestor;
    private MapView mapView ;
    private GeoCoordinates currentLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment2_layout);
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();

        if (b != null) {
            currentLocation = new GeoCoordinates(b.getDouble("locationLatitude"), b.getDouble("locationLongitude"));
        }
        mapView.setOnReadyListener(new MapView.OnReadyListener() {
            @Override
            public void onMapViewReady() {
                // This will be called each time after this activity is resumed.
                // It will not be called before the first map scene was loaded.
                // Any code that requires map data may not work as expected beforehand.
                Log.d(TAG, "HERE Rendering Engine attached.");
            }
        });
//ask permission
        handleAndroidPermissions();
        loadMapScene();
        ImageButton reportButton = findViewById(R.id.reportButton);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Fragment2.this,"It works",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Fragment2.this, Fragment1.class);
                //can not pass GeoCoordinates so we pass the data to rebuild it after
                //double latitude = currentLocation.latitude;
                //double longitude = currentLocation.longitude;
                //intent.putExtra("locationLatitude", latitude);
                //intent.putExtra("locationLongitude", longitude);
                startActivity(intent);
                //end the activity because because we don't want to go back but we want to restart it after
                //finish();

            }
        });
    }



    private void handleAndroidPermissions() {
        permissionsRequestor = new PermissionsRequestor(this);
        permissionsRequestor.request(new PermissionsRequestor.ResultListener(){

            @Override
            public void permissionsGranted() {
                loadMapScene();
            }

            @Override
            public void permissionsDenied() {
                Log.e(TAG, "Permissions denied by user.");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsRequestor.onRequestPermissionsResult(requestCode, grantResults);
    }

   private void loadMapScene(){
        mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, mapError -> {
            if (mapError == null){
                mapView.getCamera().lookAt(new GeoCoordinates(52.5,13.3,10000));
            }else{
                Log.d(TAG, "Loading map failed: mapError: " + mapError.name());
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}

