package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        String title        = tasks.get(position).getTitle();
        String creationDate = tasks.get(position).getDate();
        String endDate      = tasks.get(position).getDeadline();
        long id             = tasks.get(position).getID();

        holder.taskViewTitle.setText(title);
        holder.taskViewCreationDate.setText(creationDate);
        holder.taskViewEndDate.setText(endDate);
        holder.taskViewID.setText(String.valueOf(tasks.get(position).getID()));
    }

    @Override
    public int getItemCount()
    {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView taskViewTitle, taskViewCreationDate, taskViewEndDate, taskViewID;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            taskViewTitle           = itemView.findViewById(R.id.taskViewTitle);
            taskViewCreationDate    = itemView.findViewById(R.id.taskViewCreationDate);
            taskViewEndDate         = itemView.findViewById(R.id.taskViewEndDate);
            taskViewID              = itemView.findViewById(R.id.listId);

            itemView.setOnClickListener(view -> {
                Toast.makeText(view.getContext(),"Itemclicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),TaskDetails.class);
                intent.putExtra("ID",tasks.get(getAdapterPosition()).getID());
                view.getContext().startActivity(intent);

            });

        }
    }

}
