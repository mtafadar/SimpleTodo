package com.example.simpletodo;

import android.os.FileUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

   Button  btnAdd;
   EditText etItem;
   RecyclerView rvItems;
   ItemsAdapter itemsAdapter;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.button);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById((R.id.rvItem));



        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Go to Gym");
        items.add("Have fun");


      ItemsAdapter.onLongClickListener onLongClickListener = new ItemsAdapter.onLongClickListener(){

          @Override
          public void onItemLongClicked(int position) {
              items.remove(position);
              itemsAdapter.notifyItemRemoved(position);
              Toast.makeText(getApplicationContext(),"Item was removed", Toast.LENGTH_SHORT).show();

          }
      };
       itemsAdapter =  new ItemsAdapter(items, onLongClickListener);
       rvItems.setAdapter(itemsAdapter);
       rvItems.setLayoutManager(new LinearLayoutManager(this));

       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              String todoItem =  etItem.getText().toString();
              items.add(todoItem);
              itemsAdapter.notifyItemInserted(items.size()-1);
              etItem.setText("");
               Toast.makeText(getApplicationContext(),"Item was added", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private File getDataFile(){
        return new File (getFilesDir(), "data.txt");
    }

//    private void loadItems() {
//        try {
//            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
//        } catch (IOException e) {
//            Log.e("MainActivity", "Error reading items", e);
//            items = new ArrayList<>();
//        }
//    }
//
//    private void saveItems() {
//        try {
//            FileUtils.writeLines(getDataFile(), items);
//        } catch (IOException e) {
//            Log.e("MainActivity", "Error writing items", e);
//        }
//    }












}