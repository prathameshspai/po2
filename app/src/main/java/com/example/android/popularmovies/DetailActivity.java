package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private TextView mTitle, mDate, mRate, mOverview, mNotAvailable;
    private ImageView mImage;
    private String image, title,date,rate,overview,id;
    private URL traile,revie;
    private String[] t_name,t_key, r_name, r_review, r_Combined;
    ListView t_listView, combined;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        mImage=(ImageView)findViewById(R.id.imageID);
        mDate=(TextView)findViewById(R.id.date_tv);
        mRate=(TextView)findViewById(R.id.Rated_tv);
        mOverview=(TextView)findViewById(R.id.overview_tv);
        mTitle=(TextView)findViewById(R.id.titleID);

        title=getIntent().getStringExtra("title");
        date=getIntent().getStringExtra("date");
        rate=getIntent().getStringExtra("rate");
        overview=getIntent().getStringExtra("overview");
        image=getIntent().getStringExtra("image");
        id=getIntent().getStringExtra("id");
        String TRAILER_URL= "http://api.themoviedb.org/3/movie/"+id.toString()+"/videos?api_key="+getResources().getString(R.string.API_Key);
        String REVIEW_URL="https://api.themoviedb.org/3/movie/"+id.toString()+"/reviews?api_key="+getResources().getString(R.string.API_Key);
        mTitle.setText(title);
        mDate.setText(date);
        mOverview.setText(overview);
        mRate.setText(rate+"/10");

        //SOURCE:https://square.github.io/picasso/
        Picasso.get()
                .load(image)
                .into(mImage);

        try {
            traile = new URL(TRAILER_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new trailerAsyncTask().execute(traile);
        t_listView = findViewById(R.id.trailer_listview);
        t_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent t_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+t_key[position]));
                startActivity(t_intent);
            }
        });


        try {
            revie = new URL(REVIEW_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new reviewAsyncTask().execute(revie);
    } //.


    public class reviewAsyncTask extends AsyncTask<URL, Void, String>{
        @Override
        protected void onPostExecute(String s) {
            if(r_name.length!=0) {
                combined = findViewById(R.id.review_listview);
                ArrayList<MyReview> review_list = new ArrayList<>();

                for (int i = 0; i < r_name.length; i++) {
                    review_list.add(new MyReview(r_Combined[i]));
                }

                ReviewAdapter r_adapter = new ReviewAdapter(DetailActivity.this, review_list);
                combined.setAdapter(r_adapter);

            }
       }

        @Override
        protected String doInBackground(URL... urlsr) {
            URL urlr = urlsr[0];
            String review = null;

            try{

                review = Utils.getResponseFromHttpUrl(urlr);
                JSONObject jsonOb = new JSONObject(review);
                JSONArray result_Array = jsonOb.getJSONArray("results");

                r_name= new String[result_Array.length()];
                r_review= new String[result_Array.length()];

                for(int i=0;i<result_Array.length();i++){
                    r_name[i]=result_Array.getJSONObject(i).getString("author");
                    r_review[i]=result_Array.getJSONObject(i).getString("content");
                    r_Combined[i]=""+r_name[i]+": \n"+r_review[i];

                }


            } catch (IOException | JSONException e) {
                Log.e("DetailActivity", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
    }


public class trailerAsyncTask extends AsyncTask<URL, Void, String> {
    @Override
    protected void onPostExecute(String s) {
        if(t_name.length!=0) {
            t_listView = findViewById(R.id.trailer_listview);
            ArrayList<MyTrailer> trailer_list = new ArrayList<>();

            for (int i = 0; i < t_name.length; i++) {
                trailer_list.add(new MyTrailer(t_name[i]));
            }

            TrailerAdapter adapter = new TrailerAdapter(DetailActivity.this, trailer_list);
            t_listView.setAdapter(adapter);

        }

        else{
            mNotAvailable=(TextView)findViewById(R.id.not_Availabe);
            mNotAvailable.setVisibility(View.VISIBLE);
            t_listView = findViewById(R.id.trailer_listview);
            t_listView.setVisibility(View.GONE);

        }
    }

    @Override
    protected String doInBackground(URL... urls) {

    URL url = urls[0];
    String trailer = null;

        try{

            trailer = Utils.getResponseFromHttpUrl(url);
            JSONObject jsonObj = new JSONObject(trailer);

            JSONArray resultArray = jsonObj.getJSONArray("results");

             t_name= new String[resultArray.length()];
             t_key= new String[resultArray.length()];

            for(int i=0;i<resultArray.length();i++){
                t_name[i]=resultArray.getJSONObject(i).getString("name");
                t_key[i]=resultArray.getJSONObject(i).getString("key");
                Log.v("TAG","The names are " + t_name[i]);
            }


        } catch (IOException | JSONException e) {
            Log.e("DetailActivity", e.getMessage());
            e.printStackTrace();
        }

return null;
    }

    }
}