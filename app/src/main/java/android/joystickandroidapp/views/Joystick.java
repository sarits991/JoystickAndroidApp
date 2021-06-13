package android.joystickandroidapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.shape.ShapePath;

public class Joystick extends View {

    private float xPosition;
    private float yPosition;
    private float xCenter;
    private float yCenter;
    private float innerRadius = 150;
    private float externalRadius = 500;
    private JoystickOnChange joystickOnChange;
    private Paint paint;

    public interface JoystickOnChange{
        void onChange(float aileron, float elevator);
    }

    public void setJoystickOnChange(JoystickOnChange joystickOnChange){
        this.joystickOnChange = joystickOnChange;
    }

    public Joystick(Context context) {
        super(context);
        this.paint = new Paint();
    }

    public Joystick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
    }

    public Joystick(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setARGB(255,50,50,50);
        // Draw the background
        canvas.drawCircle(xCenter, yCenter, externalRadius, paint);
        paint.setARGB(255,0,0,255);
        // Draw the circle border
        canvas.drawCircle(xPosition, yPosition, innerRadius, paint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.xCenter = (float) getWidth() / 2;
        this.yCenter = (float) getHeight() / 2;
        this.xPosition = this.xCenter;
        this.yPosition = this.yCenter;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_UP != event.getAction()) {
            xPosition = event.getX();
            yPosition = event.getY();
            float distance = (float) Math.sqrt((xPosition - xCenter) * (xPosition - xCenter)
                    + (yPosition - yCenter) * (yPosition - yCenter));
            if (distance > externalRadius) {
                float ratio = externalRadius / distance;
                xPosition = xCenter + (xPosition - xCenter) * ratio;
                yPosition = yCenter + (yPosition - yCenter) * ratio;
            }
        }
        else {
            xPosition = xCenter;
            yPosition = yCenter;
        }

        if(joystickOnChange != null){
            float aileron = (xPosition - xCenter) / externalRadius;
            float elevator = (yPosition - yCenter) / externalRadius;
            joystickOnChange.onChange(aileron , elevator);
        }

        invalidate();
        return true;
    }
}
