package com.myapplicationdev.android.p03classjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    TextView tvWeek;
    Button btnSubmit;
    RadioGroup rg;
    String name, grade;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intentReceived = getIntent();
        count = intentReceived.getIntExtra("week", 0);
        name = intentReceived.getStringExtra("name");

        tvWeek = findViewById(R.id.textViewWeek);
        btnSubmit = findViewById(R.id.buttonSubmit);
        rg = findViewById(R.id.rg);

        tvWeek.setText("Week " + count);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbA:
                        grade = "A";
                        break;
                    case R.id.rbB:
                        grade = "B";
                        break;
                    case R.id.rbC:
                        grade = "C";
                        break;
                    case R.id.rbD:
                        grade = "D";
                        break;
                    case R.id.rbF:
                        grade = "F";
                        break;
                    case R.id.rbX:
                        grade = "X";
                        break;
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                DailyCA newGrade = new DailyCA(grade, name, count);
                Intent intent = new Intent();
                intent.putExtra("newGrade", newGrade);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
