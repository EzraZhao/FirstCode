package test.xz.com.fragmenttest;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //碎片管理
        FragmentTransaction fragmentTransaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
        ContactFragment contactFragment = new ContactFragment();
        fragmentTransaction.add(R.id.container,contactFragment);
        fragmentTransaction.commit();

    }
}
