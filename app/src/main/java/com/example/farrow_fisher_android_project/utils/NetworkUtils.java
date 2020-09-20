package com.example.farrow_fisher_android_project.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class NetworkUtils {

    public static URL buildSongsURL(){

        URL songsURL = null;
        String strURL = "https://itunes.apple.com/search?term=led+zeppelin&entity=song&attribute=artistTerm";
        try {
            songsURL = new URL(strURL);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }

        return songsURL;
    }

    public static String getResponseFromURL(URL url) throws IOException{
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = connect.getInputStream();
            Scanner s = new Scanner(in);
            s.useDelimiter("\\A");
            if (s.hasNext()) return s.next();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // use a set instead of an array list since a song may appear multiple times in this list
    //  - for example, the same song can be on multiple albums
    public static HashSet<String> parseSongsJSON(String songsResponse){
        HashSet<String> songSet = new HashSet<>();
        try{
            JSONObject songsResObj = new JSONObject(songsResponse);
            JSONArray allSongsArr = songsResObj.getJSONArray("results");
            for (int i = 0; i < allSongsArr.length(); i++){
                JSONObject song = allSongsArr.getJSONObject(i);
                if(song.has("trackName")){
                    String name = song.getString("trackName");
                    songSet.add(name);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
            Log.d("ERROR","JSON parsing error");
            System.out.println("JSON parsing error");
        }

        return songSet;
    }

}
