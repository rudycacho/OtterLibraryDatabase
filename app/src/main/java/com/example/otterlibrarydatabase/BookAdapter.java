// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otterlibrarydatabase.databinding.BookItemBinding;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{
    private List<Book> books;
    private BookItemBinding binding;

    private FragmentManager fragmentManager;
    private LoginListener listener;

    public BookAdapter(List<Book> bookList, FragmentManager fragmentManager, LoginListener listener){
        this.books = bookList;
        this.fragmentManager = fragmentManager;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        binding = BookItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(binding, fragmentManager);
    }

    @Override
    public int getItemCount(){
        return books.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.populateView(book,listener);

    }
}
