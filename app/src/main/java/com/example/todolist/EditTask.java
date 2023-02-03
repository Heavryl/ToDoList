package com.example.todolist;

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

public class EditTask extends AppCompatActivity {
    Toolbar toolbar;
    EditText tTitle,tContent, newDeadline;
    Calendar c;
    String todaysDate;

    long tId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        Intent i = getIntent();
        tId = i.getLongExtra("ID",0);
        TasksDatabase db = new TasksDatabase(this);
        Task task = db.getTask(tId);

        final String title = task.getTitle();
        String content = task.getContent();
        String deadline = task.getDeadline();
        tTitle = findViewById(R.id.taskTitle);
        tContent = findViewById(R.id.taskDetails);
        newDeadline = findViewById(R.id.endDate);


        tTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getActionBar().setTitle(title);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    getActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        tTitle.setText(title);
        tContent.setText(content);
        newDeadline.setText(deadline);
        // set current date and time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);

    }
    public interface DateValidator {
        boolean isValid(String dateStr);
    }

    public class DateValidatorUsingDateFormat implements AddTask.DateValidator {
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
        inflater.inflate(R.menu.edit_task_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.saveTask){

            DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("yyyy/MM/dd");
            boolean dateFormatOk = false;
            dateFormatOk = validator.isValid(newDeadline.getText().toString());
            if  (dateFormatOk) {
                Task task = new Task(tId, tTitle.getText().toString(), tContent.getText().toString(), todaysDate, newDeadline.getText().toString());
                TasksDatabase tDB = new TasksDatabase(getApplicationContext());
                long id = tDB.editTask(task);
                goToMain();
                Toast.makeText(this, "Task Edited.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Incorrect date, correct it in format yyyy/MM/dd.", Toast.LENGTH_SHORT).show();
            }
        }else if(item.getItemId() == R.id.cancelTask){
            Toast.makeText(this, "Changes canceled", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


}