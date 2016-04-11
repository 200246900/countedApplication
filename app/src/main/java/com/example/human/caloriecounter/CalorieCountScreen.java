package com.example.human.caloriecounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    Button saveButton;
    Button historyButton;

    private boolean isTouch = false;
    int breakfastCount, lunchCount, dinnerCount, totalDayCount, totalCount;

    ArrayList<String> myArr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_count_screen);

        //saveButton code
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new saveButtonClicked());

        //historyButton code
        historyButton = (Button) findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new historyButtonClicked());

        //
        //SeekBar breakfastSeekBar = (SeekBar) findViewById(R.id.breakfastSeekBar);
        //breakfastSeekBar.onProgressChanged(new breakfastSeekBarChanged());

        TextView breakfastTextView = (TextView) findViewById(R.id.breakfastTextView);

        //Create Date for user
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String formattedDate = (date.toString()).substring(0, 10);

        //Update date for user
        //TextView currentDateTextView = (TextView) findViewById(R.id.currentDateTextView);
        //currentDateTextView.setText("" + formattedDate);

    }

    //Update screen values whenever anything is clicked
    //Workaround until I can get seek bars registering change
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //Text Views
        TextView breakfastTextView = (TextView) findViewById(R.id.breakfastTextView);
        TextView lunchTextView = (TextView) findViewById(R.id.lunchTextView);
        TextView dinnerTextView = (TextView) findViewById(R.id.dinnerTextView);
        TextView currentCountTextView = (TextView) findViewById(R.id.currentCountTextView);

        //Create seekbars
        SeekBar breakfastSeekBar = (SeekBar) findViewById(R.id.breakfastSeekBar);
        SeekBar lunchSeekBar = (SeekBar) findViewById(R.id.lunchSeekBar);
        SeekBar dinnerSeekBar = (SeekBar) findViewById(R.id.dinnerSeekBar);

        //Get Progress
        int breakfastValue = breakfastSeekBar.getProgress();
        int lunchValue = lunchSeekBar.getProgress();
        int dinnerValue = dinnerSeekBar.getProgress();

        //Update Text Fields
        breakfastTextView.setText(""+breakfastValue);
        lunchTextView.setText(""+lunchValue);
        dinnerTextView.setText(""+dinnerValue);

        //Update Current Count
        currentCountTextView.setText("Current Count = " + (breakfastValue+lunchValue+dinnerValue));
        return true;
    }

    //This function will save the users calorie count
    public class saveButtonClicked implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            TextView currentCountTextView = (TextView) findViewById(R.id.currentCountTextView);
            DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();

            //Get Progress
            SeekBar breakfastSeekBar = (SeekBar) findViewById(R.id.breakfastSeekBar);
            SeekBar lunchSeekBar = (SeekBar) findViewById(R.id.lunchSeekBar);
            SeekBar dinnerSeekBar = (SeekBar) findViewById(R.id.dinnerSeekBar);

            lunchCount = lunchSeekBar.getProgress();
            dinnerCount = dinnerSeekBar.getProgress();

            //Update Count
            totalDayCount = breakfastCount + lunchCount + dinnerCount;
            String writingProgress = month + "/" + day + "/" + year + ": " + totalDayCount;

            myArr.add(writingProgress);
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
            myIntent.putExtra("myArr", myArr);

            //Send User
            CalorieCountScreen.this.startActivity(myIntent);
        }
    }
}
