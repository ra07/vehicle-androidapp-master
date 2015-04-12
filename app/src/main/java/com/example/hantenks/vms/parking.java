package com.example.hantenks.vms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.vms.rest.RestAPIRequest;
import com.vms.rest.RestAPIResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class parking extends ActionBarActivity {

    Spinner MySpinner;
    List<String> List1,List2,List3;
    private ArrayAdapter<String> myAdapter;
    protected VMSApplication presentApp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        presentApp = (VMSApplication)getApplicationContext();
        Log.d("PARKING", "entered oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        Log.d("PARKING", "created layout");
        LoginRequest lr = new LoginRequest();
        List<NameValuePair> data = new ArrayList<NameValuePair>(1);
        data.add(new BasicNameValuePair("reporter_name", "hello"));
        Log.d("Parking", "initializd");
        //data.add(new BasicNameValuePair("vehicle_type", txtFromSpinner));
        //data.add(new BasicNameValuePair("vehicle_model", vm));
        //data.add(new BasicNameValuePair("vehicle_number", vn));
        // data.add(new BasicNameValuePair("theft_time", date + "T" + time));
        //data.add(new BasicNameValuePair("remarks", d));
        lr.init("http://10.42.0.1:8000/", "GET", data, RestAPIRequest.parkingSlot, presentApp.getAuthToken());
        Log.d("Parking", "init lr done");
        lr.send();
        Log.d("Parking", "send started");

    }

    public void onBackPressed3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert Box");
        builder.setMessage("Something went wrong");
        builder.setNegativeButton("Return", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

        private class LoginRequest extends RestAPIRequest {
            @Override

            protected void onFinish(RestAPIResponse res) {
                //Log.d("Response", Integer.toString(res.statusCode));
                if (res.statusCode == 200) {
                    //onBackPressed2();
                    try {
                        JSONArray a = new JSONArray(res.content);
                        initList(a);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    onBackPressed3();
                }
            }
        }
    void initList(JSONArray list){
        List1 = new ArrayList<String>();
        List2 = new ArrayList<String>();
        List3 = new ArrayList<String>();
        try {
            int i;
            for(i=0;i<list.length();i++) {
                JSONObject j = list.getJSONObject(i);
                String json = j.getString("parking_area_name");
                JSONObject j1 = list.getJSONObject(i);
                String json1 = j1.getString("total_slots");
                JSONObject j2 = list.getJSONObject(i);
                String json2 = j2.getString("available_slots");
                List1.add(json);
                List2.add(json1);
                List3.add(json2);
            }




            MySpinner = (Spinner)findViewById(R.id.parkingSpotsSpinner);

            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, List1);
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            MySpinner.setAdapter(myAdapter);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void buttonOnClick2(View v)
    {
        Spinner spinner=(Spinner) findViewById(R.id.parkingSpotsSpinner);
        String txtFromSpinner = spinner.getSelectedItem().toString();
        int i=0;
        while(txtFromSpinner != List1.get(i))
        {
            i++;

        }
        String total;
        String available;
        total = List2.get(i);
        available = List3.get(i);
        final TextView textViewToChange1 = (TextView) findViewById(R.id.parkingTotalSlots);
        textViewToChange1.setText(
                total);
        final TextView textViewToChange2 = (TextView) findViewById(R.id.parkingAvailableSlots);
        textViewToChange2.setText(
                available);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
