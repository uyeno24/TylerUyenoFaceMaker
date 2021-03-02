package com.example.tyleruyenohw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * This class sets up all of my SeekBars, RadioButtons, Buttons, and Spinner.
 *
 * CAVEAT: N/A
 *
 * @author Tyler Uyeno
 * @version March 1, 2021
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * onCreate method - gets the ID's for all the interfaces
     * <p>
     * CAVEAT: N/A
     *
     * @Param savedInstanceState
     * @Return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup up the SeekBars
        Face theView = (Face) findViewById(R.id.surfaceView);
        SeekBar myRedSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        myRedSeekBar.setOnSeekBarChangeListener(theView);

        SeekBar myGreenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        myGreenSeekBar.setOnSeekBarChangeListener(theView);

        SeekBar myBlueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        myBlueSeekBar.setOnSeekBarChangeListener(theView);

        // sending the SeekBars id to Face.java
        theView.setSeekBars(myRedSeekBar, myGreenSeekBar, myBlueSeekBar);

        /**
         * External Citation
         * Date: March 1, 2021
         * Problem: Could not get my spinner to work
         * Resource: https://www.youtube.com/watch?v=on_OrrX7Nw4
         * Solution: I used the example in the video and modified it
         *           so I could get it to my desired output
         */
        // Setup the Spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hairStyle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        theView.setSpinner(spinner);

        // Setup the RadioButtons
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioButtonGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Face.focus = checkedId;
                System.out.println(Face.focus);
            }
        });

        // Setup the randomFaceButton
        Button randomizeButton = (Button) findViewById(R.id.randomFaceButton);
        randomizeButton.setOnClickListener(theView);
    }

    /**
     * External Citation
     * Date: March 1, 2021
     * Problem: Could not get my spinner to work
     * Resource: https://www.youtube.com/watch?v=on_OrrX7Nw4
     * Solution: I used the example in the video and modified it
     * so I could get it to my desired output
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        Face.hairStyle = position;
        parent.setSelection(Face.hairStyle);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
