package com.example.voting_blockchain_app;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Home extends AppCompatActivity {
public static String user="",name="";
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    TextView tv,tvres;
    Button b1,b2;


Intent next;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

tv=(TextView)findViewById(R.id.tv);
        tvres=(TextView)findViewById(R.id.tvres);
tv.setText("Welcome "+user);
next=new Intent(this,CastVote.class);
b1=(Button)findViewById(R.id.btn1);
        b2=(Button)findViewById(R.id.btn2);

b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new get_candidates().execute();
       // new send_req_rpi().execute();

    }
});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new get_candidates().execute();
                new get_result().execute();

            }
        });


    }
    private class get_result extends AsyncTask<Void, String, String>
    {
        @Override
        public String doInBackground(Void... Void)
        {
            JSONObject jsn = new JSONObject();
            String response = "";
            try {
                URL url = new URL(Global.url +"get_result");
                jsn.put("user", user);



                response = HttpClientConnection.getData(url,jsn);
                Log.d("Response",""+response);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String s) {
            if(s.endsWith("null"))
            {
                s=s.substring(0,s.length()-4);
            }

            Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
            if(s.startsWith("%%@@"))
            {
                s=s.substring(4,s.length());

            }
            if(s.endsWith("%%@@"))
            {
                s=s.substring(0,s.length()-4);
            }
            String a[]=s.split("%%@@");

            for(int i=0;i<a.length;i++) {
                tvres.setText(tvres.getText()+"\n"+a[i]);
            }
        }
    }

    private class get_candidates extends AsyncTask<Void, String, String>
    {
        @Override
        public String doInBackground(Void... Void)
        {
            JSONObject jsn = new JSONObject();
            String response = "";
            try {
                URL url = new URL(Global.url +"get_candidates");
                jsn.put("user", user);



                response = HttpClientConnection.getData(url,jsn);
                Log.d("Response",""+response);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String s) {
            if(s.endsWith("null"))
            {
                s=s.substring(0,s.length()-4);
            }

            if(s.equalsIgnoreCase("no")) {
                Toast.makeText(getApplicationContext(),"No Election Going On", Toast.LENGTH_LONG).show();

            }
            else{
                String a[]=s.split("-");
                CastVote.arr=a;
                CastVote.user=user;
                startActivity(next);

            }


        }
    }

    private class send_req_rpi extends AsyncTask<Void, String, String>
    {
        @Override
        public String doInBackground(Void... Void)
        {
            JSONObject jsn = new JSONObject();
            String response = "";
            try {
                //URL url = new URL(url_rpi.url +"send_req_rpi.jsp");
                //jsn.put("user", user);
                URL url = new URL(url_rpi.url +"casting_vote");
                jsn.put("ecardnumber", user);



                response = HttpClientConnection.getData(url,jsn);
                Log.d("Response",""+response);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String s) {
            if(s.endsWith("null"))
            {
                s=s.substring(0,s.length()-4);
            }

            if(s.equalsIgnoreCase("no")) {
                Toast.makeText(getApplicationContext(),"No Election Going On", Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(getApplicationContext(),"Please Cast Your Vote", Toast.LENGTH_LONG).show();
                String a[]=s.split("-");
                CastVote.arr=a;
                CastVote.user=user;
                startActivity(next);

            }


        }
    }



}
