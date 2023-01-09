package com.example.todolistsafetyheads;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AlertDialogFragment extends AppCompatDialogFragment {

    int position;

    AlertDialogFragment(int position) {
        this.position=position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("");
        alert.setMessage("Do you wanna delete this item from list?");
        alert.setCancelable(false);

        alert.setNegativeButton("NO", (dialogInterface, i) -> dialogInterface.cancel());

        alert.setPositiveButton("YES", (dialogInterface, i) -> {
            MainActivity.arrayItemList.remove(position);
            FileHelper.writeData(MainActivity.arrayItemList, getActivity());

        });

        return alert.create();
    }
}
