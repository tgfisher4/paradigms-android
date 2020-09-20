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

    public static ArrayList<String> parseSongsJSON(String songsResponse){
        ArrayList<String> songList = new ArrayList<>();
        try{
            JSONObject songsResObj = new JSONObject(songsResponse);
            JSONArray allSongsArr = songsResObj.getJSONArray("results");
            for (int i = 0; i < allSongsArr.length(); i++){
                JSONObject song = allSongsArr.getJSONObject(i);
                if(song.has("trackName")){
                    String name = song.getString("trackName");
                    songList.add(name);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
            Log.d("ERROR","JSON parsing error");
            System.out.println("JSON parsing error");
        }

        return songList;
    }

}
