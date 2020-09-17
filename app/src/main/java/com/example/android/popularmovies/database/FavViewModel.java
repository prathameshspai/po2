package com.example.android.popularmovies.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//SOURCE:https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#9
public class FavViewModel extends AndroidViewModel {

    private FavRepository mRepository;

    private LiveData<List<Favorite>> mAllFavorites;

    public FavViewModel (Application application) {
        super(application);
        mRepository = new FavRepository(application);
        mAllFavorites = mRepository.getAllMovies();
    }

    public LiveData<List<Favorite>> getAllMovies() { return mAllFavorites; }
    public boolean isIncluded(String movieId){return isIncluded(movieId);}
    public void insertFav(Favorite word) { mRepository.insertFav(word); }
    public void deleteFav(Favorite word) { mRepository.insertFav(word); }
}



