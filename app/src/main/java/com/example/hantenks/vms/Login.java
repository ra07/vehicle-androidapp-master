package com.example.hantenks.vms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.vms.rest.RestAPIRequest;
import com.vms.rest.RestAPIResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Login extends ActionBarActivity {
int flag=0;

    final Context context1=this;
    protected VMSApplication presentApp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //set application context
        presentApp = (VMSApplication)getApplicationContext();
       // Button button1=(Button) findViewById(R.id.loginSubmit);
        Button button2=(Button) findViewById(R.id.loginBusButton);
        Button button3=(Button) findViewById(R.id.loginNavigationButton);
        //final Context context1=this;
       /* button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                    Intent intent = new Intent(context1, MainActivity.class);
                    startActivity(intent);



            }

        });*/

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent intent = new Intent(context1,bus.class);
                startActivity(intent);

            }

        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent intent = new Intent(context1,CampusMap.class);
                startActivity(intent);

            }

        });

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
        builder.setMessage("Password Length Is Short");
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

    public void onBackPressed2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert Box");
        builder.setMessage("Incorrect Email");
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

    public void onBackPressed3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert Box");
        builder.setMessage("PLease select radio button");
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

    /*public void onBackPressed4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert Box");
        builder.setMessage("Are you Sure To Submit!!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }*/

    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    public void buttonOnClick(View v) {
        EditText Email;
        EditText Password;
        RadioButton security_admin;
        RadioButton security_personal;
        RadioButton student;

        Email = (EditText) findViewById(R.id.loginEmail);
        final String e = Email.getText()
                .toString();
        Password = (EditText) findViewById(R.id.loginPassword);
        final String p = Password.getText()
                .toString();
        security_admin = (RadioButton) findViewById(R.id.loginRadioButton3);
        security_personal = (RadioButton) findViewById(R.id.loginRadioButton2);
        student = (RadioButton) findViewById(R.id.loginRadioButton1);
        // check the empty fields
        if (TextUtils.isEmpty(e) || TextUtils.isEmpty(p))
                 {
            onBackPressed();
            return;
        }
       /* else if(p.length() < 6 )
        {
            onBackPressed1();
        }*/
        /*else if(!validEmail(e))
        {
            onBackPressed2();
        }*/
        else if(!security_admin.isChecked() && !security_personal.isChecked() && !student.isChecked())
        {
            onBackPressed3();
        }
        else
        {
            //authentication happens here
            LoginRequest lr = new LoginRequest();
            List<NameValuePair> data = new ArrayList<NameValuePair>(2);
            data.add(new BasicNameValuePair("username", e));
            data.add(new BasicNameValuePair("password", p));
            lr.init("http://10.42.0.1:8000/", "POST", data, RestAPIRequest.login, "None");
            lr.send();


        }
    }

    private class LoginRequest extends RestAPIRequest {
        @Override
        protected void onFinish(RestAPIResponse res) {
           if(res.statusCode==200){
               Log.d("LOGIN", "Authentication successful");
               try {
                   presentApp.setAuthToken(res.body.getString("token"));
               } catch (JSONException e) {
                   e.printStackTrace();
               }

               Intent intent = new Intent(context1, MainActivity.class);
               startActivity(intent);

           }
            else {
               //show authentication error
               Log.d("LOGIN", "Authentication error");
           }

        }
    }
}
