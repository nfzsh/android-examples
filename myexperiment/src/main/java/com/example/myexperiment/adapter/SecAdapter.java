package com.example.myexperiment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myexperiment.R;
import com.example.myexperiment.SecActivity;
import com.example.myexperiment.entity.Food;
import com.example.myexperiment.entity.Data;

import java.lang.reflect.Field;
import java.util.List;

public class SecAdapter extends RecyclerView.Adapter<SecAdapter.ViewHolder> {
    private List<Food> food;
    private SecActivity context;

    public SecAdapter(List<Food> food, SecActivity context) {
        this.food = food;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_food, parent, false);
        return new ViewHolder(itemView);
    }

    public int getResource(String name) {
        try {
            // 根据图片资源的文件名获得Field对象
            Field field = R.drawable.class.getField(name);
            // 取得并返回资源ID
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {}
        return 0;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(food.get(position).title);
        holder.suttitle.setText(food.get(position).subtitle);
        holder.pic.setImageResource(getResource(food.get(position).picAdress));
        holder.price.setText(food.get(position).price);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, food.get(position).title, Toast.LENGTH_SHORT).show();
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.Change(food.get(position).price);
                context.Menu(context.Recored(food.get(position).id,food.get(position).price));
            }
        });
    }


    @Override
    public int getItemCount() {
        return food.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView suttitle;
        public ImageView pic;
        public TextView price;
        public Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.food_title);
            suttitle = itemView.findViewById(R.id.food_subtitle);
            pic = itemView.findViewById(R.id.food_pic);
            price = itemView.findViewById(R.id.food_price);
            button = itemView.findViewById(R.id.food_add);
        }
    }

    // 自定义监听接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position, Food food);
    }
}
