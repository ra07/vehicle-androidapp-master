package com.example.hantenks.vms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.vms.rest.RestAPIRequest;
import com.vms.rest.RestAPIResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class SuspiciousVehicle extends ActionBarActivity {
    final Context context1=this;
    protected VMSApplication presentApp;
    private static int Result_Load_Image = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspicious_vehicle);
        presentApp = (VMSApplication)getApplicationContext();
        Button LoadImage = (Button) findViewById(R.id.button2);
        LoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, Result_Load_Image);
            }
        });

       }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == Result_Load_Image && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String[] filePathColoumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,filePathColoumn,null,null,null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColoumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageview = (ImageView) findViewById(R.id.suspiciousImageButton);
            imageview.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suspicious_vehicle, menu);
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

    public void buttonOnClick2(View v) {
        EditText VehicleModel;
        EditText VehicleNumber;
        EditText Details;
        Spinner spinner=(Spinner) findViewById(R.id.suspiciousVehicleType);
        String txtFromSpinner = spinner.getSelectedItem().toString();


        VehicleModel = (EditText) findViewById(R.id.suspiciousVehicleModel);
        final String vm = VehicleModel.getText()
                .toString();
        VehicleNumber = (EditText) findViewById(R.id.suspiciousVehicleNumber);
        final String vn = VehicleNumber.getText()
                .toString();
        Details = (EditText) findViewById(R.id.suspiciousRemarks);
        final String d = Details.getText()
                .toString();
        // check the empty fields
        if (TextUtils.isEmpty(vm) || TextUtils.isEmpty(vn) || TextUtils.isEmpty(d) || txtFromSpinner.equals("Choose Vehicle Type..."))
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
            List<NameValuePair> data = new ArrayList<NameValuePair>(4);
            //data.add(new BasicNameValuePair("reporter_name", n));
            data.add(new BasicNameValuePair("vehicle_type", txtFromSpinner));
            data.add(new BasicNameValuePair("vehicle_model", vm));
            data.add(new BasicNameValuePair("vehicle_number", vn));
           // data.add(new BasicNameValuePair("theft_time", date + "T" + time));
            data.add(new BasicNameValuePair("remarks", d));
            lr.init("http://10.42.0.1:8000/", "POST", data, RestAPIRequest.suspiciousVehicle, presentApp.getAuthToken());
            lr.send();

            /*Intent intent = new Intent(context1, MainActivity.class);
            startActivity(intent);*/
        }
    }

    private class LoginRequest extends RestAPIRequest {
        @Override

        protected void onFinish(RestAPIResponse res) {
            //Log.d("Response", Integer.toString(res.statusCode));
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
