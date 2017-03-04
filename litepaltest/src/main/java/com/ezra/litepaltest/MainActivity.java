package com.ezra.litepaltest;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });

        Button updateData = (Button) findViewById(R.id.updata_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");
                Util.showToast(MainActivity.this, String.valueOf(book.getPrice()));
            }
        });

        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "price < ?", "15");
                Util.showToast(MainActivity.this, "delete success");
            }
        });

        Button quertData = (Button) findViewById(R.id.query_data);
        quertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询所有数据
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book: books) {
                    Log.d(TAG, "book name is " + book.getName());
                    Log.d(TAG, "book author is " + book.getAuthor());
                    Log.d(TAG, "book pages is " + book.getPages());
                    Log.d(TAG, "book price is " + book.getPrice());
                    Log.d(TAG, "book press is " + book.getPress());
                }
                //查询单条数据
                //select()指定查询哪几列数据
                //where()指定查询的约束条件
                //order()指定结果的排序方式
                //limit()指定查询结果的数量
                //offset()指定结果的偏移量
//                List<Book> bookList = DataSupport.select("name", "author", "pages")
//                                                 .where("pages > ?", "400")
//                                                 .order("pages")
//                                                 .limit(10)
//                                                 .offset(10)
//                                                 .find(Book.class);
                Cursor cursor = DataSupport.findBySQL("select * from Book where pages > ? and price < ?",
                        "400", "20");
                cursor.moveToFirst();
                Log.d(TAG, cursor.getString(cursor.getColumnIndex("name")));
            }
        });
    }
}
