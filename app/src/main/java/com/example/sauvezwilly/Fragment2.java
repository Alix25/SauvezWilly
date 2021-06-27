package com.example.sauvezwilly;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.here.PermissionsRequestor;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.*;



/*public class Fragment2 extends Fragment {
    private static final String TAG = Fragment2.class.getSimpleName();
    private PermissionsRequestor permissionsRequestor;
    private MapView mapView ;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // super.onCreate(savedInstanceState);
        MapsInitializer.initialize(getActivity());

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
        loadMapScene();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final LinearLayout parent = (LinearLayout) inflater.inflate(R.layout.fragment2_layout, container, false);
        mapView = (MapView) parent.findViewById(R.id.map_view);
        return parent;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        loadMapScene();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


}*/




public class Fragment2 extends Fragment{
    private static final String TAG = Fragment2.class.getSimpleName();
   // private PermissionsRequestor permissionsRequestor;
    private MapView mapView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
       // handleAndroidPermissions();
        loadMapScene();
        return inflater.inflate(R.layout.fragment2_layout, container, false);
    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment2_layout);
        mapView = findViewById(R.id.map_view);
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
    }*/

 /*   private void handleAndroidPermissions() {
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
    }*/

    private void loadMapScene(){
        mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY,mapError -> {
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


/*public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2_layout, container, false);
    }
}*/

