package com.example.android.popularmovies.database;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

//SOURCE:https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#8
public class FavRepository {
    private FavDao mWordDao;
    private LiveData<List<Favorite>> mAllFavorites;

    FavRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mWordDao = db.favDao();
        mAllFavorites = mWordDao.loadAllMovies();
    }

        LiveData<List<Favorite>> getAllMovies() {
            return mAllFavorites;
        }

        void insertFav(Favorite favorite) {
            AppDatabase.databaseWriteExecutor.execute(() -> {
                mWordDao.insert(favorite);
            });
        }

    void deleteFav(Favorite favorite) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.delete(favorite);
        });
    }


    }

