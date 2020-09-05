package com.example.android.popularmovies;

import android.graphics.Movie;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;
//SOURCE: Sunshine App

public class Utils {
    private static final String BASEURL = "https://api.themoviedb.org/3/movie";
    //PLEASE ADD THE API KEY HERE.

    private static final String apikey = "dee24302d32f6471cecba5b5b36e7512";

    public static URL buildUrl(String query) {
        Uri builtUri = Uri.parse(BASEURL).buildUpon()
                .appendPath(query)
                .appendQueryParameter("api_key", apikey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String getResponseFromHttpdUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static MyMovie[] parseMoviesJson(String json) throws JSONException {
        String TMDBURL = "https://image.tmdb.org/t/p/w500";
        JSONObject jsonObject = new JSONObject(json);
        JSONArray resultsArray = jsonObject.getJSONArray("results");
        MyMovie[] results = new MyMovie[resultsArray.length()];

        for (int i = 0; i < resultsArray.length(); i++) {
            MyMovie movie = new MyMovie();

            movie.setDate(resultsArray.getJSONObject(i).getString("release_date"));
            movie.setImage(TMDBURL + (resultsArray.getJSONObject(i).getString("poster_path")));
            movie.setOverview(resultsArray.getJSONObject(i).getString("overview"));
            movie.setRate(resultsArray.getJSONObject(i).getString("vote_average"));
            movie.setTitle(resultsArray.getJSONObject(i).getString("title"));
            movie.setId(resultsArray.getJSONObject(i).getString("id"));

            results[i] = movie;
        }
        return results;
    }

}


