package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

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
    private RatingBar ratingBar;
    private int rListPosition;
    private SearchView searchView;
    private int[] imgs = {R.drawable.cat1,R.drawable.cat2,
            R.drawable.cat3,R.drawable.cat4, R.drawable.cat5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupAllVariable();
        spinerAdapterSetup();
        recyclerViewSetup();
        searchView.setOnQueryTextListener(this);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
            }
        });

        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price.setText("");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double catPrice;
                try {
                    String catName = name.getText().toString();
                    catPrice = Double.parseDouble(price.getText().toString());
                    Boolean b1,b2,b3;
                    int imgId = imgs[spinner.getSelectedItemPosition()];
                    if(cb1.isChecked()) b1 = true;
                    else b1 = false;
                    if(cb2.isChecked()) b2 = true;
                    else b2 = false;
                    if(cb3.isChecked()) b3 = true;
                    else b3 = false;
                    int rating = (int) ratingBar.getRating();
                    if(catName.isEmpty() || catPrice.toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Vui Long Nhap Du", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        adapter.add(new Cat(imgId, catName, catPrice, b1, b2, b3,rating));
                        Toast.makeText(MainActivity.this, "Them Con meo thanh cong", Toast.LENGTH_SHORT).show();
                        cb1.setChecked(false);
                        cb2.setChecked(false);
                        cb3.setChecked(false);
                        name.setText("");
                        price.setText("");
                    }

                } catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Vui Long Nhap So", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double catPrice;
                try {
                    String catName = name.getText().toString();
                    catPrice = Double.parseDouble(price.getText().toString());
                    Boolean b1,b2,b3;
                    int imgId = imgs[spinner.getSelectedItemPosition()];
                    if(cb1.isChecked()) b1 = true;
                    else b1 = false;
                    if(cb2.isChecked()) b2 = true;
                    else b2 = false;
                    if(cb3.isChecked()) b3 = true;
                    else b3 = false;
                    int rating = (int) ratingBar.getRating();
                    if(catName.isEmpty() || catPrice.toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Vui Long Nhap Du", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        adapter.update(rListPosition,new Cat(imgId, catName, catPrice, b1, b2, b3,rating));
                        Toast.makeText(MainActivity.this, "Cap Nhat con meo thanh cong", Toast.LENGTH_SHORT).show();
                        cb1.setChecked(false);
                        cb2.setChecked(false);
                        cb3.setChecked(false);
                        name.setText("");
                        price.setText("");
                        add.setEnabled(true);
                        update.setEnabled(false);
                    }

                } catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Vui Long Nhap So", Toast.LENGTH_SHORT).show();
                    return;
                }
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
        searchView = findViewById(R.id.search);
        ratingBar = findViewById(R.id.rate);
    }

    private void spinerAdapterSetup() {
        SpinnerImageAdapter spAdapter = new SpinnerImageAdapter(this);
        spinner.setAdapter(spAdapter);
    }

    private void recyclerViewSetup() {
        List<Cat> cats = new ArrayList<>();
        Cat cat1 = new Cat(R.drawable.cat1,"Meo Dep Trai", 12.0,true,true,false,4);
        Cat cat2 = new Cat(R.drawable.cat2,"Meo Xinh Gai", 13.0,false,true,false,5);
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
        rListPosition = position;
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
        ratingBar.setRating(cat.getRating());
        add.setEnabled(false);
        update.setEnabled(true);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s) {
        List<Cat> filterList = new ArrayList<>();
        for(Cat i : adapter.getbList()){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterList.add(i);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this, "NOT FOUND", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.filterList(filterList);
        }
    }
}