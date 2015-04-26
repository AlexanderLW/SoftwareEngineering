package com.example.softwareengineering.softwareengineering;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import domain.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/24/15.
 */
public class CardArrayAdapter extends ArrayAdapter<Card> {
    Typeface myTypeface;
    private ArrayList<Card> cardList = new ArrayList();

    static class CardViewHolder{
        TextView solutionLabel;
    }


    public CardArrayAdapter(Context context, int textViewResourceId){
        super(context, textViewResourceId);
        AssetManager assets = context.getAssets();
        myTypeface = Typeface.createFromAsset(assets, "fonts/DK Cool Crayon.ttf");
    }


    @Override
    public void add(Card object){
        cardList.add(object);
        super.add(object);
    }


    public void remove(Card object){
        cardList.remove(object);
        super.remove(object);
    }

    public void removeAll(){
        cardList.clear();
        super.clear();
    }

    @Override
    public int getCount(){
        return this.cardList.size();
    }


    @Override
    public Card getItem(int index) {
        return this.cardList.get(index);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CardViewHolder holder;

        if (v == null){
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_card, parent, false);
            holder = new CardViewHolder();
            // cache view fields into the holder
            holder.solutionLabel = (TextView) v.findViewById(R.id.textView);

            v.setTag(holder);
        } else {
            holder = (CardViewHolder) v.getTag();
        }

        Card solutionType = getItem(position);

        holder.solutionLabel.setText(solutionType.getData() + "\n");
        holder.solutionLabel.setTypeface(myTypeface);

        return v;
    }


    public Bitmap decodeToBitmap(byte[] decodedByte){
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


}
