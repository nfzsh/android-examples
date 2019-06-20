package com.example.myexperiment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
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

import com.example.myexperiment.adapter.SecAdapter;
import com.example.myexperiment.database.MyDatabase;
import com.example.myexperiment.entity.Data;
import com.example.myexperiment.entity.Food;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class SecActivity extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SecAdapter adapter1;
    private float p = 0.00f;
    private TextView price;
    private Button button;
    ArrayList<Data> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        recyclerView1 = findViewById(R.id.act_sec_recyclerview_zhaopai);
        price = findViewById(R.id.act_sec_price);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        // 动态修改toolbar属性
        toolbar.setTitle("点餐系统");
        setSupportActionBar(toolbar);
        // 显示左箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button = findViewById(R.id.act_sec_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecActivity.this, PayActivity.class);
                intent.putExtra("price", p);
                startActivity(intent);
            }
        });
        //监听点击价格文本框跳转账单
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecActivity.this, ThirdActivity.class);
                intent.putExtra("list", datas);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
        // 指定一个默认的布局管理器
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(mLayoutManager);
        // 指定item插入/移除动画
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        // 指定item分割线
        recyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        // 指定适配器
        adapter1 = new SecAdapter(listFood1(), this);
        recyclerView1.setAdapter(adapter1);
    }

    private List<Food> listFood1() {
        Food n1 = new Food(1, "鱼香肉丝", "四川风味好吃不贵", "24.00","yxrs");
        Food n2 = new Food(2, "肉末茄子", "价格公道分量足够", "12.00","rmqz");
        Food n3 = new Food(3, "麻婆豆腐", "鲜嫩可口美味多汁", "13.00","mpdf");
        Food n4 = new Food(4, "糖醋排骨", "酸甜够味鲜嫩肥美", "30.00","tcpg");
        List<Food> foods = new ArrayList<>();
        foods.add(n1);
        foods.add(n2);
        foods.add(n3);
        foods.add(n4);
        foods.add(n1);
        foods.add(n2);
        foods.add(n3);
        foods.add(n4);
        return foods;
    }

    public void Change(String a) {
        p += Float.parseFloat(a);
        price.setText("总价：" + p);
    }

    public Data Recored(int id, String price) {
        Data data = new Data(id, Float.parseFloat(price), 1);
        return data;
    }

    public void Menu(Data data) {
        boolean flag = false;
        for (Data data1 : datas
        ) {
            if (data.getId() == data1.getId()) {
                data1.AddNum();
                flag = true;
            }
        }
        if (!flag) {
            datas.add(data);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        datas = MyDatabase.getDatas();
        float p = 0.0f;
        while (datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                float a = datas.get(i).getPrice();
                float b = datas.get(i).getNum();
                p += a * b;
            }
            break;
        }
        price.setText("总价："+p);
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

