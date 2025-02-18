// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otterlibrarydatabase.databinding.LogItemBinding;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
    private List<Log> logs;
    private LogItemBinding binding;

    public LogAdapter(List<Log> logs){
        this.logs = logs;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        binding = LogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LogViewHolder(binding);
    }

    @Override
    public int getItemCount(){
        return logs.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        Log log = logs.get(position);
        holder.populateView(log);

    }
}
