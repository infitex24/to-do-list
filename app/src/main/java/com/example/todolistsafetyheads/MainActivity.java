package com.example.todolistsafetyheads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    EditText editTextItem;
    Button buttonAdd;
    RecyclerView recyclerView;
    public static boolean writeData = false;

    public static ArrayList<String> arrayItemList = new ArrayList<>();
    ArrayList<Item> itemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextItem = findViewById(R.id.editText);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerView = findViewById(R.id.recyclerView);

        writeData=false;
        startMyService();
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (String text: arrayItemList) {
            itemList.add(new Item(text, R.drawable.ic_baseline_delete_24));
        }
        buildRecyclerView();
        setButtons();
    }

    private void openDialog(int size){
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment(size);
        alertDialogFragment.show(getSupportFragmentManager(), "alertDialogFragment");
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(itemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }

            @Override
            public void onDeleteClick(int position) {
               // openDialog(position);
                removeItem(position);
            }
        });
    }

    public void insertItem(int position, String itemName) {
        itemList.add(new Item(itemName, R.drawable.ic_baseline_delete_24));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        itemList.remove(position);
        arrayItemList.remove(position);
        writeData=true;
        startMyService();
        mAdapter.notifyItemRemoved(position);
    }

    public void setButtons() {
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = editTextItem.getText().toString();
                arrayItemList.add(itemName);
                editTextItem.setText("");

                writeData=true;
                startMyService();
                int position = (itemList.size() + 1);
                insertItem(position, itemName);
            }
        });
    }

    public void startMyService(){
        Intent i = new Intent(this, MyService.class);
        startService(i);
    }
}