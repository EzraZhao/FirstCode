package com.ezra.materialtest;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MaterialTest";

    private DrawerLayout mDrawerLayout;

    private Fruit[] fruits = {new Fruit("Apple", R.drawable.apple),
    new Fruit("Banana", R.drawable.banana),
    new Fruit("Orange", R.drawable.orange),
    new Fruit("Watermelon", R.drawable.watermelon),
    new Fruit("Pear", R.drawable.pear),
    new Fruit("Grape", R.drawable.grape),
    new Fruit("Pineapple", R.drawable.pineapple),
    new Fruit("Strawberry", R.drawable.strawberry),
    new Fruit("Cherry", R.drawable.cherry),
    new Fruit("Mango", R.drawable.mango)};

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //添加导航按钮
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drwaer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            //显示导航按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //优化滑动菜单样式、布局
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //悬浮按钮处理点击事件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,
                                        "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        //初始化水果界面
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);//第二个参数为列数
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        //实现下拉刷新
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeColors(R.color.colorPrimary);//设置下拉进度条颜色
        //设置下拉监听
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

//        hello((a, b) ->{
//            String result = a + b;
//            return result;
//        });
    }

    private void refreshFruits() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        //刷新事件结束，隐藏刷新进度条
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }


    //Lambda写法
    public void hello(MyListener listener) {
        String a = "hello";
        int b = 22;
        String result = listener.doSomething(a, b);
        Log.d(TAG, result);

//        new Thread(() -> {
//            //执行具体逻辑
//        }).start();
    }

}
