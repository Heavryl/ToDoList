package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTask extends AppCompatActivity {

    Toolbar toolbar;
    EditText taskTitle, taskDetails, chosenEndDate;
    Calendar cal;
    String currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_task);

        toolbar = findViewById(R.id.toolbar);

        setActionBar(toolbar);
        getActionBar().setTitle("New Task");
        getActionBar().setDisplayHomeAsUpEnabled(true);

        taskTitle = findViewById(R.id.taskTitle);
        taskDetails = findViewById(R.id.taskDetails);
        chosenEndDate = findViewById(R.id.endDate);

        taskTitle.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (charSequence.length() !=0)
                {
                    getActionBar().setTitle(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });


        //getting current date and time
        cal = Calendar.getInstance();
        currentDate = cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH);
        chosenEndDate.setText(currentDate);
    }

    public interface DateValidator {
        boolean isValid(String dateStr);
    }

    public class DateValidatorUsingDateFormat implements DateValidator {
        private String dateFormat;

        public DateValidatorUsingDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public boolean isValid(String dateStr) {
            DateFormat sdf = new SimpleDateFormat(this.dateFormat);
            sdf.setLenient(false);
            try {
                sdf.parse(dateStr);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.save_task)
        {
            DateValidator validator = new DateValidatorUsingDateFormat("yyyy/MM/dd");
            boolean dateFormatOk = false;
            dateFormatOk = validator.isValid(chosenEndDate.getText().toString());
            if  (dateFormatOk)
            {
                Task task = new Task(taskTitle.getText().toString(), taskDetails.getText().toString(), currentDate, chosenEndDate.getText().toString());
                TasksDatabase db = new TasksDatabase( this);
                db.addTask(task);

                Toast.makeText(this, "Saving task...", Toast.LENGTH_SHORT).show();
                goToMain();
            }
            else
            {
                Toast.makeText(this, "Incorrect date format, should be yyyy/MM/dd.", Toast.LENGTH_SHORT).show();
            }

        }
        if (item.getItemId() == R.id.delete_task)
        {

            onBackPressed();
            Toast.makeText(this, "Deleting task...", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}