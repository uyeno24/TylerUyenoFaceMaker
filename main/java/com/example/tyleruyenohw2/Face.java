package com.example.tyleruyenohw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/**
 * This class draws a face on given canvas. It draws three different hairstyles
 * (Bald, Afro, and Long Hair) depending on users selection. It also generates
 * a random face when the "Random Face" button is clicked. It also changes the
 * RGB value of the colors using a SeekBar
 *
 * CAVEAT: N/A
 *
 * @author Tyler Uyeno
 * @version March 1, 2021
 */
public class Face extends SurfaceView implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    // Instance Variables
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public static int hairStyle;
    public static int focus = 2131296439; // ID number of hair RadioButton
    private SeekBar myRedSeekBar, myGreenSeekBar, myBlueSeekBar;
    private Spinner hairSelector;


    //These constants define the dimensions of the Face. Used to make
    //draw methods easier to read
    public float headLeft = 400.f;
    public float headRight = 1400.f;
    public float headTop = 500.f;
    public float headBottom = 1800.f;
    public float leftEyeCx = 700.f;
    public float rightEyeCx = 1100.f;
    public float eyeTop = 1000.f;
    public float eyeRadius = 75.f;
    public float noseLeft = 800.f;
    public float noseRight = 1000.f;
    public float noseTop = 1100.f;
    public float noseBottom = 1250.f;
    public float mouthLeft = 200.f;
    public float mouthRight = 1000.f;
    public float mouthTop = 1200.f;
    public float mouthBottom = 1650.f;
    public float startAngle = 20.f;
    public float sweepAngle = 10.f;
    public boolean useCenter = true;
    public float hairLeft = 300.f;
    public float hairRight = 1500.f;
    public float hairTop = 400.f;
    public float hairBottom = 1800.f;
    public float afroCx = 500.f;
    public float afroCy = 900.f;
    public float afroRadius = 200.f;

    // Creating Colors
    Paint black = new Paint();
    Paint white = new Paint();
    Paint blue = new Paint();
    Paint red = new Paint();
    Paint color = new Paint();

    /**
     * Face Constructor initializes the variables
     *
     * CAVEAT:
     *
     * @Param context
     *
     * @Param attrs
     *
     * @Return N/A
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);

        // makes the onDraw method get called
        setWillNotDraw(false);

        setBackgroundColor(Color.WHITE);

        black.setColor(Color.BLACK);
        white.setColor(Color.WHITE);
        blue.setColor(Color.BLUE);
        red.setColor(Color.RED);

        randomize();
    }

    /**
     * Randomize method - initializes all the variables to randomly selected valid values
     *
     * CAVEAT: N/A
     *
     * @Param N/A
     *
     * @Return void
     */
    public void randomize(){
        // Randomizes RGB values from 0-255
        skinColor = Color.rgb((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
        eyeColor = Color.rgb((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
        hairColor = Color.rgb((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));

        // Randomly selects one of the three hairStyles
        hairStyle = (int) (Math.random()*3);
    }

    /**
     * OnDraw method draws the Face on given canvas
     *
     * CAVEAT: N/A
     *
     * @param canvas is needed to draw
     *
     * @return void
     */
    public void onDraw(Canvas canvas) {
        if(hairStyle == 1) {
            drawAfro(canvas);
        }
        else if(hairStyle == 2) {
            drawLongHair(canvas);
        }
        drawHead(canvas);
        drawEyes(canvas);
        drawNose(canvas);
        drawMouth(canvas);

        invalidate();
    }

    /**
     * drawHead method draws an randomly colored oval (head) on given canvas
     *
     * CAVEAT: N/A
     *
     * @param canvas is needed to draw
     *
     * @return void
     */
    public void drawHead(Canvas canvas) {
        color.setColor(skinColor);
        canvas.drawOval(headLeft, headTop, headRight, headBottom, color);
    }

    /**
     * drawEyes method draws multiple circles on top of each other resembling eyes
     *
     * CAVEAT: N/A
     *
     * @param canvas is needed to draw
     *
     * @return void
     */
    public void drawEyes(Canvas canvas) {
        color.setColor(eyeColor);

        // Black border of eye
        canvas.drawCircle(leftEyeCx,eyeTop, eyeRadius, black);
        canvas.drawCircle(rightEyeCx,eyeTop, eyeRadius, black);

        // Eye whites
        canvas.drawCircle(leftEyeCx,eyeTop, eyeRadius-5.f, white);
        canvas.drawCircle(rightEyeCx,eyeTop, eyeRadius-5.f, white);

        // Iris(colored part)
        canvas.drawCircle(leftEyeCx,eyeTop, eyeRadius-30.f, color);
        canvas.drawCircle(rightEyeCx,eyeTop, eyeRadius-30.f, color);

        // Pupil(black center)
        canvas.drawCircle(leftEyeCx,eyeTop, eyeRadius-40.f, black);
        canvas.drawCircle(rightEyeCx,eyeTop, eyeRadius-40.f, black);
    }

    /**
     * drawAfro method draws randomly colored circles resembling an afro
     *
     * CAVEAT: N/A
     *
     * @param canvas is needed to draw
     *
     * @return void
     */
    public void drawAfro(Canvas canvas) {
        color.setColor(hairColor);
        for(int i=0; i<5; i++) {
            canvas.drawCircle(afroCx+(100*i), afroCy-(150*i), afroRadius, color);
            canvas.drawCircle(afroCx+800-(100*i), afroCy-(150*i), afroRadius, color);
        }
    }

    /**
     * drawLongHair method draws a randomly colored oval and rectangle resembling long hair
     *
     * CAVEAT: N/A
     *
     * @param canvas is needed to draw
     *
     * @return void
     */
    public void drawLongHair(Canvas canvas) {
        color.setColor(hairColor);
        canvas.drawOval(hairLeft, hairTop, hairRight, hairBottom, color);
        canvas.drawRect(hairLeft,hairTop+650.f,hairRight,hairBottom+150.f,color);
    }

    /**
     * drawNose method draws a red oval resembling a nose
     *
     * CAVEAT: N/A
     *
     * @param canvas is needed to draw
     *
     * @return void
     */
    public void drawNose(Canvas canvas) {
        canvas.drawOval(noseLeft, noseTop, noseRight, noseBottom, red);
    }

    /**
     * drawMouth method draws black Arc resembling a mouth
     *
     * CAVEAT: N/A
     *
     * @param canvas is needed to draw
     *
     * @return void
     */
    public void drawMouth(Canvas canvas) {
        canvas.drawArc(mouthLeft, mouthTop, mouthRight, mouthBottom, startAngle, sweepAngle, useCenter, black);
    }

    /**
     * onProgressChanged method checks for when the SeekBar values have changed and
     * if they have changed, it will set the color according to those values
     *
     * CAVEAT: N/A
     *
     * @param seekBar is a SeekBar that is built into method
     *
     * @param progress is a int value that comes from SeekBar
     *
     * @param fromUser is a boolean that is built into method
     *
     * @return void
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(focus == R.id.hairButton) {
            hairColor = Color.rgb(myRedSeekBar.getProgress(), myGreenSeekBar.getProgress(), myBlueSeekBar.getProgress());
        }
        else if(focus == R.id.skinButton) {
            skinColor = Color.rgb(myRedSeekBar.getProgress(), myGreenSeekBar.getProgress(), myBlueSeekBar.getProgress());
        }
        else if(focus == R.id.eyeButton) {
            eyeColor = Color.rgb(myRedSeekBar.getProgress(), myGreenSeekBar.getProgress(), myBlueSeekBar.getProgress());
        }

        invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    /**
     * onClick method, when RandomFaceButton is clicked it calls the randomize()
     * method and draws a random face. It also sets Spinner to the random hairStyle
     *
     * CAVEAT: N/A
     *
     * @param v is built in view needed for onClick
     *
     * @return void
     */
    @Override
    public void onClick(View v) {
        randomize();
        hairSelector.setSelection(hairStyle);
    }

    /**
     * setSeekBar method passes the SeekBars from MainActivity into SeekBar
     * variables in Face class
     *
     * CAVEAT: N/A
     *
     * @param r is myRedSeekBar
     *
     * @param g is myGreenSeekBar
     *
     * @param b is myBlueSeekBar
     *
     * @return void
     */
    public void setSeekBars(SeekBar r, SeekBar g, SeekBar b) {
        myRedSeekBar = r;
        myGreenSeekBar = g;
        myBlueSeekBar = b;
    }

    /**
     * setSpinner method passes the spinner from MainActivity into hairSelector
     * variable in Face class
     *
     * CAVEAT: N/A
     *
     * @param s is my spinner
     *
     * @return void
     */
    public void setSpinner(Spinner s) {
        hairSelector = s;
    }
}

