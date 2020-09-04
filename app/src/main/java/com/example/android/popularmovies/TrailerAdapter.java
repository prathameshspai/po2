package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
//SOURCE: Miwok App (Android Basics)
public class TrailerAdapter extends ArrayAdapter<MyTrailer> {


    public TrailerAdapter(@NonNull Context context, @NonNull List<MyTrailer> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.trailer_list_item, parent, false);
        }
        MyTrailer thisTrailer = getItem(position);
        TextView trailer_tv= (TextView) listItemView.findViewById(R.id.list_tv);
        trailer_tv.setText(thisTrailer.getTrailer_name());
        return listItemView;
}

}
