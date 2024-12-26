package com.example.taskmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanagerapp.model.Task;
import com.example.taskmanagerapp.model.TaskAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;
    private ListView taskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskListView = findViewById(R.id.taskListView);

        // Creating task list
        taskList = new ArrayList<>();
        taskList.add(new Task("Task 1", "Task 1 details", "2024-12-31", "Pending"));
        taskList.add(new Task("Task 2", "Task 2 details", "2024-12-28", "Done"));
        taskList.add(new Task("Task 3", "Task 3 details", "2024-12-25", "Pending"));

        // Creating adapter and assigning it to ListView
        taskAdapter = new TaskAdapter(this, taskList);
        taskListView.setAdapter(taskAdapter);

        // Click handling on task
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task selectedTask = taskList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("title", selectedTask.getTitle());
                intent.putExtra("details", selectedTask.getDetails());
                intent.putExtra("dueDate", selectedTask.getDueDate());
                intent.putExtra("status", selectedTask.getStatus());
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String newStatus = data.getStringExtra("status");
            int position = data.getIntExtra("position", -1);

            if (position != -1) {
                Task task = taskList.get(position);
                task.setStatus(newStatus);
                taskAdapter.notifyDataSetChanged();
            }
        }
    }
}
