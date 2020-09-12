package com.example.android.popularmovies.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_table")
public class Favorite {

    @PrimaryKey
    private int f_Id;
    private String f_Title;
    @ColumnInfo(name = "image_url")
    private String image_url;

    public Favorite(int f_Id,String f_Title,String image_url){
        this.f_Id = f_Id;
        this.f_Title = f_Title;
        this.image_url = image_url;
    }


    public String getF_Title() {
        return f_Title;
    }

    public int getF_Id() {
        return f_Id;
    }

    public String getImage_url() {
        return image_url;
    }
}
