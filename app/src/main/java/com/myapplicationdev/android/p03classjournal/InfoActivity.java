package com.myapplicationdev.android.p03classjournal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    ListView lvGrade;
    ArrayAdapter aaGrade;
    ArrayList<DailyCA> dailyGrade;
    Button btnInfo, btnAdd, btnEmail;
    String name, email, website;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        lvGrade = findViewById(R.id.lvDailyGrade);
        btnAdd = findViewById(R.id.buttonAdd);
        btnEmail = findViewById(R.id.buttonEmail);
        btnInfo = findViewById(R.id.buttonInfo);

        dailyGrade = new ArrayList<DailyCA>();

        Intent intentReceived = getIntent();
        name = intentReceived.getStringExtra("module_code");
        email = intentReceived.getStringExtra("email");
        website = intentReceived.getStringExtra("website");
        position = intentReceived.getIntExtra("pos", 0);

        setTitle("Info for " + name);

        if (position == 0) {
            dailyGrade.add(new DailyCA("B", "C347", 1));
            dailyGrade.add(new DailyCA("C", "C347", 2));
            dailyGrade.add(new DailyCA("A", "C347", 3));
        } else {
            dailyGrade.add(new DailyCA("B", "C302", 1));
            dailyGrade.add(new DailyCA("A", "C302", 2));
        }

        aaGrade = new ModuleAdapter(this, R.layout.row, dailyGrade);
        lvGrade.setAdapter(aaGrade);
        aaGrade.notifyDataSetChanged();

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);
                rpIntent.setData(Uri.parse(website));
                startActivity(rpIntent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Hi faci,\n\nI am ...\nPlease see my remarks so far, thank you!\n\n";
                for (int i = 0; i < dailyGrade.size(); i++){
                    if (dailyGrade.get(i).getModuleCode().equals(name)){
                        msg += "Week " + dailyGrade.get(i).getWeek() + ": DG: " + dailyGrade.get(i).getDgGrade();
                    }
                }

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

                emailIntent.setType("message/rfc822");

                startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 1;
                for (int i = 0; i < dailyGrade.size(); i++){
                    if (dailyGrade.get(i).getModuleCode().equals(name)){
                        count ++;
                    }
                }
                Intent intent = new Intent(getBaseContext(), AddActivity.class);
                intent.putExtra("week", count);
                intent.putExtra("name", name);
                startActivityForResult(intent, position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if (data != null) {
                DailyCA newGrade = (DailyCA) data.getSerializableExtra("newGrade");
                if(requestCode == position){
                    dailyGrade.add(newGrade);
                    aaGrade.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dailyGrade);
        prefEdit.putString("name", json);
        prefEdit.commit();
    }
}
