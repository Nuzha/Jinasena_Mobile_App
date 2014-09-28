package com.NIRR.jenasena;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<Item>{
	Context context;
	 
    public CustomListViewAdapter(Context context, int resourceId,
            List<Item> items) {
        super(context, resourceId, items);
        this.context = context;
    }
    
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Item rowItem = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_item, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.tv_menu_item_hedder_Isuru);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_item_icon_Isuru);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
 
        holder.txtTitle.setText(rowItem.getTitle());
        holder.imageView.setImageResource(rowItem.getImageId());
 
        return convertView;
    }

}