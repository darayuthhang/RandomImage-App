package com.darayuth.randompicture;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String[] mCatgories;
    private int mResource;

    public CustomAdapter(Context context, int resource, String[]data){
        super(context, resource, data);
        this.mContext = context;
        this.mCatgories = data;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder;
        if(row == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }
        //set the background, and text color for lists
        //in categories.
        if (mCatgories[position].equals("Film")) {
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_office));
            viewHolder.mCatgorie.setTextColor(Color.WHITE);
        }else if(mCatgories[position].equals("Nature")){
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_office));
            viewHolder.mCatgorie.setTextColor(Color.WHITE);
        } else if (mCatgories[position].equals("Animal")) {
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_animal));
            viewHolder.mCatgorie.setTextColor(ContextCompat.getColor(getContext(), R.color.blue));
        }else if(mCatgories[position].equals("Office")){
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_office));
            viewHolder.mCatgorie.setTextColor(Color.WHITE);
        }else if(mCatgories[position].equals("Travel")){
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_travel));
            viewHolder.mCatgorie.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }else if(mCatgories[position].equals("FoodDrink")){
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_office));
            viewHolder.mCatgorie.setTextColor(Color.WHITE);
        } else if (mCatgories[position].equals("People")) {
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_people));
            viewHolder.mCatgorie.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
        } else if (mCatgories[position].equals("Arts-Culture")) {
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_office));
            viewHolder.mCatgorie.setTextColor(Color.WHITE);
        } else if (mCatgories[position].equals("Spirituality")) {
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_nature));
            viewHolder.mCatgorie.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
        } else if (mCatgories[position].equals("Architecture")) {
            viewHolder.mCatgorie.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shadow_button_for_animal));
            viewHolder.mCatgorie.setTextColor(ContextCompat.getColor(getContext(), R.color.blue));
        }
            viewHolder.mCatgorie.setText(mCatgories[position]);

        return row;
    }

    private class ViewHolder{
        final TextView mCatgorie;
        ViewHolder(View v){
            this.mCatgorie = v.findViewById(R.id.categorieView);

        }
    }
}
