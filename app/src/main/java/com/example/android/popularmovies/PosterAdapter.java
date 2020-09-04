package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

//SOURCE: https://stackoverflow.com/questions/40587168/simple-android-grid-example-using-recyclerview-with-gridlayoutmanager-like-the
//SOURCE: Sunshine App

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder> {
        private MyMovie[] movieData;
        private final PosterAdapterOnClickHandler mClickHandler;

        public interface PosterAdapterOnClickHandler {
            void onClick(int position);
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.activity_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //SOURCE:https://square.github.io/picasso/
        Picasso.get()
                .load(movieData[position].getImage())
                .into(holder.movieImageView);
    }

    @Override
    public int getItemCount() {
        if (null == movieData) return 0;
        return movieData.length;
    }

    public PosterAdapter (MyMovie[] movie, PosterAdapterOnClickHandler clickHandler){
            movieData=movie;
            mClickHandler=clickHandler;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView movieImageView;

            ViewHolder(View item) {
                super(item);
                movieImageView = (ImageView) item.findViewById(R.id.main_list_ID);
                item.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                mClickHandler.onClick(getAdapterPosition());
            }
        }

}
