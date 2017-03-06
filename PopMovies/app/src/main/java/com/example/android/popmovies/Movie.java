package com.example.android.popmovies;

/**
 * Created by aschwartz on 3/3/2017.
 */

public class Movie {
    private String mPosterPath;
    private String mTitle;
    public Movie(String posterPath, String title){
        mPosterPath = formatUrl(posterPath);
        mTitle = title;
    }
    public String toString(){
        return mTitle+":"+mTitle;
    }

    public String formatUrl(String partialPath){
        String base = "http://image.tmdb.org/t/p/w185";
        return base + partialPath;
    }

    public String getPosterPath(){
        return mPosterPath;
    }

    public String getTitle(){
        return mTitle;
    }
}
