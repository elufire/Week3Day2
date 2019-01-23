package com.example.week3day2;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Car> carsArrayList;
    String TAG;

    public RecyclerViewAdapter(ArrayList<Car> carsArrayList) {
        this.carsArrayList = carsArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Car car = carsArrayList.get(position);

        if (car != null) {
            String name = car.getName();
            String color = car.getColor();
            String engine = car.getEngine();
            String image = car.getImageUrl();
            viewHolder.name.setText(name);
            viewHolder.color.setText(color);
            viewHolder.engine.setText(engine);
            Glide.with(viewHolder.itemView.getContext()).load(image).into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return carsArrayList != null ? carsArrayList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        TextView color;
        TextView engine;

        Car car;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgViewImage);
            name = itemView.findViewById(R.id.rvName);
            color = itemView.findViewById(R.id.rvColor);
            engine = itemView.findViewById(R.id.rvEngine);

        }

        public void setcar(Car car){ this.car = car;}
    }

    public void addcar(Car car){
        //System.out.println(name);
        carsArrayList.add(car);
        notifyDataSetChanged();
    }
}