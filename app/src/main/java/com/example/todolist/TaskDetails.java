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

    TextView tDetails, chosenEndDate;
    TasksDatabase db;
    Task task;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        tDetails = findViewById(R.id.detailsOftask);
        chosenEndDate = findViewById(R.id.detailsDeadline);

        intent = getIntent();
        long id = intent.getLongExtra("ID", 0);

        db = new TasksDatabase(this);
        task = db.getTask(id);

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

        }
        if (item.getItemId() == R.id.detailsDeleteTask)
        {
            intent = getIntent();
            Long id = intent.getLongExtra("ID", 0);
            db = new TasksDatabase(this);
            task = db.getTask(id);
            db.deleteTask(task.getID());
            Toast.makeText(getApplicationContext(), "Task deleted.", Toast.LENGTH_SHORT).show();
            goToMain();
        }

        return super.onOptionsItemSelected(item);
    }



    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

