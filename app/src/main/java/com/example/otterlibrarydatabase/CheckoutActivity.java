// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.otterlibrarydatabase.databinding.ActivityCheckoutBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckoutActivity extends AppCompatActivity implements LoginListener {
    private BookAdapter bookAdapter;
    private ActivityCheckoutBinding binding;
    private RecyclerView rv;

    private OtterLibraryDatabase db;

    private String selection;
    private Context context;
    private ConfirmationDialog confirmationDialog;
    private NoBookDialog noBookDialog;
    List<Book> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        rv = binding.recyclerView;
        rv.setLayoutManager(new LinearLayoutManager(this));
        db = OtterLibraryDatabase.getInstance(this);

        Bundle receivedBundle = getIntent().getExtras();
        String genre = receivedBundle.getString("genre");
        this.booksList = db.book().getAll();
        confirmationDialog = new ConfirmationDialog();
        noBookDialog = new NoBookDialog();
        fetchBooks(genre);
    }

    private void fetchBooks(String genre){
        List<Book>books = new ArrayList<>();
        for (int i = 0; i < booksList.size(); i++){
            if (Objects.equals(booksList.get(i).getGenre(), genre)){
                books.add(booksList.get(i));
            }
        }
        bookAdapter = new BookAdapter(books, getSupportFragmentManager(),this);
        rv.setAdapter(bookAdapter);
        if (books.size() < 1){
            noBookDialog.show(getSupportFragmentManager(),"");
        }
    }


    @Override
    public void log(boolean isSuccessful, String username) {
        if(isSuccessful){
            Bundle info = new Bundle();
            info.putString("username", username);
            info.putString("book", selection);
            confirmationDialog.setArguments(info);
            confirmationDialog.show(getSupportFragmentManager(),"");
        }
    }

    @Override
    public void selection(String book) {
        this.selection = book;
    }
}