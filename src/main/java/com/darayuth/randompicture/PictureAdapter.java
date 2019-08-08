package com.darayuth.randompicture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PictureAdapter extends ArrayAdapter<Picture> {
    private Context mContext;
    private ArrayList<Picture> mList;
    private int mResource;

    public PictureAdapter(Context context, int resource, ArrayList<Picture> list){
        super(context, resource, list);
        this.mContext = context;
        this.mList = list;
        this.mResource = resource;
    }
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
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
        Picture currePicture = getItem(position);
        if(currePicture != null){
            viewHolder.mName.setText("Credit to  " + currePicture.getmName());
            viewHolder.mLocation.setText("Location: " + currePicture.getmLocation());
            Picasso.get()
                    .load(currePicture.getmImage())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.mImageView);
        }
        return row;
    }
    private class ViewHolder{
        final TextView mName;
        final TextView mLocation;
        final ImageView mImageView;

        ViewHolder(View v){
            this.mName = v.findViewById(R.id.name);
            this.mLocation = v.findViewById(R.id.location);
            this.mImageView = v.findViewById(R.id.image_view);
        }
    }
}
