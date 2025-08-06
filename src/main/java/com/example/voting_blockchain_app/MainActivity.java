package com.example.voting_blockchain_app;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    EditText etm,etp;
    Button btn;

    Intent nh;
    String user="",pass="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etm=(EditText)findViewById(R.id.etMob);
        btn=(Button)findViewById(R.id.btnLog);

        etp=(EditText)findViewById(R.id.etPass);

        nh=new Intent(this,FingerprintActivity.class);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               user= etm.getText().toString();
                pass=etp.getText().toString();
                new login_check().execute();
            }
        });

    }



    private class login_check extends AsyncTask<Void, String, String>
    {
        @Override
        public String doInBackground(Void... Void)
        {
            JSONObject jsn = new JSONObject();
            String response = "";
            try {
                URL url = new URL(Global.url +"login_check");
                jsn.put("user", user);

                jsn.put("pass",pass);

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


            if(s.contains("fail")) {
                Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_LONG).show();

            }
            else{
                if(s.endsWith("null"))
                {
                    s=s.substring(0,s.length()-4);
                }




               // FingerprintActivity.name=s;
                FingerprintActivity.user=user;
                startActivity(nh);

            }


        }
    }
}
