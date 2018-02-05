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
import java.text.DecimalFormat;


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
        //TODO de-localize (no dollar sign) it
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        double taxRate = ((double) value) * 25 / 100 / 100 + 1;
        txvAmount.setText("$" + df.format(subTotal.multiply(new BigDecimal(taxRate))));
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor spEdit = sp.edit();

        spEdit.putString("totalAmount", txvAmount.getText().toString());
        spEdit.commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        txvAmount.setText(sp.getString("totalAmount", getString(R.string.bling_0_dot_00)));
    }
}
