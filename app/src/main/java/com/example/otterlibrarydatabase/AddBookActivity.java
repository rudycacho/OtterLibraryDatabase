// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.otterlibrarydatabase.databinding.ActivityAddBookBinding;

public class AddBookActivity extends AppCompatActivity implements BookListener {
    private ActivityAddBookBinding binding;
    private BookCreateDialog bookCreateDialog;
    private BookConfirmDialog bookConfirmDialog;
    private Button btnNo;
    private Button btnYes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bookCreateDialog = new BookCreateDialog();
        bookConfirmDialog = new BookConfirmDialog();
        bookCreateDialog.setListener(this);

        btnNo = binding.btnNo;
        btnYes = binding.btnYes;
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookCreateDialog.show(getSupportFragmentManager(),"");
            }
        });
    }

    @Override
    public void confirmBook(boolean isSuccessful, String title, String author, String genre) {
        if(isSuccessful){
            Bundle info = new Bundle();
            info.putString("title", title);
            info.putString("author", author);
            info.putString("genre", genre);
            bookConfirmDialog.setArguments(info);
            bookConfirmDialog.show(getSupportFragmentManager(),"");
        }
    }
}