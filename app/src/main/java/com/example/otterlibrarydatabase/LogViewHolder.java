// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.recyclerview.widget.RecyclerView;

import com.example.otterlibrarydatabase.databinding.LogItemBinding;

public class LogViewHolder extends RecyclerView.ViewHolder {
    private final LogItemBinding binding;

    public LogViewHolder(LogItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void populateView(Log log){
        binding.logText.setText(log.getTransactionType() + " " +log.getDetails());
    }
}
