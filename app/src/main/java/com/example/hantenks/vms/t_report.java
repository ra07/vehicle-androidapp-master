package com.example.hantenks.vms;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.vms.rest.RestAPIRequest;
import com.vms.rest.RestAPIResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class t_report extends ActionBarActivity {
    final Context context1=this;
    protected VMSApplication presentApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_report);
        presentApp = (VMSApplication)getApplicationContext();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_t_report, menu);
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

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert Box");
        builder.setMessage("Please fill the complete form!!");
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
    public void onBackPressed1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert Box");
        builder.setMessage("Vehicle Number Is Short");
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
    public void return_main()
    {
        Intent intent = new Intent(context1, MainActivity.class);
        startActivity(intent);
    }

    public void onBackPressed2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert Box");
        builder.setMessage("Your report is successfully registered");
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id)
            {
                return_main();
            }

        });

        AlertDialog alert = builder.create();
        alert.show();
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
    String AM_PM;
    public void onTimeSet(TimePicker time, int hourOfDay, int minute) {

        if (hourOfDay < 12) {
            AM_PM = "AM";
        } else {
            AM_PM = "PM";
        }
    }

    public void buttonOnClick1(View v) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        EditText Name;
        EditText VehicleModel;
        EditText VehicleNumber;
        EditText Details;
        Spinner spinner=(Spinner) findViewById(R.id.theftVehicleType);
        String txtFromSpinner = spinner.getSelectedItem().toString();
        TimePicker Time=(TimePicker) findViewById(R.id.theftTime);
        int hour = Time.getCurrentHour();
        int min = Time.getCurrentMinute();
        onTimeSet(Time , hour , min);
        /*if(AM_PM == "PM")
            hour = hour + 12;*/
        String c1 = Integer.toString(hour);
        String c2 = Integer.toString(min);
        String time = c1 + ":" + c2;
        //if(hour==1 && min==00 && AM_PM == "AM")
        Name = (EditText) findViewById(R.id.theftName);
        final String n = Name.getText()
                .toString();
        VehicleModel = (EditText) findViewById(R.id.theftVehicleModel);
        final String vm = VehicleModel.getText()
                .toString();
        VehicleNumber = (EditText) findViewById(R.id.theftVehicleNumber);
        final String vn = VehicleNumber.getText()
                .toString();
        Details = (EditText) findViewById(R.id.theftRemarks);
        final String d = Details.getText()
                .toString();
        // check the empty fields
        if (TextUtils.isEmpty(n) || TextUtils.isEmpty(vm) || TextUtils.isEmpty(vn) || TextUtils.isEmpty(d) || txtFromSpinner.equals("Choose Vehicle Type..."))
        {
            onBackPressed();
            return;
        }
        else if(vn.length() < 4 )
        {
            onBackPressed1();
        }


        else
        {
            LoginRequest lr = new LoginRequest();
            List<NameValuePair> data = new ArrayList<NameValuePair>(5);
            //data.add(new BasicNameValuePair("reporter_name", n));
            data.add(new BasicNameValuePair("vehicle_type", txtFromSpinner));
            data.add(new BasicNameValuePair("vehicle_model", vm));
            data.add(new BasicNameValuePair("registration_number", vn));
            data.add(new BasicNameValuePair("theft_time", date + "T" + time));
            data.add(new BasicNameValuePair("remarks", d));

           /* Log.d("THEFT", date+"T"+time);
            Log.d("THEFT", txtFromSpinner);
            Log.d("THEFT",vm );
            Log.d("THEFT", vn);
            Log.d("THEFT", d);*/
            lr.init("http://10.42.0.1:8000/", "POST", data, RestAPIRequest.theftReport, presentApp.getAuthToken());
            lr.send();



        }
    }
    private class LoginRequest extends RestAPIRequest {
        @Override

        protected void onFinish(RestAPIResponse res) {
            //Log.d("Response",Integer.toString(res.statusCode));
            if (res.statusCode == 201) {
                onBackPressed2();
            }
            else
            {
                onBackPressed3();
            }

        }
    }


    }

