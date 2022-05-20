package com.dennis_brink.android.myerrands;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLogo();

        listView = findViewById(R.id.list);
        fabAdd = findViewById(R.id.fabAdd);
        itemList = FileHelper.readData(MainActivity.this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);
        listView.setAdapter(arrayAdapter);
        listView.setLongClickable(true);

        // LongClick = Delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("DENNIS_B", "Caught long click on item in listview. Position = " + position);
                AlertDialog dlg = DialogHelper.getDeleteDialog(MainActivity.this,itemList,position,arrayAdapter);
                dlg.show();
                return true;
            }
        });

        // floating action button = Add
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DENNIS_B", "Caught click on floating action button");
                AlertDialog dlg = DialogHelper.getInputDialog(MainActivity.this,itemList,arrayAdapter);
                dlg.show();
            }
        });

    }

    private void setupLogo(){
        // added a comment line for git
        // extra comments 2
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_logo_padding);
        getSupportActionBar().setTitle(R.string._title);
        getSupportActionBar().setSubtitle(getString(R.string._sub_title));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

}