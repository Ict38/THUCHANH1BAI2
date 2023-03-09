package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import java.util.List;

public class SpinnerImageAdapter extends BaseAdapter {
    private int[] imgs = {R.drawable.cat1,R.drawable.cat2,R.drawable.cat3,R.drawable.cat4,
            R.drawable.cat5};
    private Context context;

    public SpinnerImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.spinner_item,viewGroup,false);
        ImageView imageView = itemView.findViewById(R.id.img);
        imageView.setImageResource(imgs[i]);
        return itemView;
    }
}
