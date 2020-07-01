package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    TextView Date;
    TextView Gre;

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
    String time = sdf.format(new Date(System.currentTimeMillis()));

    Calendar cal = new GregorianCalendar();
    @SuppressLint("DefaultLocale")
    String timeGre = String.format("%d/%d/%d %d:%d:%d" , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
            cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE),
            cal.get(Calendar.SECOND));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date = (TextView) findViewById(R.id.Date);

        Date.setText(time);
    }
}