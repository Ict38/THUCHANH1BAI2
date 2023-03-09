package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.myapplication.adapter.CatAdapter;
import com.example.myapplication.adapter.SpinnerImageAdapter;
import com.example.myapplication.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener, SearchView.OnQueryTextListener {
    private CatAdapter adapter;
    private Spinner spinner;
    private RecyclerView rView;
    private CheckBox cb1,cb2,cb3;
    private EditText name,price;
    private Button add,update;
    private int[] imgs = {R.drawable.cat1,R.drawable.cat2,
            R.drawable.cat3,R.drawable.cat4, R.drawable.cat5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupAllVariable();
        spinerAdapterSetup();
        recyclerViewSetup();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    private void setupAllVariable() {
        spinner = findViewById(R.id.spinner);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        rView = findViewById(R.id.rview);
    }

    private void spinerAdapterSetup() {
        SpinnerImageAdapter spAdapter = new SpinnerImageAdapter(this);
        spinner.setAdapter(spAdapter);
    }

    private void recyclerViewSetup() {
        List<Cat> cats = new ArrayList<>();
        Cat cat1 = new Cat(R.drawable.cat1,"Meo Dep Trai", 12.0,true,true,false);
        Cat cat2 = new Cat(R.drawable.cat2,"Meo Xinh Gai", 13.0,false,true,false);
        cats.add(cat1);
        cats.add(cat2);
        adapter = new CatAdapter(cats,this);
        adapter.setCatItemListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rView.setAdapter(adapter);
        rView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e( "tag", "Passhere" );
        Cat cat = adapter.getItemAt(position);
        name.setText(cat.getName());
        price.setText(cat.getPrice()+"");
        int pos = 0;
        for(int i = 0; i < imgs.length; i ++){
            if (cat.getImg() == imgs[i]) {
                pos = i;
                break;
            }
        }
        spinner.setSelection(pos);
        if (cat.getB1() == true) cb1.setChecked(true);
        else cb1.setChecked(false);
        if (cat.getB2() == true) cb2.setChecked(true);
        else cb2.setChecked(false);
        if (cat.getB3() == true) cb3.setChecked(true);
        else cb3.setChecked(false);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}