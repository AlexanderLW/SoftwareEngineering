package com.example.softwareengineering.softwareengineering;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 4/14/2015.
 */
public class typeAdapter extends ArrayAdapter<String> {
    Typeface myTypeface;
    List list = new ArrayList();
    public typeAdapter(Context context, String[] values){
        super(context, R.layout.row_layout, values);
        AssetManager assets = context.getAssets();
        myTypeface = Typeface.createFromAsset(assets, "fonts/DK Cool Crayon.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.row_layout, parent, false);

        String solutionType = getItem(position);
        TextView typeView = (TextView) theView.findViewById(R.id.textView);
        typeView.setText(solutionType);

        typeView.setTypeface(myTypeface);

        return theView;
    }

    @Override
    public void add(String object) {
        super.add(object);
        list.add(object);
    }
}
