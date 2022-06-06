package com.example.studio_sqlite;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Adapter for Customized Design of ListView-Items
 *
 * @author Nicole Gottschall
 * @since 2022-06-06
 */

public class DataAdapter extends ArrayAdapter<DataModel> {

    /**
     * Attributes
     */
    private Context mContext;
    private int mResource;

    public DataAdapter(Context context, int resource, ArrayList<DataModel> list) {
        super(context, resource, list);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the objects information
        String title = getItem(position).getTitle();
        String description = getItem(position).getDesription();
        boolean isImportant = getItem(position).isImportant();

        //create object with the information
        DataModel model = new DataModel(title, description, isImportant);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //get TextViews
        TextView tvTitle = (TextView) convertView.findViewById(R.id.task);

        //set information to TextViews
        tvTitle.setText(title + ", " + description + ", " + new Boolean(isImportant).toString());

        return convertView;
    }
}
