// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.otterlibrarydatabase.databinding.DialogBookConfirmBinding;


public class BookConfirmDialog extends DialogFragment {
    private DialogBookConfirmBinding binding;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DialogBookConfirmBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        OtterLibraryDatabase db = OtterLibraryDatabase.getInstance(getActivity());

        Bundle receivedInfo = getArguments();
        String title = receivedInfo.getString("title");
        String author = receivedInfo.getString("author");
        String genre = receivedInfo.getString("genre");
        binding.title.setText("Book Title: " + title);
        binding.author.setText("Name of Author: " + author);
        binding.genre.setText("Genre: " + genre);

        builder.setView(binding.getRoot())
                .setTitle("Confirm Addition")
                .setMessage("Please confirm the new addition to the library.")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Book book = new Book(title,author,genre);
                        Log log = new Log("Book Added: ","Title: " + title + " Author: " + author + " Genre: " + genre);
                        db.log().addLog(log);
                        db.book().addBook(book);
                        getActivity().finish();
                        Toast.makeText(getContext(), "Book Added!", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                        Toast.makeText(getContext(), "Book Not Added.", Toast.LENGTH_LONG).show();
                    }
                });
        return builder.create();
    }
}
