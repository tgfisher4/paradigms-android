package com.example.shreyakumar.lecture01_android_summer20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        // connect with the visual elements in activity_main.xml
        mSearchResultsDisplay = (TextView) findViewById(R.id.tv_display_text);
        mSearchTermEditText = (EditText) findViewById(R.id.et_search_box);
        mSearchButton = (Button) findViewById(R.id.search_button);
        mResetButton = (Button) findViewById(R.id.reset_button);

        mSearchResultsDisplay.append("\nDwight\n\nHarry");

        final String[] studentNames = {"Matthew", "Rose", "Jack", "Claire", "Aram", "Andrew", "Sydney", "Chris", "Frank", "Annie"};
        for(String name : studentNames){
            mSearchResultsDisplay.append("\n\n"+ name);
        }

        final String defaultDisplayText = mSearchResultsDisplay.getText().toString();

        // responding to search button
        mSearchButton.setOnClickListener(
                new View.OnClickListener(){ // a unnamed object
                    //inner method def
                    public void onClick(View v){
                        //get search string from user
                        String searchText = mSearchTermEditText.getText().toString();
                        // check if search string matches
                        for(String name : studentNames){
                            if(name.toLowerCase().equals(searchText.toLowerCase())){
                                mSearchResultsDisplay.setText(name);
                                break;
                            }else{
                                mSearchResultsDisplay.setText("No results match.");
                            }
                        }
                    } // end of onClick method

                } // end of View.OnClickListener
        ); // end of setOnClickListener

        // responding to reset button
        mResetButton.setOnClickListener(
                new View.OnClickListener(){ // a unnamed object
                    //inner method def
                    public void onClick(View v){
                        // reset the text
                        mSearchResultsDisplay.setText(defaultDisplayText);

                    } // end of onClick method

                } // end of View.OnClickListener
        ); // end of setOnClickListener


    } // end of onCreate
} // end of MainActivity class
