package com.example.android.popmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {
    @Override
    public void onListItemClick(int clickedItemIndex) {}
    private RecyclerView mRecycler;
    private MovieAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread().execute();

    }

    public void manageGrid(ArrayList<Movie> movies){
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecycler.setLayoutManager(layoutManager);
        mAdapter = new MovieAdapter(this);
        mAdapter.setMovieData(movies);
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setVisibility(View.VISIBLE);
    }

    public ArrayList<Movie> createMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        String columns = "ABCDEFGHIJK";
        for (int row = 1; row<10; row++){
            for (int column = 0; column < columns.length(); column++){
                movies.add(new Movie(String.valueOf(columns.charAt(column)),String.valueOf(row)));
            }
        }
        return movies;
    }



    public class Thread extends AsyncTask<Void, Void, ArrayList<Movie>>{
        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            manageGrid(movies);
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            ArrayList<Movie> movies  = NetworkUtils.getData();
            return movies;
        }


    }
}
