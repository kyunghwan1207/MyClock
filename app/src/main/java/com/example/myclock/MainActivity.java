package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    TextView Date;
    TextView Gre;
    UsedAsync asyncTask;
    ProgressHandler handler;

    SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date = (TextView) findViewById(R.id.Date);
        Gre = (TextView) findViewById(R.id.Gre);

        handler = new ProgressHandler();

        runTime();
    }

    public void runTime() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        time = sdf.format(new Date(System.currentTimeMillis()));
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);
                        Thread.sleep(1000);

                    }catch (InterruptedException ex) {}
                }
            }
        });
        thread.start();
        asyncTask = new UsedAsync();
        asyncTask.execute();
    }

    class ProgressHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            Date.setText(time);
        }
    }

    class UsedAsync extends AsyncTask<Integer, Integer, Integer> {
        Calendar cal;
        String timeGre;

        @Override
        protected Integer doInBackground(Integer...params){
            while(!isCancelled()){
                cal = new GregorianCalendar();
                timeGre = String.format("%d/%d/%d %d:%d:%d" , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE),
                        cal.get(Calendar.SECOND));
                publishProgress();
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ex){}
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            cal = new GregorianCalendar();
            timeGre = String.format("%d/%d/%d %d:%d:%d" , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE),
                    cal.get(Calendar.SECOND));
            Gre.setText(timeGre);

            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
        }
        @Override
        protected void onProgressUpdate(Integer...values){
            Gre.setText(timeGre);

            super.onProgressUpdate(values);
        }

        public void execute() {
        }
    }
}