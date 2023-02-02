package com.example.todolist;

public class Task {
    private long ID;
    private String title;
    private String content;
    private String date;
    private String deadline;

    Task(){}

    Task(String title, String content, String date, String deadline)
    {
        this.title = title;
        this.content = content;
        this.date = date;
        this.deadline = deadline;
    }

    Task(long id, String title, String content, String date, String deadline)
    {
        this.ID = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.deadline = deadline;
    }



    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
