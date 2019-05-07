package com.myapplicationdev.android.p03classjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvClasses;
    ArrayList<Modules> alClasses;
    ArrayAdapter aaClasses;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvClasses = findViewById(R.id.lvClasses);

        alClasses = new ArrayList<>();
        alClasses.add(new Modules("C347", "Android Programming II"));
        alClasses.add(new Modules("C302", "Web Services"));

        aaClasses = new MainAdapter(this, R.layout.row_main, alClasses);

        lvClasses.setAdapter(aaClasses);

        lvClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String code = alClasses.get(position).getCode();
                pos = parent.getPositionForView(view);
                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                if (pos == 0){
                    intent.putExtra("module_code", code);
                    intent.putExtra("email", "andy_tao@rp.edu.sg");
                    intent.putExtra("website", "https://www.rp.edu.sg/schools-courses/courses/full-time-diplomas/full-time-courses/modules/index/C347");
                    intent.putExtra("pos", pos);
                } else {
                    intent.putExtra("module_code", code);
                    intent.putExtra("email", "charissa_chua@rp.edu.sg");
                    intent.putExtra("website", "https://www.rp.edu.sg/schools-courses/courses/full-time-diplomas/full-time-courses/modules/index/C302");
                    intent.putExtra("pos", pos);
                }
                startActivity(intent);
            }
        });
    }
}
