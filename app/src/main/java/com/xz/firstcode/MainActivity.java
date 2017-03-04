package com.xz.firstcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xz.firstcode.adapter.FruitAdapter;
import com.xz.firstcode.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String Tag = "MainActivity";
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //隐藏标题栏(当前未执行)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        Button startSecondActivity = (Button) findViewById(R.id.button_1);
//        Button startdialogActivity = (Button) findViewById(R.id.button_2);
//        Log.d(Tag, "onCreat");
//        ActivityCollector.addActivity(this);


        //FruitUI
        initFruits();
        FruitAdapter fruitAdapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);

        ListView listView = (ListView) findViewById(R.id.fruit_list);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //Toast
//        startSecondActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "You Clicked startSecondActivity", Toast.LENGTH_SHORT).show();
//            }
//        });

        //显式intent
//        startSecondActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startSecondActivity(intent);
//            }
//        });

        //隐式Intent
//        startSecondActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("com.xz.firstcode.ACTION_START");
//                //指定多个category，需在AndroidMainfest.xml中添加声明
//                intent.addCategory("com.xz.firstcode.MY_CATEGORY");
//                startSecondActivity(intent);
//            }
//        });

        //使用隐式Intent调用其它程序活动（如打开网页）
//        startSecondActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);//常量值为android.intent.action.VIEW
//                intent.setData(Uri.parse("http://www.baidu.com"));//通过Uri.parse将字符串解析成Uri对象
//                startSecondActivity(intent);
//            }
//        });
//
//        //返回数据给上一个活动...需重写onActivityResult()方法
//        startSecondActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                //这个方法期望在活动被销毁之前能够返回一个结果给上一个活动
//                startActivityForResult(intent,1);
//            }
//        });

        //打开DialogActivity
//        startdialogActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent =  new Intent(MainActivity.this, DialogActivityTest.class);
////                startActivity(intent);
//                new AlertDialog.Builder(MainActivity.this).setTitle("你喜欢我么")
//                        .setView(R.layout.dialog_layout)
//                        .setMessage("你是不是我最爱的人，你为什么不说话")
//                        .setPositiveButton("喜欢", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this, "太棒了，我也喜欢你哟", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setNegativeButton("很喜欢", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this, "我也是:-D", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .create().show();
//            }
//        });
//
   }

    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.drawable.ic_launcher);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.ic_launcher);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.ic_launcher);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.ic_launcher);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.ic_launcher);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.ic_launcher);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.ic_launcher);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.ic_launcher);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.ic_launcher);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.ic_launcher);
        fruitList.add(mango);

    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case 1:
//                if(resultCode == RESULT_OK) {
//                    String returnedData = data.getStringExtra("data_return");
//                    Log.d("MainActivity", returnedData);
//                }
//                break;
//            default:
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //menu item可用
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked remove", Toast.LENGTH_SHORT).show();
                break;
            case R.id.finish_item:
                finish();
            default:
        }
        return super.onOptionsItemSelected(item);
    }



    //保证活动关闭之前调用，保存数据
    public  void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key",tempData);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Tag, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag, "omStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag, "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag, "onDestroy");
        ActivityCollector.removeActivity(this);
    }
}
