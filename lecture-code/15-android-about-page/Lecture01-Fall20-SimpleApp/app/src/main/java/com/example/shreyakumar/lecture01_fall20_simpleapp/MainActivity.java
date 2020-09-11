package com.example.shreyakumar.lecture01_fall20_simpleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private TextView mSearchResultsDisplay;
    private EditText mSearchTermEditText;
    private Button mSearchButton;
    private Button mResetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // access the visual elements
        mSearchResultsDisplay = (TextView) findViewById(R.id.tv_display_text);
        mSearchTermEditText   = (EditText) findViewById(R.id.et_search_box);
        mSearchButton         = (Button) findViewById(R.id.search_button);
        mResetButton          = (Button) findViewById(R.id.reset_button);

        mSearchResultsDisplay.append("\nHermione \nNeville\n");

        final String[] studentNames = {"Nicholas", "Katelyn", "Ryan", "Simon", "Estefania", "Bradley", "Jorge"};
        for(String name: studentNames){
            mSearchResultsDisplay.append("\n\n" + name);
        } // end of for

        final String defaultListString = mSearchResultsDisplay.getText().toString();


        // reset button
        mResetButton.setOnClickListener(
                new View.OnClickListener(){
                    // inner method
                    public void onClick(View v){
                        // reset the text
                        mSearchResultsDisplay.setText(defaultListString); // change to the default display text
                    } // end of onClick
                } // end of object OnClickListener
        ); // end of setOnClickListener

        // respond to search button
        mSearchButton.setOnClickListener(
                new View.OnClickListener(){
                    // inner method
                    public void onClick(View v){
                        // retrieve the search text
                        String searchText = mSearchTermEditText.getText().toString();
                        // filter the results and text view
                        for(String name: studentNames){
                            if(name.toLowerCase().equals(searchText.toLowerCase())){
                                mSearchResultsDisplay.setText(name);
                            }
                        }

                    } // end of onClick
                } // end of object OnClickListener
        ); // end of setOnClickListener

    } // end of onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu); // id of the menu resource that should be inflated
        return true;
    } // end of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuItemSelected = item.getItemId();

        if(menuItemSelected == R.id.menu_about){ // id from main_menu.xml for the About item


            //spl - launching activity in our app - then launch the About Activity
            Class destinationActivity = AboutActivity.class;

            // create intent to go to next page
            Intent startAboutActivityIntent = new Intent(MainActivity.this, destinationActivity);

            String msg = mSearchTermEditText.getText().toString();
            startAboutActivityIntent.putExtra(Intent.EXTRA_TEXT, msg);

            startActivity(startAboutActivityIntent);
            Log.d("info", "About Activity launched");
        } // end if
        return true;
    } // end of onOptions

} // end of MainActiivty class
