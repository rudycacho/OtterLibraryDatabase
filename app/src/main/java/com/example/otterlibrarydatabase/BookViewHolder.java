// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otterlibrarydatabase.databinding.BookItemBinding;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private final BookItemBinding binding;
    private CheckoutDialog checkoutDialog;
    private FragmentManager fragmentManager;



    public BookViewHolder(BookItemBinding binding, FragmentManager fragmentManager){
        super(binding.getRoot());
        this.binding = binding;
        this.fragmentManager = fragmentManager;
    }

    public void populateView(Book book,LoginListener listener){
        binding.bookText.setText(book.getTitle() + " by " + book.getAuthor());
        binding.getRoot().setOnClickListener(view -> {
            listener.selection(book.getTitle());
            checkoutDialog = new CheckoutDialog();
            checkoutDialog.setLoginListener(listener);
            Context context = view.getContext();
            checkoutDialog.show(fragmentManager, "");
        });
    }
}
