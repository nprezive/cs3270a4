package com.example.npreszler.cs3270a4;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.math.BigDecimal;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentItems extends Fragment {

    public interface OnTaxItemChangeListener {
        public void onTaxItemChange();
    }

    private OnTaxItemChangeListener mCallback;
    private View rootView;
    private EditText edtItemAmount1, edtItemAmount2, edtItemAmount3, edtItemAmount4;
    private final TextWatcher itemAmountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mCallback.onTaxItemChange();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public FragmentItems() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnTaxItemChangeListener) activity;
        }
        catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() +
                    " must implement OnTaxItemChangeListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_items, container, false);

        // Get pointers to each of the edtItemAmounts and add a TextChangedListener
        edtItemAmount1 = (EditText) rootView.findViewById(R.id.edtItemAmount1);
        edtItemAmount1.addTextChangedListener(itemAmountTextWatcher);
        edtItemAmount2 = (EditText) rootView.findViewById(R.id.edtItemAmount2);
        edtItemAmount2.addTextChangedListener(itemAmountTextWatcher);
        edtItemAmount3 = (EditText) rootView.findViewById(R.id.edtItemAmount3);
        edtItemAmount3.addTextChangedListener(itemAmountTextWatcher);
        edtItemAmount4 = (EditText) rootView.findViewById(R.id.edtItemAmount4);
        edtItemAmount4.addTextChangedListener(itemAmountTextWatcher);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        edtItemAmount1.setText(sp.getString("ItemAmount1", getString(R.string._0)));
        edtItemAmount2.setText(sp.getString("ItemAmount2", getString(R.string._0)));
        edtItemAmount3.setText(sp.getString("ItemAmount3", getString(R.string._0)));
        edtItemAmount4.setText(sp.getString("ItemAmount4", getString(R.string._0)));
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        sp.edit().putString("ItemAmount1", edtItemAmount1.getText().toString())
                .putString("ItemAmount2", edtItemAmount2.getText().toString())
                .putString("ItemAmount3", edtItemAmount3.getText().toString())
                .putString("ItemAmount4", edtItemAmount4.getText().toString())
                .commit();
    }

    public BigDecimal getItemAmountTotal() {
        String s1 = edtItemAmount1.getText().toString();
        String s2 = edtItemAmount2.getText().toString();
        String s3 = edtItemAmount3.getText().toString();
        String s4 = edtItemAmount4.getText().toString();

        BigDecimal a1 = s1.equals("") ? new BigDecimal(0) : new BigDecimal(s1);
        BigDecimal a2 = s2.equals("") ? new BigDecimal(0) : new BigDecimal(s2);
        BigDecimal a3 = s3.equals("") ? new BigDecimal(0) : new BigDecimal(s3);
        BigDecimal a4 = s4.equals("") ? new BigDecimal(0) : new BigDecimal(s4);

        return a1.add(a2).add(a3).add(a4);
    }
}
