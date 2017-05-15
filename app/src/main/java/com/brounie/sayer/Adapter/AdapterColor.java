package com.brounie.sayer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.brounie.sayer.Activities.ColoresActivity;
import com.brounie.sayer.Activities.PerfilColorActivity;
import com.brounie.sayer.Models.Armadora;
import com.brounie.sayer.Models.Color;
import com.brounie.sayer.R;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by ajamaica on 27/03/17.
 */

public class AdapterColor extends BaseAdapter implements StickyListHeadersAdapter {

    private List data;
    private LayoutInflater inflater;
    Context context;

    public AdapterColor(Context context,List data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterColor.ViewHolder holder;

        if (convertView == null) {
            holder = new AdapterColor.ViewHolder();
            convertView = inflater.inflate(R.layout.item_armadora, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.txtName);
            holder.logo = (CircleImageView) convertView.findViewById(R.id.logo);
            holder.color = (View) convertView.findViewById(R.id.color);
            holder.item = (View) convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        } else {
            holder = (AdapterColor.ViewHolder) convertView.getTag();
        }


        final Color color = (Color) data.get(position);

        holder.text.setText(color.getColorName());



        int strokeWidth = 5; // 3px not dp
        int roundRadius = 100; // 8px not dp
        int strokeColor = android.graphics.Color.parseColor("#"+color.getHexadecimal());
        int fillColor = android.graphics.Color.parseColor("#"+color.getHexadecimal());

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColor);


        //holder.color.setBackgroundColor(android.graphics.Color.parseColor("#"+color.getHexadecimal()));
        holder.color.setBackground(gd);

        Glide.with(context).load("android.resource://com.brounie.sayer/drawable/basecolor").into(holder.logo);


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PerfilColorActivity.class);
                i.putExtra("colorName",color.getColorName());
                i.putExtra("hexadecimal","#"+color.getHexadecimal());
                i.putExtra("colorCode",color.getColorCode());
                i.putExtra("fondoLocal",color.getColCode());
                context.startActivity(i);
            }
        });


      /*  Drawable myIcon = context.getResources().getDrawable( R.drawable.basecolor );
        ColorFilter filter = new LightingColorFilter( android.graphics.Color.parseColor("#"+color.getHexadecimal()), android.graphics.Color.parseColor("#"+color.getHexadecimal()) );
        myIcon.setColorFilter(filter);
        holder.logo.setImageDrawable(myIcon);*/


        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        AdapterColor.HeaderViewHolder holder;
        if (convertView == null) {
            holder = new AdapterColor.HeaderViewHolder();
            convertView = inflater.inflate(R.layout.item_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.txtName);
            convertView.setTag(holder);
        } else {
            holder = (AdapterColor.HeaderViewHolder) convertView.getTag();
        }

        Color color = (Color) data.get(position);
        String headerText = "" + color.getColorName().subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        Color color = (Color) data.get(position);

        return color.getColorName().subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
        CircleImageView logo;
        View color;
        View item;
    }

}