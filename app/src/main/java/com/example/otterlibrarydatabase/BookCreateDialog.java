// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.widget.Button;
import android.widget.Toast;

import com.example.otterlibrarydatabase.databinding.DialogBookCreateBinding;

import java.util.List;

public class BookCreateDialog extends DialogFragment {
    private DialogBookCreateBinding binding;
    private BookListener listener;
    private boolean hasDuplicateBook = false;
    private BookCreateDialog bookCreateDialog;

    private List<Book> books;
    private int mistakes = 0;

    public void setListener(BookListener bookListener){
        this.listener = bookListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        bookCreateDialog = new BookCreateDialog();
        OtterLibraryDatabase db = OtterLibraryDatabase.getInstance(getActivity());
        books = db.book().getAll();
        binding = DialogBookCreateBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(binding.getRoot())
                .setTitle("Add Book")
                .setMessage("Please enter title, author and genre of the book to be added.");
        builder.setPositiveButton("Add Book", null);
        AlertDialog addBook = builder.create();

        addBook.setOnShowListener(dialogInterface -> {
            Button positveButton = addBook.getButton(AlertDialog.BUTTON_POSITIVE);
            positveButton.setOnClickListener(v -> {
                String title = String.valueOf(binding.cTitle.getText());
                String author = String.valueOf(binding.cAuthor.getText());
                String genre = String.valueOf(binding.cGenre.getText());
                for (int j = 0; j < books.size(); j++) {
                    if (books.get(j).getTitle().equals(title)) {
                        hasDuplicateBook = true;
                        Toast.makeText(getContext(), "Book is already in the database!", Toast.LENGTH_SHORT).show();
                        mistakes++;
                    }
                }
                if (title.equals("") || author.equals("") || genre.equals("")) {
                    Toast.makeText(getContext(), "Missing Information", Toast.LENGTH_SHORT).show();
                    mistakes++;
                }
                else if (!hasDuplicateBook && listener != null) {
                    listener.confirmBook(true,title,author,genre);
                    hasDuplicateBook = false;
                    addBook.dismiss();
                }
                if(mistakes == 2){
                    mistakes = 0;
                    getActivity().finish();
                    addBook.dismiss();
                }
            });

        });
        return addBook;
    }
}