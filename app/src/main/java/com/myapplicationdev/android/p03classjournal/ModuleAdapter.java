package com.myapplicationdev.android.p03classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ModuleAdapter extends ArrayAdapter<DailyCA> {
    private ArrayList<DailyCA> dailyGrade;
    private Context context;
    private TextView tvWeek, tvGrade;

    public ModuleAdapter(Context context, int resource, ArrayList<DailyCA> objects) {
        super(context, resource, objects);
        dailyGrade = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvWeek = rowView.findViewById(R.id.tvWeek);
        tvGrade = rowView.findViewById(R.id.tvGrade);

        DailyCA currPos = dailyGrade.get(position);

        tvWeek.setText("Week "  + currPos.getWeek());
        tvGrade.setText(currPos.getDgGrade());

        return rowView;
    }

}
