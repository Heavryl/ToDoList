package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TasksDatabase extends SQLiteOpenHelper
{


    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Tasks_db";
    private static final String DATABASE_TABLE = "Taskstable";

    //column names for database table
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_DEADLINE = "deadline";


    TasksDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String query = "CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_CONTENT + " TEXT," +
                KEY_DATE + " TEXT," +
                KEY_DEADLINE + " TEXT" + " )";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
            if (oldVersion >= newVersion)
                    return;

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public Long addTask(Task task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TITLE, task.getTitle());
        cv.put(KEY_CONTENT, task.getContent());
        cv.put(KEY_DATE, task.getDate());
        cv.put(KEY_DEADLINE, task.getDeadline());

        Long ID = db.insert(DATABASE_TABLE, null, cv);
        Log.d("Inserted", "ID-> " + ID);

        return ID;
    }

    public Task getTask(Long id)
    {
      SQLiteDatabase db = this.getWritableDatabase();
      String[] query = new String[] {KEY_ID,KEY_TITLE,KEY_CONTENT,KEY_DATE,KEY_DEADLINE};
      Cursor cursor = db.query(DATABASE_TABLE,query,KEY_ID +"=?",
              new String[]{String.valueOf(id)},null,null,null);
        if (cursor != null )
            cursor.moveToFirst();

        //getting data from database using cursor
        return new Task (
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }

    public List<Task> getAllTasks()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Task> allTasks = new ArrayList<>();

        String query = "SELECT * FROM " + DATABASE_TABLE + " ORDER BY " +KEY_ID+ " DESC"; //select all data from the database

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Task task = new Task();
                task.setID(cursor.getLong(0));
                task.setTitle(cursor.getString(1));
                task.setContent(cursor.getString(2));
                task.setDate(cursor.getString(3));
                task.setDeadline(cursor.getString(4));
                Log.d("ID: ", "ID-> " + task.getID());
                Log.d("ID: ", "Title-> " + task.getTitle());
                Log.d("ID: ", "Content-> " + task.getContent());
                Log.d("ID: ", "Date-> " + task.getDate());
                Log.d("ID: ", "Deadline > " + task.getDeadline());

                allTasks.add(task);

            }while(cursor.moveToNext());
        }
        return allTasks;
    }

    void deleteTask (Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public int editNote(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ task.getTitle() + "\n ID -> "+task.getID());
        c.put(KEY_TITLE,task.getTitle());
        c.put(KEY_CONTENT,task.getContent());
        c.put(KEY_DATE,task.getDate());
        c.put(KEY_DEADLINE,task.getDeadline());
        return db.update(DATABASE_TABLE,c,KEY_ID+"=?",new String[]{String.valueOf(task.getID())});
    }












}
