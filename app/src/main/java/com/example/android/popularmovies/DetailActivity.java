package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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
    private TextView mTitle, mDate, mRate, mOverview;
    private ImageView mImage;
    private String image, title,date,rate,overview,id;
    private URL traile;
    private String[] t_name,t_key;
    ListView t_listView;
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

        if(t_name!=null) {

        }
    } //.



public class trailerAsyncTask extends AsyncTask<URL, Void, String> {

    @Override
    protected String doInBackground(URL... urls) {

    URL url = urls[0];
    String trailer = null;

        try{
            trailer = Utils.getResponseFromHttpUrl(url);
            JSONObject jsonObject = new JSONObject(trailer);
            JSONArray resultArray = jsonObject.getJSONArray("results");

             t_name= new String[resultArray.length()];
             t_key= new String[resultArray.length()];

            for(int i=0;i<resultArray.length();i++){
                t_name[i]=resultArray.getJSONObject(i).getString("name");
                t_key[i]=resultArray.getJSONObject(i).getString("key");

            }
            t_listView = findViewById(R.id.trailer_listview);
            ArrayList<MyTrailer> trailer_list = new ArrayList<>();

            for (int i = 0; i < t_name.length; i++) {
                trailer_list.add(new MyTrailer(t_name[i]));
            }

            TrailerAdapter adapter = new TrailerAdapter(DetailActivity.this, trailer_list);
            t_listView.setAdapter(adapter);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
return null;
    }

    }
}