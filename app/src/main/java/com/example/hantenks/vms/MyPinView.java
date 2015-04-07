package com.example.hantenks.vms;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.hantenks.vms.R;

public class MyPinView extends SubsamplingScaleImageView {

    private PointF sPin;
    private PointF dPin;
    private Bitmap pin;
    private Bitmap pin2;


    public MyPinView(Context context) {
        this(context, null);
    }

    public MyPinView(Context context, AttributeSet attr) {
        super(context, attr);
        initialise();
    }

    public void setPin(PointF sPin) {
        this.sPin = sPin;
        initialise();
        invalidate();
    }
    public void setDoublePin(PointF sPin,PointF dPin) {
        this.sPin = sPin;
        this.dPin = dPin;
        initialise();
        invalidate();


    }

    public PointF getPin() {
        return sPin;
    }

    private void initialise() {
        float density = getResources().getDisplayMetrics().densityDpi;
        pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_src);
        float w = (density/420f) * pin.getWidth();
        float h = (density/420f) * pin.getHeight();
        pin = Bitmap.createScaledBitmap(pin, (int)w, (int)h, true);
        pin2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_dest);
        float w2 = (density/420f) * pin2.getWidth();
        float h2 = (density/420f) * pin2.getHeight();
        pin2 = Bitmap.createScaledBitmap(pin2, (int)w2, (int)h2, true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady()) {
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        if (sPin != null && pin != null) {
            PointF vPin = sourceToViewCoord(sPin);
            float vX = vPin.x - (pin.getWidth()/2);
            float vY = vPin.y - pin.getHeight();
            canvas.drawBitmap(pin, vX, vY, paint);}
            if (dPin != null && pin != null) {
                PointF v2Pin = sourceToViewCoord(dPin);
                float vX2 = v2Pin.x - (pin2.getWidth() / 2);
                float vY2 = v2Pin.y - pin2.getHeight();
                canvas.drawBitmap(pin2, vX2, vY2, paint);
            }


    }

}