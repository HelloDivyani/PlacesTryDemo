package com.example.android.placestry;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener
{
    TextView text;
    //First Get lat and long

    double latitude, longitude;
    //    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    Location loc;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    text=(TextView)findViewById(R.id.text);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            // txt.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        } catch (SecurityException e) {
            // dialogGPS(this.getContext()); // lets the user know there is a problem with the gps
            Toast.makeText(this, "Security Exception What What next", Toast.LENGTH_SHORT).show();
        }
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //txt.setText("Latitude:" + loc.getLatitude() + ", Longitude:" + loc.getLongitude());
            latitude = loc.getLatitude();
            longitude=loc.getLongitude();

        } catch (Exception e) {
            Toast.makeText(this,"Security Exception What ",Toast.LENGTH_SHORT).show();
            // dialogGPS(this.getContext()); // lets the user know there is a problem with the gps
        }
        Toast.makeText(this,"Latitude : "+latitude+"Long :"+longitude,Toast.LENGTH_LONG).show();
        ok=(Button)findViewById(R.id.ok);
        text.setText("Latitude : "+latitude+"Long :"+longitude);
    }

    public void ok(View v)
    {
        Bundle b= new Bundle();
        // Storing Data into Bundle

      //  b.putString("type",names[i]);
        b.putString("Latitude", String.valueOf(latitude));
        b.putString("Longitude", String.valueOf(longitude));
        //Intent i3 = new Intent(MainList.this,Display_First.class);
       // i3.putExtras(b);
        //startActivity(i3);

        Intent i=new Intent(MainActivity.this,ButtonOKClass.class);
        i.putExtras(b);
        startActivity(i);
        Toast.makeText(this,"Clicked ok fun",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onLocationChanged(Location location) {



        //txt.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();
       // Toast.makeText(this,"Latitude : "+latitude+"Long :"+longitude,Toast.LENGTH_LONG).show();

    }









    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
