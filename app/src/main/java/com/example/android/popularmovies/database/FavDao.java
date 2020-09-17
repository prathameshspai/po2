package com.example.android.popularmovies.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Favorite favorite);

@Delete
void delete(Favorite favorite);

@Query("SELECT * FROM fav_table")
LiveData<List<Favorite>> loadAllMovies();

@Query("SELECT * FROM fav_table WHERE movie_id=:movieId")
    boolean isIncluded(String movieId);
}
