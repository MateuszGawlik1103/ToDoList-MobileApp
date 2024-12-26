package com.example.taskmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String details = intent.getStringExtra("details");
        String dueDate = intent.getStringExtra("dueDate");
        String status = intent.getStringExtra("status");
        int position = intent.getIntExtra("position", -1);

        TextView titleView = findViewById(R.id.taskTitle);
        TextView detailsView = findViewById(R.id.taskDetails);
        TextView dueDateView = findViewById(R.id.taskDueDate);
        TextView statusView = findViewById(R.id.taskStatus);

        titleView.setText(title);
        detailsView.setText(details);
        dueDateView.setText(dueDate);

        if ("Pending".equals(status)) {
            if (isOverdue(dueDate)) {
                status = "Overdue";
            }
        }

        statusView.setText(status);

        Button doneButton = findViewById(R.id.doneButton);
        Button pendingButton = findViewById(R.id.pendingButton);

        String finalStatus = status;
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult("Done", position);
            }
        });

        pendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult("Pending", position);
            }
        });
    }

    private void sendResult(String newStatus, int position) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("status", newStatus);
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private boolean isOverdue(String dueDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date due = sdf.parse(dueDate);
            return new Date().after(due);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
