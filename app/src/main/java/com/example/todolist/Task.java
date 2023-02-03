package com.example.todolist;

public class Task {
    private Long id;
    private String title;
    private String content;
    private String date;
    private String deadline;



    Task(String title, String content, String date, String deadline)
    {
        this.title = title;
        this.content = content;
        this.date = date;
        this.deadline = deadline;
    }

    Task(Long id, String title, String content, String date, String deadline)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.deadline = deadline;
    }

    Task(){} // empty constructor

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
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
