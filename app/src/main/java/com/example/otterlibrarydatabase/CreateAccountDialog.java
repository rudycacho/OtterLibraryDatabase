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

import com.example.otterlibrarydatabase.databinding.DialogAccoutCreateBinding;

import java.util.List;

public class CreateAccountDialog extends DialogFragment {
    private DialogAccoutCreateBinding binding;
    private boolean hasDuplicateAccount = false;
    private CreateAccountDialog createAccountDialog;

    private List<Account> accounts;
    private int mistakes = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        createAccountDialog = new CreateAccountDialog();
        OtterLibraryDatabase db = OtterLibraryDatabase.getInstance(getActivity());
        accounts = db.account().getAll();
        binding = DialogAccoutCreateBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(binding.getRoot())
                .setTitle("Account Creation")
                .setMessage("Please enter a new username and password.");
        builder.setPositiveButton("Create Account", null);
        AlertDialog accountCreation = builder.create();

        accountCreation.setOnShowListener(dialogInterface -> {
            Button positveButton = accountCreation.getButton(AlertDialog.BUTTON_POSITIVE);
            positveButton.setOnClickListener(v -> {
                String username = String.valueOf(binding.cUsername.getText());
                String password = String.valueOf(binding.cPassword.getText());
                for (int j = 0; j < accounts.size(); j++) {
                    if (accounts.get(j).getUsername().equals(username)) {
                        hasDuplicateAccount = true;
                        Toast.makeText(getContext(), "Username is taken!", Toast.LENGTH_SHORT).show();
                        mistakes++;
                    }
                }
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getContext(), "Username or Password Cannot Be Blank", Toast.LENGTH_SHORT).show();
                    mistakes++;
                }
                else if (!hasDuplicateAccount) {
                    Account account = new Account(username, password);
                    Log log = new Log("New Account: ", "Username: " + username);
                    db.account().addAccount(account);
                    hasDuplicateAccount = false;
                    db.log().addLog(log);
                    Toast.makeText(getContext(), "Account Successfully Created", Toast.LENGTH_SHORT).show();
                    accountCreation.dismiss();
                }
                if(mistakes == 2){
                    mistakes = 0;
                    accountCreation.dismiss();
                }
            });

        });
        return accountCreation;
    }
}