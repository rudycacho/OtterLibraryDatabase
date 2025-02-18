// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.otterlibrarydatabase.databinding.DialogCheckoutBinding;

public class CheckoutDialog extends DialogFragment {
    private DialogCheckoutBinding binding;
    private LoginListener listener;
    private int mistakes = 0;

    public void setLoginListener(LoginListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        OtterLibraryDatabase db = OtterLibraryDatabase.getInstance(getActivity());
        binding = DialogCheckoutBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(binding.getRoot())
                .setTitle("Sign In")
                .setMessage("Please enter your username and password.");
        builder.setPositiveButton("Log in", null);
        AlertDialog checkout = builder.create();

        checkout.setOnShowListener(dialogInterface -> {
            Button positiveButton = checkout.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(v -> {
                String username = String.valueOf(binding.oUsername.getText());
                String password = String.valueOf(binding.oPassword.getText());
                boolean valid = false;

                for (int j = 0; j < db.account().getAll().size(); j++) {
                    if (username.equals(db.account().getAll().get(j).getUsername()) && password.equals(db.account().getAll().get(j).getPassword())) {
                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    mistakes++;
                }
                if (listener != null && valid) {
                    listener.log(true, username);
                }
                if(mistakes == 2){
                    mistakes = 0;
                    checkout.dismiss();
                    getActivity().finish();
                }
            });
        });
        return checkout;
    }
}
