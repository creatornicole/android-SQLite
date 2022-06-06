package com.example.studio_sqlite;

import android.content.Context;
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

public class DataAdapter<DataModel> extends ArrayAdapter<String> {

    /**
     * Attributes
     */
    private Context mContext;
    private List<String> stringList = new ArrayList<>();

    /**
     * Constructor of Adapter
     *
     * @param context
     * @param list
     */
    public DataAdapter(@NonNull Context context, @NonNull List<DataModel> list) {
        super(context, 0, (List<String>) list);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        //get Elements of ListItem
        TextView text = listItem.findViewById(R.id.text);
        ImageButton delBtn = listItem.findViewById(R.id.delBtn);

        //get clicked item
        String current = stringList.get(position);
        //get Elements from ArrayList
        List<DataModel> list = (List<DataModel>) MainActivity.getList();
        //show clicked Item in ListView


        return null;
    }
}
