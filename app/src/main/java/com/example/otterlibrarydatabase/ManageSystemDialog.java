// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.otterlibrarydatabase.databinding.DialogManageLoginBinding;

public class ManageSystemDialog extends DialogFragment {
    private DialogManageLoginBinding binding;
    private int mistakes = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DialogManageLoginBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        OtterLibraryDatabase db = OtterLibraryDatabase.getInstance(getActivity());

        builder.setView(binding.getRoot())
                .setTitle("Librarian Log In")
                .setMessage("Please enter your username and password.");
        builder.setPositiveButton("Log in", null);
        AlertDialog systemLogIn = builder.create();

        systemLogIn.setOnShowListener(dialogInterface -> {
            Button positiveButton = systemLogIn.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(v -> {
                Intent logActivity = new Intent(getActivity(),LogActivity.class);
                String username = String.valueOf(binding.lUsername.getText());
                String password = String.valueOf(binding.lPassword.getText());

                if(username.equals(db.account().getAll().get(0).getUsername()) && password.equals(db.account().getAll().get(0).getPassword())){
                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(logActivity);
                    systemLogIn.dismiss();
                }
                else{
                    Toast.makeText(getContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                    mistakes++;
                }
                if(mistakes == 2){
                    mistakes = 0;
                    systemLogIn.dismiss();
                }
            });
        });
        return systemLogIn;
    }
}
