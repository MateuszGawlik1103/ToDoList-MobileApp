package com.example.taskmanagerapp.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.taskmanagerapp.R;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        }

        Task task = tasks.get(position);

        TextView titleView = convertView.findViewById(R.id.taskTitle);
        TextView dueDateView = convertView.findViewById(R.id.taskDueDate);
        View statusIndicator = convertView.findViewById(R.id.statusIndicator);

        titleView.setText(task.getTitle());
        dueDateView.setText(task.getDueDate());

        if ("Done".equals(task.getStatus())) {
            statusIndicator.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else if ("Pending".equals(task.getStatus())) {
            statusIndicator.setBackgroundColor(Color.parseColor("#F44336"));
        } else {
            statusIndicator.setBackgroundColor(Color.GRAY);
        }

        return convertView;
    }

}

