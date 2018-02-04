package com.example.npreszler.cs3270a4;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTax extends Fragment {

    public interface OnTaxSeekBarChangeListener {
        public void onTaxSeekValueChange(int value);
    }

    OnTaxSeekBarChangeListener mCallback;
    View rootView;
    SeekBar sbTax;
    TextView txvTaxRate, txvTaxAmount;

    public FragmentTax() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnTaxSeekBarChangeListener) activity;
        }
        catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() +
                    " must implement OnTaxSeekBarChangeListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tax, container, false);
        sbTax = (SeekBar) rootView.findViewById(R.id.sbTax);
        txvTaxRate = (TextView) rootView.findViewById(R.id.txvTaxRate);
        txvTaxAmount = (TextView) rootView.findViewById(R.id.txvTaxAmount);

        sbTax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setTxvTaxRate(i);
                mCallback.onTaxSeekValueChange(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return rootView;
    }

    public void setTxvTaxRate(int value) {
        double taxRate = ((double) value) * 25 / 100;
        //TODO use a resource string with placeholders instead
        txvTaxRate.setText(String.format("%.2f", taxRate) + "%");
    }

    public void setTxvTaxAmount(int value, BigDecimal subTotal) {
        double taxRate = ((double) value) * 25 / 100 / 100;
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        //TODO de-localize it (no dollar sign)
        txvTaxAmount.setText("$" + df.format(subTotal.multiply(new BigDecimal(taxRate))));
    }

    public int getTaxSeekValue() {
        return sbTax.getProgress();
    }

}
