package test.ezra.com.recyclertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button verticalRoll;
    private Button horizontalRoll;
    private Button staggeredGrid;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verticalRoll = (Button) findViewById(R.id.vertical_roll_btn);
        verticalRoll.setOnClickListener(this);
        horizontalRoll = (Button) findViewById(R.id.horizontal_roll_btn);
        horizontalRoll.setOnClickListener(this);
        staggeredGrid = (Button) findViewById(R.id.staggeredGird_btn);
        staggeredGrid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //竖直滚动
            case R.id.vertical_roll_btn:
                intent = new Intent(MainActivity.this, VerticalRollActivity.class);
                startActivity(intent);
                break;
            //水平滚动
            case  R.id.horizontal_roll_btn:
                intent = new Intent(MainActivity.this, HorizontalRollActivity.class);
                startActivity(intent);
                break;
            //瀑布流式
            case R.id.staggeredGird_btn:
                intent = new Intent(MainActivity.this, StaggeredGridActivity.class);
                startActivity(intent);
                break;
        }
    }

}
