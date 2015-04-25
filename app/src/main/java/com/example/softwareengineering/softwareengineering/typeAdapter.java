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
public class TypeAdapter extends ArrayAdapter<String> {
    Typeface myTypeface;
    List list = new ArrayList();
    public TypeAdapter(Context context, String[] values){
        super(context, R.layout.solution_types_listitem, values);
        AssetManager assets = context.getAssets();
        myTypeface = Typeface.createFromAsset(assets, "fonts/DK Cool Crayon.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if (v == null){
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.solution_types_listitem, null);
            holder = new ViewHolder();
            // cache view fields into the holder
            holder.solutionLabel = (TextView) v.findViewById(R.id.textView);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }


        String solutionType = getItem(position);

        holder.solutionLabel.setText(solutionType);
        holder.solutionLabel.setTypeface(myTypeface);


        return v;
    }

    @Override
    public void add(String object) {
        super.add(object);
        list.add(object);
    }

    static class ViewHolder{
        TextView solutionLabel;
    }
}
