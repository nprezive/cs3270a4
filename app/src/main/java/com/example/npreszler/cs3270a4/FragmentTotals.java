package com.example.npreszler.cs3270a4;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.math.BigDecimal;
import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTotals extends Fragment {

    private View rootView;
    private TextView txvAmount;

    public FragmentTotals() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_totals, container, false);

        txvAmount = rootView.findViewById(R.id.txvTotalAmount);

        return rootView;
    }

    public void setTotalAmount(int value, BigDecimal subTotal) {
        double taxRate = ((double) value) * 25 / 100 / 100 + 1;
        BigDecimal total = subTotal.multiply(new BigDecimal(taxRate));

        NumberFormat format = NumberFormat.getCurrencyInstance();
        txvAmount.setText(format.format(total));
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        sp.edit()
                .putString("totalAmount", txvAmount.getText().toString())
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        txvAmount.setText(sp.getString("totalAmount", getString(R.string.bling_0_dot_00)));
    }
}
