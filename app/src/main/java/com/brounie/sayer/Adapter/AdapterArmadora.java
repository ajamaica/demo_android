package com.brounie.sayer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.brounie.sayer.Activities.ColoresActivity;
import com.brounie.sayer.Models.Armadora;
import com.brounie.sayer.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by ajamaica on 24/03/17.
 */

public class AdapterArmadora extends BaseAdapter implements StickyListHeadersAdapter {

    private List data;
    private LayoutInflater inflater;
    Context context;
    ArrayList<String> armadorasLocal;

    public AdapterArmadora(Context context,List data,ArrayList<String> armadoras) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.armadorasLocal = armadoras;



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
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_armadora, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.txtName);
            holder.logo = (CircleImageView) convertView.findViewById(R.id.logo);
            holder.item = (View) convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final Armadora armadora = (Armadora) data.get(position);

        holder.text.setText(armadora.getNameComplete());


        if(armadorasLocal.contains(armadora.getNameComplete().toLowerCase().replace(" ",""))){
            Glide.with(context).load("android.resource://com.brounie.sayer/drawable/"+armadora.getNameComplete().toLowerCase().replace(" ",""))
                    .into(holder.logo);
        }else{
            Glide.with(context).load("android.resource://com.brounie.sayer/drawable/logo").into(holder.logo);
        }







        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ColoresActivity.class);
                i.putExtra("objectId",armadora.getObjectId());
                context.startActivity(i);
            }
        });


        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.item_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.txtName);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        Armadora armadora = (Armadora) data.get(position);
        String headerText = "" + armadora.getNameComplete().subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        Armadora armadora = (Armadora) data.get(position);

        return armadora.getNameComplete().subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
        CircleImageView logo;
        View item;
    }

}