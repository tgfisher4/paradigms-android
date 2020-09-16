package com.example.shreyakumar.pr_fall20_simple.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by shreyakumar on 9/11/20.
 */

public class NetworkUtils {

    // this method is specific to the countries URL
    public static URL buildCountriesUrl(){
        // get string url
        String countryUrlString = "https://api.openaq.org/v1/countries";
        URL countryUrl = null;
        try{
            countryUrl = new URL(countryUrlString);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return countryUrl;
    } // end of buil

    // this method can be used with any URL object
    public static String getResponseFromUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // getting the connection open
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A"); // delimiter for end of message
            boolean hasInput = scanner.hasNext();
            if(hasInput) return scanner.next(); // success
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }
        return null;
    } // end of get Resp

    public static ArrayList<String> parseCountriesJson(String countriesResponseString){
        //String [] countryList = new String[100];
        ArrayList<String> countryList = new ArrayList<String>();
        try{
            JSONObject allCountriesObject = new JSONObject(countriesResponseString);
            JSONArray allCountriesArray = allCountriesObject.getJSONArray("results");
            //countryList = new String[allCountriesArray.length()];
            for(int i = 0; i < allCountriesArray.length(); i++){
                JSONObject childJson = allCountriesArray.getJSONObject(i);
                // check if it has name
                if(childJson.has("name")){
                    String name = childJson.getString("name");
                    if(name != null) countryList.add(name);
                }
            } // end for
        } catch(JSONException e){
            e.printStackTrace();
        }
        return countryList;
    } // end of parse


}
