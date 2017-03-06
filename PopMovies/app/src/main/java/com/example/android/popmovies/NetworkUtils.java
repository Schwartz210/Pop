package com.example.android.popmovies;

import android.net.Uri;
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

/**
 * Created by aschwartz on 3/6/2017.
 */

public class NetworkUtils {
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/popular?";
    private static String API_KEY = "1808b9699aaf8a0a5d2656fadf8a3799";
    public static URL buildUrl(){
        Uri buildUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("api_key",API_KEY)
                .build();
        URL url = null;
        try{
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String getJSON(){
        URL url = buildUrl();
        String json = null;
        try{
            json = getResponseFromHttpUrl(url);
        } catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    public static ArrayList<Movie> getData(){
        String stringJSON = getJSON();
        ArrayList<Movie> movies = new ArrayList<>();
        try{
            JSONObject json = new JSONObject(stringJSON);
            JSONArray array = json.getJSONArray("results");
            Log.i("monkey", array.length() + "");
            for (int i = 0; i < array.length(); i++){
                JSONObject currentMovie = array.getJSONObject(i);
                String title = currentMovie.getString("title");
                String posterPath = currentMovie.getString("poster_path");
                movies.add(new Movie(posterPath, title));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return movies;
    }
}
