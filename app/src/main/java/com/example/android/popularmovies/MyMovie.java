package com.example.android.popularmovies;

public class MyMovie {
    private String title;
    private String image;
    private String rate;
    private String date;
    private String overview;
    private String id;
    public MyMovie(){}

    public MyMovie(String title, String image, String rate, String date, String overview,String id){
        this.title = title;
        this.image = image;
        this.rate = rate;
        this.date = date;
        this.overview = overview;
        this.id = id;
    }

    public String getTitle(){return title;}
    public String getImage(){return image;}
    public String getRate(){return rate;}
    public String getDate(){return date;}
    public String getOverview(){return overview;}
    public String getId(){return id;}


    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public void setId(String id){this.id =id;}
}
