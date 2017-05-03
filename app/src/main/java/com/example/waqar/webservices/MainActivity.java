package com.example.waqar.webservices;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    List<String> list;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb= (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        pb.setMax(10);
        tv= (TextView) findViewById(R.id.tv);
        list=new ArrayList();
        for (int i=0;i<10;i++){
            list.add("Param "+i);
        }
        MyTask myTask=new MyTask();
        myTask.execute(list);
    }

    private class MyTask extends AsyncTask<List<String>,String,String>{

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(List... params) {
            for(int i=0;i<params[0].size();i++){
                publishProgress(params[0].get(i).toString(),String.valueOf(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Task Completed";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pb.setVisibility(View.INVISIBLE);
            tv.setText(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv.setText(values[0]);
            pb.setProgress(Integer.parseInt(values[1])+1);

        }
    }

}
