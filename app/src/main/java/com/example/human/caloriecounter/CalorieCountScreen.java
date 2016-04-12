package com.example.human.caloriecounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalorieCountScreen extends AppCompatActivity {

    //Buttons
    Button saveButton;
    Button historyButton;

    //Ints for counting
    int breakfastCount, lunchCount, dinnerCount, totalDayCount, totalCount;

    //Arraylist for storing
    ArrayList<String> myArr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_count_screen);

        //saveButton code
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new saveButtonClicked());

        //historyButton code
        historyButton = (Button) findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new historyButtonClicked());

        //SeekBars
        SeekBar breakfastSeekBar = (SeekBar) findViewById(R.id.breakfastSeekBar);
        breakfastSeekBar.setOnSeekBarChangeListener(new breakfastChange());

        SeekBar lunchSeekBar = (SeekBar) findViewById(R.id.lunchSeekBar);
        lunchSeekBar.setOnSeekBarChangeListener(new lunchChange());

        SeekBar dinnerSeekBar = (SeekBar) findViewById(R.id.dinnerSeekBar);
        dinnerSeekBar.setOnSeekBarChangeListener(new dinnerChange());

    }

    //Called when user touches breakfast seekbar
    //Updates the texview holding calorie count
    private class breakfastChange implements SeekBar.OnSeekBarChangeListener
    {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            //Assign Textview
            TextView breakfastTextView = (TextView) findViewById(R.id.breakfastTextView);
            //Log the progress for disaply
            Log.d("DEBUG", "Progress is: " + progress);
            //Update Calorie Count
            breakfastTextView.setText("" + progress);
            //Assign breakfast for counting total after
            breakfastCount = progress;
            //Update Current Count
            TextView currentCountTextView = (TextView) findViewById(R.id.currentCountTextView);
            currentCountTextView.setText("Current Count = " + (breakfastCount+lunchCount+dinnerCount));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }

    //Called when user touches lunch seekbar
    //Updates the texview holding calorie count
    private class lunchChange implements SeekBar.OnSeekBarChangeListener
    {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            //Assign Textview
            TextView lunchTextView = (TextView) findViewById(R.id.lunchTextView);
            //Log the progress for disaply
            Log.d("DEBUG", "Progress is: " + progress);
            //Update Calorie Count
            lunchTextView.setText("" + progress);
            //Assign lunch for counting total after
            lunchCount = progress;
            //Update Current Count
            TextView currentCountTextView = (TextView) findViewById(R.id.currentCountTextView);
            currentCountTextView.setText("Current Count = " + (breakfastCount+lunchCount+dinnerCount));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }

    //Called when user touches dinner seekbar
    //Updates the texview holding calorie count
    private class dinnerChange implements SeekBar.OnSeekBarChangeListener
    {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            //Assign Textview
            TextView dinnerTextView = (TextView) findViewById(R.id.dinnerTextView);
            //Log the progress for disaply
            Log.d("DEBUG", "Progress is: " + progress);
            //Update Calorie Count
            dinnerTextView.setText("" + progress);
            //Assign dinner for counting total after
            dinnerCount = progress;
            //Update Current Count
            TextView currentCountTextView = (TextView) findViewById(R.id.currentCountTextView);
            currentCountTextView.setText("Current Count = " + (breakfastCount+lunchCount+dinnerCount));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }

    //Update screen values whenever anything is clicked
    //Workaround until I can get seek bars registering change
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return true;
    }

    //This function will save the users calorie count
    public class saveButtonClicked implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Get textview
            TextView currentCountTextView = (TextView) findViewById(R.id.currentCountTextView);
            //Get Date
            DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();

            //Get Progress
            SeekBar breakfastSeekBar = (SeekBar) findViewById(R.id.breakfastSeekBar);
            SeekBar lunchSeekBar = (SeekBar) findViewById(R.id.lunchSeekBar);
            SeekBar dinnerSeekBar = (SeekBar) findViewById(R.id.dinnerSeekBar);
            breakfastCount = lunchSeekBar.getProgress();
            lunchCount = lunchSeekBar.getProgress();
            dinnerCount = dinnerSeekBar.getProgress();

            //Update Count
            totalDayCount = breakfastCount + lunchCount + dinnerCount;
            String writingProgress = month + "/" + day + "/" + year + ": " + totalDayCount;

            //Add to array
            myArr.add(writingProgress);

            //Update View for user
            currentCountTextView.setText(writingProgress);

        }
    }

    //Send user to History Screen
    public class historyButtonClicked implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Create Intent
            Intent myIntent = new Intent(CalorieCountScreen.this, History.class);

            //Attach Array
            myIntent.putExtra("myArr", myArr);

            //Send User
            CalorieCountScreen.this.startActivity(myIntent);
        }
    }
}
