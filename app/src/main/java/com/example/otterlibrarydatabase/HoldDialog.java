// Name: Rudolph Cacho Hernandez
// Date: December 16th, 2024
package com.example.otterlibrarydatabase;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.otterlibrarydatabase.databinding.DialogHoldBinding;

public class HoldDialog extends DialogFragment {
    private DialogHoldBinding binding;
    private String genreSelection;

    Bundle bundle = new Bundle();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DialogHoldBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setView(binding.getRoot())
                .setTitle("Genre Selection")
                .setMessage("Please select a genre.")
                .setPositiveButton("Select", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent selection = new Intent(getActivity(),CheckoutActivity.class);
                        RadioGroup radioGroup = binding.radioGroup;
                        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                        if(checkedRadioButtonId == R.id.genre_self_help){
                            genreSelection = "Self-Help";
                        }
                        if(checkedRadioButtonId == R.id.genre_historical_fantasy){
                            genreSelection = "Historical Fantasy";
                        }
                        if(checkedRadioButtonId == R.id.genre_cs){
                            genreSelection = "Computer Science";
                        }
                        if(checkedRadioButtonId == R.id.genre_historical_Fiction){
                            genreSelection = "Historical Fiction";
                        }

                        bundle.putString("genre",genreSelection);
                        selection.putExtras(bundle);
                        startActivity(selection);
                    }
                });
        return builder.create();
    }
}
