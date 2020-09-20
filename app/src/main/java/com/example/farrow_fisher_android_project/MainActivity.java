package com.example.farrow_fisher_android_project;

import com.example.farrow_fisher_android_project.utils.NetworkUtils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private TextView myTitle;
    private EditText myQuery;
    private Button mySearch;
    private Button myClear;
    private TextView myListInfo;
    private TextView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTitle = (TextView) findViewById(R.id.my_title);
        myQuery = (EditText) findViewById(R.id.my_query);
        mySearch = (Button) findViewById(R.id.my_search);
        myClear = (Button) findViewById(R.id.my_clear);
        myListInfo = (TextView) findViewById(R.id.my_list_info);
        myList = (TextView) findViewById(R.id.my_list);

        // make network call
        queryNetwork("");

        mySearch.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        // make network call using the query
                        String query = myQuery.getText().toString();
                        queryNetwork(query);
                    }
                }
        );

        myClear.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        // make network call
                        queryNetwork("");
                        // clear search bar
                        myQuery.setText("");
                    }
                }
        );

    }

    public void queryNetwork(String searchTerm){
        // calls doInBackground behind the scenes
        new FetchNetworkData().execute(searchTerm);
    }

    // inner class
    public class FetchNetworkData extends AsyncTask<String, Void, String> {
        @Override
        // makes networking call and waits, passes result to onPostExecute
        protected String doInBackground(String... strings) {
            // make network call
            if (strings.length == 0){
                Log.d("ERROR", "no query parameter to doInBackground");
                return null;
            }

            String searchTerm = strings[0];
            try {
                return searchTerm + "!START!JSON!" + NetworkUtils.getResponseFromURL(NetworkUtils.buildSongsURL());
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        // executed with result from doInBackground once finished
        protected void onPostExecute(String response) {
            // update UI
            super.onPostExecute(response);
            String[] queryAndJSON = response.split("!START!JSON!");
            if (queryAndJSON.length != 2){
                Log.d("ERROR", queryAndJSON.length + "tokens in doInBackground response (expected 2)");
                myList.setText("JSON parsing error. Sorry about that.");
            }
            String userQuery = queryAndJSON[0];
            String songJSON = queryAndJSON[1];

            if(songJSON.length() == 0){
                Log.d("ERROR", "JSON response empty, likely because of network error");
                myList.setText("Unable to retrieve JSON. Sorry about that.");
            }
            HashSet<String> songs = NetworkUtils.parseSongsJSON(songJSON);

            Log.d("INFO", "userQuery: " + userQuery);
            Log.d("INFO", "JSON: " + songJSON);

            myListInfo.setText( (userQuery.length() > 0
                                ? "Led Zeppelin Songs containing '"+ userQuery + "':"
                                : "Led Zeppelin Songs (in no particular order):")
                                + "\n\n");
                                //+                 "\n---------------------------------------------------\n");

            StringBuilder toDisplay = new StringBuilder();

            for(String song: songs){
                if(userQuery.length() == 0 || song.toLowerCase().contains(userQuery.toLowerCase())){
                    toDisplay.append(song + "\n\n");
                }
            }

            // lop off trailing space
            myList.setText(toDisplay.substring(0, toDisplay.length() - 2));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int menuItemSelected = item.getItemId();
        if(menuItemSelected == R.id.menu_about){
            Class dest = com.example.farrow_fisher_android_project.AboutActivity.class;

            Intent launchDest = new Intent(MainActivity.this, dest);
            String msg = myQuery.getText().toString();
            launchDest.putExtra(Intent.EXTRA_TEXT, msg);

            startActivity(launchDest);
            Log.d("info", "about activity launched");
        }

        return true;
    }
}