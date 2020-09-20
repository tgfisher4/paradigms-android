package com.example.farrow_fisher_android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.farrow_fisher_android_project.R;

public class AboutActivity extends AppCompatActivity {

        private TextView aboutText;
        private Button openWeb;
        private Button openMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_about);

                aboutText = (TextView)findViewById(R.id.about_text);
                openWeb = (Button)findViewById(R.id.open_webpage);
                openMap = (Button)findViewById(R.id.open_map);

                final String urlStr = "https://www.nd.edu";
                final String addressStr = "University of Notre Dame, IN";

                openWeb.setOnClickListener(
                        new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                        openWebPage(urlStr);
                                }
                        }
                );

                openMap.setOnClickListener(
                        new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                        openMap(addressStr);
                                }
                        }
                );

        }

        public void openMap(String adrStr){
                Uri adr = Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", adrStr).build();
                Intent openMap = new Intent(Intent.ACTION_VIEW);
                openMap.setData(adr);

                if(openMap.resolveActivity(getPackageManager()) != null){
                        startActivity(openMap);
                }
        }

        public void openWebPage(String urlStr) {
                Log.d("DEBUG", "openWebPage called");
                Uri webPage = Uri.parse(urlStr);
                Intent openPage = new Intent(Intent.ACTION_VIEW, webPage);

                if (openPage.resolveActivity(getPackageManager()) != null) {
                        startActivity(openPage);
                }
        }
}