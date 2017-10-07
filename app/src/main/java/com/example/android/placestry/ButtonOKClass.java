package com.example.android.placestry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Divyani on 07-10-2017.
 */
public class ButtonOKClass extends AppCompatActivity
{
    String complete="";
    TextView t1;
    private ProgressDialog pDialog;
    ListView lv;

    ArrayList<HashMap<String, String>> contactList;
    // Key-value Pair

    String getLat,getlg;

    //String myurl;


    //Places API work Start
    private static String url;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok);
        t1=(TextView)findViewById(R.id.t1);
        Toast.makeText(this,"IN Button Class",Toast.LENGTH_SHORT).show();
        Intent i5 = getIntent();
        Bundle b1 = i5.getExtras();
        getLat = b1.getString("Latitude");
        getlg = b1.getString("Longitude");
        Toast.makeText(this,"Lat : "+getLat+"Long : "+getlg+"in Button class",Toast.LENGTH_SHORT).show();
       // url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + getlat + "," + getlg + "&radius=5000&type=" + type + "&key=AIzaSyB5J0DVdARLzuVMVp7pQlSMYeqtbDAaUuo";
        url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+getLat+","+getlg+"&radius=500&key=AIzaSyDnZMWXIcq82CuGl0-ItP2XDJkuTcl6Dyo";
        //only name extract
      contactList = new ArrayList<>();
      new GetContacts().execute();
        


    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ButtonOKClass.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler_First sh = new HttpHandler_First();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e("TAG", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray contacts = null;
                    try {
                        contacts = jsonObj.getJSONArray("results");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    int count=0;
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        //plag = c.getJSONObject("geometry").getJSONObject("location").getString("lat");
                        //plng = c.getJSONObject("geometry").getJSONObject("location").getString("lng");
                        //id = c.getString("place_id");
                        // b7.putString(String.valueOf(i), id);
                        // b7.putString(String.valueOf(i) + "lag", plag);
                        //b7.putString(String.valueOf(i) + "lng", plng);
                        String name = c.getString("name");
                        complete=complete+name+" ";
                        count++;
                        if(count==5)
                        {
                            break;
                        }
                        //String rating = c.getString("rating");
                        //icon_url = c.getString("icon");
                        //String vicinity = c.getString("vicinity");


                       // HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        //contact.put("place_id", id);
                        //contact.put("name", name);
                        //Toast.makeText(getApplicationContext(),"Here name"+name,Toast.LENGTH_SHORT).show();
                        // contact.put("check", String.valueOf(i));
                        //contact.put("icon_url", icon_url);
                        //  contact.put("rating",rating);
                        // new Download(imageView).execute(icon_url);

                        //contact.put("vicinity", vicinity);

                        //ontactList.add(contact);
                    }

                } catch (final JSONException e) {
                    Log.e("TAG", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("TAG", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Here Post Execute",Toast.LENGTH_SHORT).show();

           // ListAdapter adapter = new SimpleAdapter(
             //       ButtonOKClass.this, contactList, R.layout.list_item_first, new String[]{"name"}, new int[]{R.id.getName});
            // new ImageLoadTask("https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png", image).execute();
            //   new Download((ImageView)findViewById(R.id.getImage)).execute(icon_url);

            t1.setText(complete);
            //lv.setAdapter(adapter);


        }


    }  } // Class Main Ends

