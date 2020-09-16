package com.example.shreyakumar.pr_fall20_simple;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URL;

public class AboutActivity extends AppCompatActivity {

    private TextView mDisplayAboutTextView;
    private Button mOpenWebpageButton;
    private Button mOpenMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mOpenWebpageButton = (Button) findViewById(R.id.button_open_webpage);
        mOpenMapButton     = (Button) findViewById(R.id.button_open_map);
        mDisplayAboutTextView = (TextView) findViewById(R.id.tv_about_text);

        // grabbing the data that the originating intent sends us
        Intent intentThatStartedThisActivity = getIntent();
        String message = "news";
        //check is extra data
        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            message = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mDisplayAboutTextView.append("\n\n\n" + message);
        } // end if

        // react to open webpage
        final String urlString = "https://www.nd.edu/";
        // add buttonlistener
        mOpenWebpageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("informational", urlString);
                openWebPage(urlString);

            } // end of onClick
        }); // end of setOnClickListener

        // react to open map button
        mOpenMapButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openMap();

            }   // end of onClick
        }); // end of setOnClickListener

    } // end of OnCreate

    public void openMap(){
        String addressString = "University of Notre Dame, Notre Dame, IN";
        Uri addressUri = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", addressString).build();
        Log.d("informational","addressUri" + addressUri.toString());

        Intent openMapIntent = new Intent(Intent.ACTION_VIEW);
        openMapIntent.setData(addressUri);

        if(openMapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openMapIntent);
        }
    } // end of openMap

    public void openWebPage(String urlString){
        Uri webpage = Uri.parse(urlString);

        Intent openWebpageIntent = new Intent(Intent.ACTION_VIEW, webpage);
        // check if it can be launched
        if(openWebpageIntent.resolveActivity(getPackageManager()) != null){
            startActivity(openWebpageIntent);
        }
    } // end of openWebpage

} // end class
