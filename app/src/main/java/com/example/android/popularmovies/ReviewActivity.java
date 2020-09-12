package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {
    private URL revie;
    private String id;
    private String[]  r_name, r_review, r_Combined;
    private ListView combined;
    private TextView not;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        id=getIntent().getStringExtra("id2");
        String REVIEW_URL="https://api.themoviedb.org/3/movie/"+id.toString()+"/reviews?api_key="+getResources().getString(R.string.API_Key);

        try {
            revie = new URL(REVIEW_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new reviewAsyncTask().execute(revie);
    }





    public class reviewAsyncTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            if(r_name.length!=0) {
                combined = findViewById(R.id.review_listview);
                ArrayList<MyReview> review_list = new ArrayList<>();

                for (int i = 0; i < r_name.length; i++) {
                    review_list.add(new MyReview(r_Combined[i]));
                }

                ReviewAdapter r_adapter = new ReviewAdapter(ReviewActivity.this, review_list);
                combined.setAdapter(r_adapter);

            }
            else{
                not =(TextView)findViewById(R.id.rev_notavailable);
                combined = findViewById(R.id.review_listview);
                not.setVisibility(View.VISIBLE);
                combined.setVisibility(View.GONE);
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
                r_Combined=new String[result_Array.length()];

                for(int i=0;i<result_Array.length();i++){
                    r_name[i]=result_Array.getJSONObject(i).getString("author");
                    r_review[i]=result_Array.getJSONObject(i).getString("content");
                    r_Combined[i]=""+r_name[i]+": \n\n\t\t\t\t\t"+r_review[i]+"\n\n\n";

                }


            } catch (IOException | JSONException e) {
                Log.e("DetailActivity", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
    }
}