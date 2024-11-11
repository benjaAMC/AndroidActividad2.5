package com.bmdi.taskmaster2;



import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Usuario extends AppCompatActivity {
    private EditText etNewTask;
    private Button btnAddTask;
    private ListView lvTasks;

    private ArrayList<String> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        etNewTask = findViewById(R.id.etNewTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        lvTasks = findViewById(R.id.lvTasks);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        lvTasks.setAdapter(taskAdapter);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etNewTask.getText().toString().trim();
                if (!task.isEmpty()) {
                    taskList.add(task);
                    taskAdapter.notifyDataSetChanged();
                    etNewTask.setText("");
                }
            }
        });
    }

    // Adapter personalizado para manejar tareas con CheckBox y botón de eliminar
    private class TaskAdapter extends ArrayAdapter<String> {
        public TaskAdapter(Usuario context, ArrayList<String> tasks) {
            super(context, 0, tasks);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
            }

            final String task = getItem(position);

            CheckBox cbTask = convertView.findViewById(R.id.cbTask);
            Button btnDeleteTask = convertView.findViewById(R.id.btnDeleteTask);

            cbTask.setText(task);
            cbTask.setOnCheckedChangeListener(null);
            cbTask.setChecked(false);

            btnDeleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taskList.remove(position);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }
}