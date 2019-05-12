package com.nol.arthurg.date2datecompletion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nol.arthurg.date2datecompletion.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button defdateToday = findViewById(R.id.todayButton);
        Button defdateBirth = findViewById(R.id.birthButton);
        Button GoButton = findViewById(R.id.goButton);
        final EditText startDate = findViewById(R.id.startingDate);
        final EditText endDate = findViewById(R.id.endingDate);
        final TextView resultText = findViewById(R.id.resultText);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100*1000);
        //final ProgressBar progressBar = findViewById(R.id.progressBar);

        resultText.setText("");
        //progressBar.setMin(0);
        //progressBar.setMax(100000);
        //progressBar.setProgress(0);

        defdateToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GregorianCalendar gc = new GregorianCalendar();
                int day = gc.get(Calendar.DAY_OF_MONTH);
                int month = gc.get(Calendar.MONTH)+1;
                int year = gc.get(Calendar.YEAR);

                String str1 = "" + day + "/" + month + "/" + year;
                String str2 = "" + (day+1) + "/" + month + "/" + year;

                startDate.setText(str1);
                endDate.setText(str2);
            }
        });

        defdateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startDate.setText("19/6/2000");
                endDate.setText("19/6/2085");
            }
        });

        GoButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String[] parseStart = startDate.getText().toString().split("/");
                String[] parseEnd = endDate.getText().toString().split("/");

//                LocalDateTime startTime = LocalDateTime.of(Integer.parseInt(parseStart[2]), Integer.parseInt(parseStart[1]), Integer.parseInt(parseStart[0]), 0,0,0);
//
//                LocalDateTime endTime = LocalDateTime.of(Integer.parseInt(parseEnd[2]), Integer.parseInt(parseEnd[1]), Integer.parseInt(parseEnd[0]), 0,0,0);
//
//                LocalDateTime currentTime = LocalDateTime.now();
//
//
//                long totalDiff = ChronoUnit.SECONDS.between(endTime, startTime);
//                long varDiff = ChronoUnit.SECONDS.between(currentTime, startTime);
                // long left = endMillis - currentMillis;

                GregorianCalendar startTime = new GregorianCalendar(Integer.parseInt(parseStart[2]), Integer.parseInt(parseStart[1])-1, Integer.parseInt(parseStart[0]));
                GregorianCalendar endTime = new GregorianCalendar(Integer.parseInt(parseEnd[2]), Integer.parseInt(parseEnd[1])-1, Integer.parseInt(parseEnd[0]));
                GregorianCalendar currentTime = new GregorianCalendar();

                long bigDiff = endTime.getTimeInMillis() - startTime.getTimeInMillis();
                long smallDiff = currentTime.getTimeInMillis()- startTime.getTimeInMillis();

                double frac = (double)smallDiff/(double)bigDiff*100;
                DecimalFormat df = new DecimalFormat("#.#####");

                resultText.setText("" + df.format(frac) + "% done");
                progressBar.setProgress((int)(frac*1000));




            }

        });


    }
}
