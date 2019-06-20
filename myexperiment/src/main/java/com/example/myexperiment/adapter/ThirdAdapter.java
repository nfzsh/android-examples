package com.example.myexperiment.adapter;

import android.content.Context;
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
import com.example.myexperiment.ThirdActivity;
import com.example.myexperiment.entity.Food;

import java.lang.reflect.Field;
import java.util.List;

public class ThirdAdapter extends RecyclerView.Adapter<ThirdAdapter.ViewHolder> {
    private List<Food> food;
    private ThirdActivity context;

    public ThirdAdapter(List<Food> food, ThirdActivity context) {
        this.food = food;
        this.context = context;
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

    @NonNull
    @Override
    public ThirdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list, parent, false);
        return new ThirdAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThirdAdapter.ViewHolder holder, final int position) {
        holder.title.setText(food.get(position).title);
        holder.suttitle.setText(food.get(position).subtitle);
        holder.pic.setImageResource(getResource(food.get(position).picAdress));
        holder.price.setText("单价：" + food.get(position).price);
        holder.num.setText(" " + food.get(position).num + "份");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, food.get(position).title, Toast.LENGTH_SHORT).show();
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i;
                for(i=0;i<context.getDatas().size();i++){
                    if(context.getDatas().get(i).getId()==food.get(position).id)
                        context.getDatas().remove(i);
                }
                food.remove(position);//删除数据源,移除集合中当前下标的数据
                notifyItemRemoved(position);//刷新被删除的地方
                notifyItemRangeChanged(position, getItemCount());//刷新被删除数据，以及其后面的数据
                float a = 0.0f;
                for (int j = 0; j < context.getDatas().size(); j++) {
                    a += context.getDatas().get(j).getPrice() * context.getDatas().get(j).getNum();
                }
                context.setTextView("总价："+a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return context.getDatas().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView suttitle;
        public ImageView pic;
        public TextView price;
        public Button button;
        public TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_title);
            suttitle = itemView.findViewById(R.id.list_subtitle);
            pic = itemView.findViewById(R.id.list_pic);
            price = itemView.findViewById(R.id.list_price);
            button = itemView.findViewById(R.id.list_btn);
            num = itemView.findViewById(R.id.list_num);
        }
    }

    // 自定义监听接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position, Food food);
    }
}
