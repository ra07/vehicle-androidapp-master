package com.example.hantenks.vms;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CampusMap extends ActionBarActivity {

    MyPinView pinView;
    Button btnSubmit,btnReset;
    Spinner spinnerSrc, spinnerDst;
    float x1,y1,x2,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ///// spinner triggering//////

        ////// end of spinner///////


        ////////// Multi Touch ////////////////////////////
        SubsamplingScaleImageView imageView2 = (SubsamplingScaleImageView)findViewById(R.id.indexView);
        imageView2.setImage(ImageSource.resource(R.drawable.indeximage));
        ///////////////end of multitouch///////////////////

        /////////////Plain Mao///////////////
        pinView = (MyPinView)findViewById(R.id.imageView);
        pinView.setImage(ImageSource.resource(R.drawable.mapimage));

        ///////end of plain map/////////////

        btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnReset = (Button)findViewById(R.id.btnReset);

        spinnerSrc = (Spinner)findViewById(R.id.spinnerSrc);
        spinnerDst = (Spinner)findViewById(R.id.spinnerDst);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showMarkers();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pinView.setDoublePin(new PointF(0f,0f),new PointF(0f,0f));
            }
        });
    }

    private void showMarkers() {
        int src,dst;
        src = spinnerSrc.getSelectedItemPosition();
        dst = spinnerDst.getSelectedItemPosition();
        switch(src)
        {
            case 0:
                x1 = 0f;
                y1 = 0f;
                break;
            case 1:
                x1 =1250f ;
                y1 =1617f ;
                break;
            case 2:
                x1 =3253f ;
                y1 =4878f ;
                break;
            case 3:
                x1 =4370f ;
                y1 =4110f ;
                break;
            case 4:
                x1 =4633f;
                y1 =3530f ;
                break;
            case 5:
                x1 =3837f;
                y1 =4042f ;
                break;
            case 6:
                x1 =3809f;
                y1 =2262f ;
                break;
            case 7:
                x1 =3441f;
                y1 =3810f ;
                break;
            case 8:
                x1 =4133f;
                y1 =3810f ;
                break;
            case 9:
                x1 =4633f;
                y1 =3082f ;
                break;
            case 10:
                x1 =3453f;
                y1 =3734f ;
                break;
            case 11:
                x1 =3749f;
                y1 =3726f ;
                break;
            case 12:
                x1 =4221f;
                y1 =3466f ;
                break;
            case 13:
                x1 =3533f;
                y1 =3366f ;
                break;
            case 14:
                x1 =2957f;
                y1 =2616f ;
                break;
            case 15:
                x1 =4631f;
                y1 =3571f ;
                break;
            case 16:
                x1 = 2280f;
                y1 = 3775f;;
                break;
            case 17:
                x1 = 2279f;
                y1 = 4062f;
                break;
            case 18:
                x1 = 2281f;
                y1 = 4379f;
                break;
            case 19:
                x1 = 2281f;
                y1 = 4613f;
                break;
            case 20:
                x1 = 2632f;
                y1 = 4112f;
                break;
            case 21:
                x1 = 2593f;
                y1 = 3416f;
                break;
            case 22:
                x1 = 2934f;
                y1 = 1101f;
                break;
            case 23:
                x1 = 4037f;
                y1 = 2617f;
                break;
            case 24:
                x1 = 3741f;
                y1 = 2972f;
                break;
            case 25:
                x1 = 3484f;
                y1 = 1707f;
                break;
            case 26:
                x1 = 2657f;
                y1 = 4131f;
                break;

            case 27:
                x1 =3385f;
                y1 =4672f ;
                break;
            case 28:
                x1 =4141f;
                y1 =3386f ;
                break;
            case 29:
                x1 =1469f;
                y1 =1798f ;
                break;
            case 30:
                x1 =3857f;
                y1 =2044f ;
                break;
            case 31:
                x1 =1817f;
                y1 =3850f ;
                break;
            case 32:
                x1 =3877f;
                y1 =2528f ;
                break;
            case 33:
                x1 =4581f;
                y1 =2536f ;
                break;
            case 34:
                x1 = 3448f;
                y1 = 767f;
                break;
            case 35:
                x1 = 3233f;
                y1 = 998f;
                break;
            case 36:
                x1 = 2468f;
                y1 = 1232f;
                break;
            case 37:
                x1 = 2085f;
                y1 = 1627f;
                break;
            case 38:
                x1 = 1245f;
                y1 = 2537f;
                break;
            case 39:
                x1 = 2837f;
                y1 = 1729f;
                break;
            case 40:
                x1 = 3268f;
                y1 = 3227f;
                break;
            case 41:
                x1 = 3393f;
                y1 = 2849f;
                break;
            case 42:
                x1 = 3053f;
                y1 = 2857f;
                break;
            case 43:
                x1 = 3954f;
                y1 = 2975f;
                break;

        }


        switch(dst)
        {
            case 0:
                x2 = 0f;
                y2 = 0f;
                break;
            case 1:
                x2 =1250f ;
                y2 =1617f ;
                break;
            case 2:
                x2 =3253f ;
                y2 =4878f ;
                break;
            case 3:
                x2 =4370f ;
                y2 =4110f ;
                break;
            case 4:
                x2 =4633f;
                y2 =3530f ;
                break;
            case 5:
                x2 =3837f;
                y2 =4042f ;
                break;
            case 6:
                x2 =3809f;
                y2 =2262f ;
                break;
            case 7:
                x2 =3441f;
                y2 =3810f ;
                break;
            case 8:
                x2 =4133f;
                y2 =3810f ;
                break;
            case 9:
                x2 =4633f;
                y2 =3082f ;
                break;
            case 10:
                x2 =3453f;
                y2 =3734f ;
                break;
            case 11:
                x2 =3749f;
                y2 =3726f ;
                break;
            case 12:
                x2 =4221f;
                y2 =3466f ;
                break;
            case 13:
                x2 =3533f;
                y2 =3366f ;
                break;
            case 14:
                x2 =2957f;
                y2 =2616f ;
                break;
            case 15:
                x2 =4631f;
                y2 =3571f ;
                break;
            case 16:
                x2 = 2280f;
                y2 = 3775f;;
                break;
            case 17:
                x2 = 2279f;
                y2 = 4062f;
                break;
            case 18:
                x2 = 2281f;
                y2 = 4379f;
                break;
            case 19:
                x2 = 2281f;
                y2 = 4613f;
                break;
            case 20:
                x2 = 2632f;
                y2 = 4112f;
                break;
            case 21:
                x2 = 2593f;
                y2 = 3416f;
                break;
            case 22:
                x2 = 2934f;
                y2 = 1101f;
                break;
            case 23:
                x2 = 4037f;
                y2 = 2617f;
                break;
            case 24:
                x2 = 3741f;
                y2 = 2972f;
                break;
            case 25:
                x2 = 3484f;
                y2 = 1707f;
                break;
            case 26:
                x2 = 2657f;
                y2 = 4131f;
                break;

            case 27:
                x2 =3385f;
                y2 =4672f ;
                break;
            case 28:
                x2 =4141f;
                y2 =3386f ;
                break;
            case 29:
                x2 =1469f;
                y2 =1798f ;
                break;
            case 30:
                x2 =3857f;
                y2 =2044f ;
                break;
            case 31:
                x2 =1817f;
                y2 =3850f ;
                break;
            case 32:
                x2 =3877f;
                y2 =2528f ;
                break;
            case 33:
                x2 =4581f;
                y2 =2536f ;
                break;
            case 34:
                x2 = 3448f;
                y2 = 767f;
                break;
            case 35:
                x2 = 3233f;
                y2 = 998f;
                break;
            case 36:
                x2 = 2468f;
                y2 = 1232f;
                break;
            case 37:
                x2 = 2085f;
                y2 = 1627f;
                break;
            case 38:
                x2 = 1245f;
                y2 = 2537f;
                break;
            case 39:
                x2 = 2837f;
                y2 = 1729f;
                break;
            case 40:
                x2 = 3268f;
                y2 = 3227f;
                break;
            case 41:
                x2 = 3393f;
                y2 = 2849f;
                break;
            case 42:
                x2 = 3053f;
                y2 = 2857f;
                break;
            case 43:
                x2 = 3954f;
                y2 = 2975f;
                break;

        }

        /////////////Markers///////////////
        pinView.setDoublePin(new PointF(x1,y1),new PointF(x2, y2));

        ///////end of markers module/////////////
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
