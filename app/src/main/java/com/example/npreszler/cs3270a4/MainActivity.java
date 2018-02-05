package com.example.npreszler.cs3270a4;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity 
            implements FragmentTax.OnTaxSeekBarChangeListener,
                        FragmentItems.OnTaxItemChangeListener {

    private FragmentManager fm;
    private FragmentTotals fragTotals;
    private FragmentTax fragTax;
    private FragmentItems fragItems;

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

    @Override
    protected void onResume() {
        super.onResume();

        fragTotals = (FragmentTotals) fm.findFragmentByTag("fragTotals");
        fragTax = (FragmentTax) fm.findFragmentByTag("fragTax");
        fragItems = (FragmentItems) fm.findFragmentByTag("fragItems");
    }

    @Override
    public void onTaxSeekValueChange(int value) {
        if(fragTotals != null && fragItems != null) {
            BigDecimal itemAmountTotal = fragItems.getItemAmountTotal();
            fragTax.setTxvTaxAmount(value, itemAmountTotal);
            fragTotals.setTotalAmount(value, itemAmountTotal);
        }
    }

    @Override
    public void onTaxItemChange() {
        if(fragTotals != null && fragTax != null){
            BigDecimal itemAmountTotal = fragItems.getItemAmountTotal();
            fragTax.setTxvTaxAmount(fragTax.getTaxSeekValue(), itemAmountTotal);
            fragTotals.setTotalAmount(fragTax.getTaxSeekValue(), itemAmountTotal);
        }
    }
}
