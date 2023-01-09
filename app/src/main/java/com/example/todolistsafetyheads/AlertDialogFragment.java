package com.example.todolistsafetyheads;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AlertDialogFragment extends AppCompatDialogFragment {

    int size;

    AlertDialogFragment(int size) {
        this.size=size;
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
            MainActivity.itemList.remove(size);
            MainActivity.arrayAdapter.notifyDataSetChanged();
            FileHelper.writeData(MainActivity.itemList, getActivity());

        });

        return alert.create();
    }
}
