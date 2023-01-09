package com.example.todolistsafetyheads;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    ListView listview;
    public static ArrayList<String> itemList = new ArrayList<>();
    public static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listview = findViewById(R.id.list);

        itemList = FileHelper.readData(this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);

        listview.setAdapter(arrayAdapter);

        add.setOnClickListener(v -> {
            String itemName = item.getText().toString();
            itemList.add(itemName);
            item.setText("");

            FileHelper.writeData(itemList, getApplicationContext());

            arrayAdapter.notifyDataSetChanged();
        });

        listview.setOnItemClickListener((adapterView, view, i, l) -> {
            openDialog(i);
        });
    }

    private void openDialog(int size){
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment(size);
        alertDialogFragment.show(getSupportFragmentManager(), "alertDialogFragment");
    }
}