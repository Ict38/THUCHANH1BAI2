package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Cat;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private List<Cat> mList, bList;
    private Context context;
    private CatItemListener catItemListener;

    public CatAdapter(List<Cat> mList, Context context) {
        this.mList = mList;
        this.context = context;
        this.bList = mList;
    }

    public List<Cat> getbList() {
        return bList;
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);
        if(cat == null) return;
        holder.img.setImageResource(cat.getImg());
        holder.name.setText(cat.getName());
        holder.price.setText(cat.getPrice() +"");
        if (cat.getB1() == true) holder.cb1.setChecked(true);
        else holder.cb1.setChecked(false);
        if (cat.getB2() == true) holder.cb2.setChecked(true);
        else holder.cb2.setChecked(false);
        if (cat.getB3() == true) holder.cb3.setChecked(true);
        else holder.cb3.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public Cat getItemAt(int position) {
        return mList.get(position);
    }

    public void add(Cat cat) {
        mList.add(cat);
        notifyDataSetChanged();
    }

    public void update(int position,Cat cat) {
        mList.set(position,cat);
        notifyDataSetChanged();
    }

    public void filterList(List<Cat> filterList) {
        mList = filterList;
        notifyDataSetChanged();
    }


    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView name,price;
        private CheckBox cb1,cb2,cb3;
        private Button remove;


        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.rvImg);
            name = itemView.findViewById(R.id.rvName);
            price = itemView.findViewById(R.id.rvPrice);
            cb1 = itemView.findViewById(R.id.rvcb1);
            cb2 = itemView.findViewById(R.id.rvcb2);
            cb3 = itemView.findViewById(R.id.rvcb3);
            remove = itemView.findViewById(R.id.remove);
            itemView.setOnClickListener(this);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList.remove(getAdapterPosition());
                    Toast.makeText(context.getApplicationContext(), "REMOVE SUCCESS", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
    }

        @Override
        public void onClick(View view) {
            if(catItemListener == null) return;
            catItemListener.onItemClick(view, getAdapterPosition());
        }
    }
    public interface CatItemListener{
        void onItemClick(View view,int position);
    }
}
