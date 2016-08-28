package com.english.howf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HoangDuy on 27/08/2016.
 */
public class MenuAdapter extends ArrayAdapter<Items> {

    public MenuAdapter(Context context, int resource, List<Items> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.menu_row, null);
        }
        Items p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView title = (TextView) view.findViewById(R.id.txttitle);
            ImageView imageView=(ImageView)view.findViewById(R.id.imgicon);
            title.setText(p.getTitle());
            imageView.setImageResource(p.getIcon());
        }
        return view;

    }
}
