package com.example.simpletodo1;

import android.os.FileUtils;
import android.support.v7.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> items;
    Button btnAdd;
    EditText etItems;
    RecyclerView rvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        etItems = findViewById((R.id.etItem));
        rvItems = findViewById(R.id.rvItems);

        loadItems()

        itemsAdapter.OnLongClickListener onLongClickListener = new itemsAdapter.OnLongClickListener(){
            @Override
            public  void onItemLongClicked (int position){
                items.remove(position);
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"Item was removed", Toast.LENGTH_SHORT).show();
            }
        };

        itemsAdapter itemsAdapter = new itemsAdapter(items);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        saveItems();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String todoItem = etItems.getText().toString();
            items.add(todoItem);
            itemsAdapter.notifyitemInserted(items.size()-1);
            etItems.setText("");
                Toast.makeText(getApplicationContext(),"Item was added", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });

    }
    private File getDataFile(){
        return new File(getFilesDir(),"data,txt");
    }
    private void loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e){
            Log.e("MainActivity", "Error reading items", e);
            items = new ArrayList<>();
        }
    }
    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(),items);
        } catch (IOException e){
            Log.e("MainActivity","Error reading items", e);
        }
    }

}