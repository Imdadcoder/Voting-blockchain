package com.example.voting_blockchain_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CastVote extends AppCompatActivity {
    public static String user="",name="",det="";
    public static String arr[];
    Spinner sp;
String party="";
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_vote);
        Toast.makeText(getApplicationContext(),det,Toast.LENGTH_SHORT).show();

        sp=(Spinner) findViewById(R.id.sp);
         imageView = findViewById(R.id.imageView);
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,arr);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapterFrom);

        Button btn=findViewById(R.id.btnCast);




        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                int index = parentView.getSelectedItemPosition();
                party=arr[index];
                Toast.makeText(getApplicationContext(),party,Toast.LENGTH_SHORT).show();


                String imageUrl = Global.url+"parties/"+party+".jpg";
                Toast.makeText(getApplicationContext(),imageUrl,Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext())
                        .load(imageUrl)
                        .into(imageView);
               // Picasso.get().load(imageUrl).into(imageView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
new cast_vote().execute();
            }
        });

    }

    private class cast_vote extends AsyncTask<Void, String, String>
    {
        @Override
        public String doInBackground(Void... Void)
        {
            JSONObject jsn = new JSONObject();
            String response = "";
            try {
                URL url = new URL(Global.url +"cast_vote");
                jsn.put("user", user);
                jsn.put("party", party);


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



        }
    }



}
