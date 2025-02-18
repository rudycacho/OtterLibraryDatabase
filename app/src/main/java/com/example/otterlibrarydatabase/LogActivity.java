// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.otterlibrarydatabase.databinding.ActivityLogBinding;

import java.util.Collections;
import java.util.List;

public class LogActivity extends AppCompatActivity{
    private LogAdapter logAdapter;
    private ActivityLogBinding  binding;
    private RecyclerView rv;
    private OtterLibraryDatabase db;
    private ConfirmationDialog confirmationDialog;
    List<Log> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        binding = ActivityLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        rv = binding.recyclerView;
        rv.setLayoutManager(new LinearLayoutManager(this));
        db = OtterLibraryDatabase.getInstance(this);

        Button okay = binding.okayButton;
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogActivity.this,AddBookActivity.class);
                startActivity(i);
                finish();
            }
        });

        this.logs = db.log().getAll();
        confirmationDialog = new ConfirmationDialog();
        fetchLogs();
    }

    private void fetchLogs(){
        Collections.reverse(logs);
        logAdapter = new LogAdapter(logs);
        rv.setAdapter(logAdapter);
    }

}