package com.example.myexperiment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myexperiment.adapter.ThirdAdapter;
import com.example.myexperiment.database.MyDatabase;
import com.example.myexperiment.entity.Data;
import com.example.myexperiment.entity.Food;

import java.util.List;
import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    TextView textView;
    ArrayList<Data> datas = new ArrayList<>();
    float a = 0.0f;
    private ThirdAdapter adapter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        RecyclerView recyclerView = findViewById(R.id.act_third_list);
        textView = findViewById(R.id.act_third_price);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        // 动态修改toolbar属性
        toolbar.setTitle("点餐系统");
        setSupportActionBar(toolbar);
        // 显示左箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        datas = (ArrayList) getIntent().getSerializableExtra("list");

        button = findViewById(R.id.act_third_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, PayActivity.class);
                intent.putExtra("price", a);
                startActivity(intent);
            }
        });
        for (int i = 0; i < datas.size(); i++) {
            a += ((datas.get(i).getPrice()) * (datas.get(i).getNum()));

        }
        textView.setText("总价："+a);
        // 指定一个默认的布局管理器
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // 指定item插入/移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 指定item分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new ThirdAdapter(listFood(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        //此处写退向后台的处理
        MyDatabase.setDatas(datas);
        finish();
    }

    private List<Food> listFood() {

        int a = 0, b = 0, c = 0, d = 0;
        for (Data data : datas
        ) {
            if (data.getId() == 1)
                a = data.getNum();
            if (data.getId() == 2)
                b = data.getNum();
            if (data.getId() == 3)
                c = data.getNum();
            if (data.getId() == 4)
                d = data.getNum();
        }
        Food n1 = new Food(1, "鱼香肉丝", "四川风味好吃不贵", "24.00", a,"yxrs");
        Food n2 = new Food(2, "肉末茄子", "价格公道分量足够", "12.00", b,"rmqz");
        Food n3 = new Food(3, "麻婆豆腐", "鲜嫩可口美味多汁", "13.00", c,"mpdf");
        Food n4 = new Food(4, "糖醋排骨", "酸甜够味鲜嫩肥美", "30.00", d,"tcpg");
        List<Food> foods = new ArrayList<>();
        if (n1.num != 0)
            foods.add(n1);
        if (n2.num != 0)
            foods.add(n2);
        if (n3.num != 0)
            foods.add(n3);
        if (n4.num != 0)
            foods.add(n4);

        return foods;
    }

    public List<Data> getDatas() {
        return datas;
    }
    public void setTextView(String price){
        this.textView.setText(price);
    }

    /**
     * 重写，加载menu布局
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * 重写，监听menu点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.menu_add:
                msg = "add";
                break;
            // 点击左箭头，返回，即关闭当前activity
            case android.R.id.home:
                msg = "home";
                finish();
            case R.id.menu_send:
                msg = "send";
                break;
            case R.id.menu_edit:
                msg = "edit";
                break;
            case R.id.menu_del:
                msg = "delete";
                break;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
