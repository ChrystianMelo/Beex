package com.example.beex.view.schedule;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beex.R;

public class ScheduleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ScheduleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        recyclerView = findViewById(R.id.rv_schedule_recyclerServices);
        adapter = new ScheduleAdapter();

        adaptReciclerView();
    }

    public void adaptReciclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
    
    public void goBack(View v){
        finish();
    }
}
