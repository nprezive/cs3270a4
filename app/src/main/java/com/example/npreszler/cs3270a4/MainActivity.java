package com.example.npreszler.cs3270a4;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.frame_totals, new FragmentTotals(), "fragTotals")
                .replace(R.id.frame_tax, new FragmentTax(), "fragTax")
                .replace(R.id.frame_items, new FragmentItems(), "fragItems")
                .commit();
    }
}
