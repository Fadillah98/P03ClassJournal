package com.myapplicationdev.android.p03classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends ArrayAdapter<Modules> {

    private ArrayList<Modules> modules;
    private Context context;
    private TextView tvCode, tvName;

    public MainAdapter(Context context, int resource, ArrayList<Modules> objects) {
        super(context, resource, objects);
        modules = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_main, parent, false);

        tvCode = rowView.findViewById(R.id.textViewCode);
        tvName = rowView.findViewById(R.id.textViewName);

        Modules currPos = modules.get(position);

        tvCode.setText(currPos.getCode());
        tvName.setText(currPos.getName());

        return rowView;
    }


}
