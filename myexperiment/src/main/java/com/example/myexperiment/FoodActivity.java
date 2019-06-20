package com.example.myexperiment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        // 动态修改toolbar属性
        toolbar.setTitle("点餐系统");
        setSupportActionBar(toolbar);
        // 显示左箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
