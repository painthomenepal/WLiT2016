package com.manishab.wlit2016;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 5/17/2016.
 */
public class CustomArrayAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Entity>entityArrayList;

    public CustomArrayAdapter(Context context, ArrayList<Entity> list) {
        this.entityArrayList=list;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return entityArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return entityArrayList.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null) {
            convertView = layoutInflater.inflate(R.layout.name_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.fullname);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(viewHolder);
        }else
            viewHolder=(ViewHolder)convertView.getTag();
        viewHolder.name.setText(entityArrayList.get(position).getName());
        viewHolder.address.setText(entityArrayList.get(position).getAddress());

        return convertView;
    }
    public static class ViewHolder{
        TextView name;
        TextView address;

    }
}
