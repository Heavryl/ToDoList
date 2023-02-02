package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        TasksDatabase db = new TasksDatabase(this);
        tasks = db.getAllTasks();

        recyclerView = findViewById(R.id.listOfTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this, tasks);
        recyclerView.setAdapter(adapter);
    }


       //menu override
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.addButton)
        {
            Intent i = new Intent(this, AddTask.class);
            startActivity(i);
            Toast.makeText(this, "Adding new task...", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}