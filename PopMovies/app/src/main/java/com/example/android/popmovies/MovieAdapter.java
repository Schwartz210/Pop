package com.example.android.popmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aschwartz on 3/3/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    final private ListItemClickListener mOnClickListener;
    private int mNumberItems;
    private ArrayList<Movie> mMovies;

    public MovieAdapter(ListItemClickListener listener){
        mOnClickListener = listener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(id, parent, false);
        MovieHolder holder = new MovieHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie currentMovie = mMovies.get(position);
        String poster = currentMovie.getPosterPath();
        Picasso.with(holder.itemView.getContext()).load(poster).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView mImageView;
        @Override
        public void onClick(View view) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }


        public MovieHolder(View itemView){
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image1);
            itemView.setOnClickListener(this);
        }

        void bind(int index){}
    }



    public void setMovieData(ArrayList<Movie> movies){
        mMovies = movies;
        mNumberItems = movies.size();
    }
}
