package com.example.todolist;

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
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

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
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("");
            alert.setMessage("Do you wanna delete this item from list?");
            alert.setCancelable(false);

            alert.setNegativeButton("No", (dialogInterface, i12) -> dialogInterface.cancel());

            alert.setPositiveButton("Yes", (dialogInterface, i1) -> {
                itemList.remove(i);
                arrayAdapter.notifyDataSetChanged();
                FileHelper.writeData(itemList, getApplicationContext());
            });

            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        });
    }
}