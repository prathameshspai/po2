package com.example.android.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements PosterAdapter.PosterAdapterOnClickHandler{
    private RecyclerView recyclerView;
    private PosterAdapter posterAdapter;
    private MyMovie[] array;
    private TextView fail;
    private String nTitle,nDate,nRate,nImage,nOverview,nId;
    //SOURCE: Sunshine App

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fail= (TextView)findViewById(R.id.failed);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewID);

        //SOURCE:https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/GridLayoutManager
        int numOfColumns= 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns,RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(posterAdapter);
        new MovieTask().execute ("popular");
    }

    @Override
    public void onClick(int position) {
        //SOURCE:https://android.jlelse.eu/passing-data-between-activities-using-intent-in-android-85cb097f3016
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);

        nDate=array[position].getDate();
        nImage=array[position].getImage();
        nTitle=array[position].getTitle();
        nOverview=array[position].getOverview();
        nRate=array[position].getRate();
        nId=array[position].getId();

        intent.putExtra("title", nTitle);
        intent.putExtra("date",nDate);
        intent.putExtra("image",nImage);
        intent.putExtra("overview",nOverview);
        intent.putExtra("rate",nRate);
        intent.putExtra("id",nId);
        startActivity(intent);
    }

    public class MovieTask extends AsyncTask<String, Void, MyMovie[]>{

        @Override
        protected MyMovie[] doInBackground(String... strings) {
            if(strings.length ==0){
                return null;
            }

            URL movieUrl = Utils.buildUrl(strings[0]);
            try{
                String jsonMovieResponse = Utils.getResponseFromHttpUrl(movieUrl);
                array = Utils.parseMoviesJson(jsonMovieResponse);
                return array;

            } catch (JSONException | IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(MyMovie[] myMovies) {
            super.onPostExecute(myMovies);
            if(myMovies!=null){
                fail.setVisibility(View.GONE);
                recyclerView.setAdapter(new PosterAdapter(myMovies,MainActivity.this));
            }
            else{
                fail.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toprated) {
            new MovieTask().execute ("top_rated");
            return true;
        }
        if (id == R.id.popular) {
            new MovieTask().execute ("popular");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
