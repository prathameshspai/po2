package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<MyReview> {


    public ReviewAdapter(@NonNull Context context, @NonNull List<MyReview> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.review_list_item, parent, false);
        }
        MyReview thisReview = getItem(position);
        TextView review_tv= (TextView) listItemView.findViewById(R.id.rlist_tv);
        review_tv.setText(thisReview.getReview_name());
        return listItemView;
    }
}
