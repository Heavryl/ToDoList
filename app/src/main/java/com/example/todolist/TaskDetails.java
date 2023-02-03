package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


public class TaskDetails extends AppCompatActivity {
    Long id;

    TextView tDetails, chosenEndDate;

    Task task;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_details);



        Intent intent = getIntent();
        id = intent.getLongExtra("ID", 0);

        TasksDatabase db = new TasksDatabase(this);
        task = db.getTask(id);

        tDetails = findViewById(R.id.detailsOfTask);
        chosenEndDate = findViewById(R.id.detailsDeadline);

        toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle(task.getTitle());

        tDetails.setText(task.getContent());
        chosenEndDate.setText(task.getDeadline());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.detailsEditTask)
        {
            Intent i = new Intent(this,EditTask.class);
            i.putExtra("ID",id);
            startActivity(i);
        }
        if (item.getItemId() == R.id.detailsDeleteTask)
        {
            Intent intent = getIntent();
            Long id = intent.getLongExtra("ID", 0);
            TasksDatabase db = new TasksDatabase(this);
            task = db.getTask(id);
            db.deleteTask(task.getID());
            Toast.makeText(getApplicationContext(), "Task deleted.", Toast.LENGTH_SHORT).show();
            goToMain();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

