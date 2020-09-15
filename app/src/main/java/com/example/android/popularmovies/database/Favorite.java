package com.example.android.popularmovies.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_table")
public class Favorite {

    @NonNull
    @PrimaryKey
    private String fid;
    private String ftitle;

    private String fimage;
    private String frate;
    private String fdate;
    private String foverview;


    public Favorite(String ftitle, String fimage, String frate, String fdate, String foverview,String fid){
        this.ftitle = ftitle;
        this.fimage = fimage;
        this.frate = frate;
        this.fdate = fdate;
        this.foverview = foverview;
        this.fid = fid;
    }




    public String getFtitle() {
        return ftitle;
    }

    public String getFimage() {
        return fimage;
    }

    public String getFrate() {
        return frate;
    }

    public String getFdate() {
        return fdate;
    }

    public String getFoverview() {
        return foverview;
    }

    public String getFid() {
        return fid;
    }
}
