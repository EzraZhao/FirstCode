package test.ezra.com.recyclertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ezra on 2017/2/21.
 */

public class VerticalRollActivity extends AppCompatActivity {

    private List<Fruit> mfruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        FruitAdapter adapter = new FruitAdapter(mfruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {
        for(int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.ic_launcher);
            mfruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.ic_launcher);
            mfruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.ic_launcher);
            mfruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.ic_launcher);
            mfruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.ic_launcher);
            mfruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.ic_launcher);
            mfruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.ic_launcher);
            mfruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.ic_launcher);
            mfruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.ic_launcher);
            mfruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.ic_launcher);
            mfruitList.add(mango);
        }
    }
}
