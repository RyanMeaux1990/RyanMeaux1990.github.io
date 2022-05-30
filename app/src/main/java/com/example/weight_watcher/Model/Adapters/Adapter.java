package com.example.weight_watcher.Model.Adapters;
/*
Adapter for Main Grid View
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.weight_watcher.Controllers.Controller.Main_Grid_View;
import com.example.weight_watcher.Model.GridViewRows.Row;
import com.example.weight_watcher.R;

public class Adapter extends BaseAdapter {
    private Context mContext;
    private Row[] rows;
    public Main_Grid_View gridView;
    public int gridRows = R.layout.selection_views;
    public int headers = R.layout.table_headers;
    public View headerView;


    public Adapter(Context context, Row[] row) {
        this.rows = row;
        this.mContext = context;


    }

    @Override
    public int getCount() {
        return rows.length;
    }

    @Override
    public Object getItem(int position) {
        return this.rows[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Row currentRow = rows[position];

        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.selection_views, null);

        TextView dateTextBox = convertView.findViewById(R.id.dateTextBox);
        TextView weightTextBox = convertView.findViewById(R.id.weightTextBox);
        TextView goalTextBox = convertView.findViewById(R.id.goalTextBox);

        Button editButtons = convertView.findViewById(R.id.editButton);
        Button deletebuttons = convertView.findViewById(R.id.deleteButton);

        editButtons.setTag(position + "E");
        deletebuttons.setTag(position + "D");

        editButtons.setOnClickListener(gridView.toEditAndDelete);
        deletebuttons.setOnClickListener(gridView.toEditAndDelete);

        weightTextBox.setText(String.valueOf(currentRow.weightThatDay));
        dateTextBox.setText(String.valueOf(currentRow.date));
        goalTextBox.setText(String.valueOf(currentRow.goalWeight));


        return convertView;
    }




}
