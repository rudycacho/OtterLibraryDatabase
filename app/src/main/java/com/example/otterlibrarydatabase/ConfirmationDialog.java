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

import com.example.otterlibrarydatabase.databinding.DialogConfirmationBinding;

public class ConfirmationDialog extends DialogFragment {
    private int confirmationNumber;
    private DialogConfirmationBinding binding;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DialogConfirmationBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        OtterLibraryDatabase db = OtterLibraryDatabase.getInstance(getActivity());
        confirmationNumber = db.log().getAll().size() + 1;

        Bundle receivedInfo = getArguments();
        String username = receivedInfo.getString("username");
        String book = receivedInfo.getString("book");
        binding.username.setText("Customer Username: " + username);
        binding.bookTitle.setText("Book Title: " + book);
        binding.reservationNum.setText("Reservation Number: " + confirmationNumber);

        builder.setView(binding.getRoot())
                .setTitle("Check Out?")
                .setMessage("Please confirm your book hold.")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log log = new Log("Place Hold: ","Username: " + username + " Reservation Number: " + confirmationNumber);
                        db.log().addLog(log);
                        getActivity().finish();
                        Toast.makeText(getContext(), "Hold Placed!", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         getActivity().finish();
                         Toast.makeText(getContext(), "Hold Canceled.", Toast.LENGTH_LONG).show();
                    }
            });
        return builder.create();
    }
}
