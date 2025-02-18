// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.otterlibrarydatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private CreateAccountDialog createAccountDialog;
    private ManageSystemDialog manageSystemDialog;
    private HoldDialog holdDialog;

    private ActivityMainBinding binding;
    private OtterLibraryDatabase db;

    private Button create_account_btn;
    private Button place_hold_btn;
    private Button manage_system_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createAccountDialog = new CreateAccountDialog();
        manageSystemDialog = new ManageSystemDialog();
        holdDialog = new HoldDialog();

        db = OtterLibraryDatabase.getInstance(this);
        db.populateInitialData();

        create_account_btn = binding.btnNewAccount;
        place_hold_btn = binding.btnPlaceHold;
        manage_system_btn = binding.btnManageSystem;
        create_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccountDialog.show(getSupportFragmentManager(),"");
            }
        });

        place_hold_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holdDialog.show(getSupportFragmentManager(),"");
            }
        });

        manage_system_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageSystemDialog.show(getSupportFragmentManager(),"");
            }
        });

    }
}