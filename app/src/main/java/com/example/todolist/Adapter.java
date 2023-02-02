package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{

    LayoutInflater inflater;
    List<Task> tasks;
    Adapter (Context context, List<Task> tasks){
        this.inflater = LayoutInflater.from(context);
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //binding custom list view to recycle view
        View view = inflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position)
    {
        //binding text views with data from database
        String title = tasks.get(position).getTitle();
        String creationDate = tasks.get(position).getDate();
        String endDate = tasks.get(position).getDeadline();
        holder.taskViewTitle.setText(title);
        holder.taskViewCreationDate.setText(creationDate);
        holder.taskViewEndDate.setText(endDate);
    }

    @Override
    public int getItemCount()
    {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView taskViewTitle, taskViewCreationDate, taskViewEndDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskViewTitle = itemView.findViewById(R.id.taskViewTitle);
            taskViewCreationDate = itemView.findViewById(R.id.taskViewCreationDate);
            taskViewEndDate = itemView.findViewById(R.id.taskViewEndDate);
        }
    }

}
