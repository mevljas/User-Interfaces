package com.example.naloga5;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    public static  MainActivity activity;

    Button btnDatePicker;
    Button btnDatePicker2;
    Spinner destinationSpinner;
    Spinner classSpinner;
    Spinner classSpinnerPovratna;
    EditText txtDate;
    EditText txtDatePovratna;
    CheckBox povratna;
    ConstraintLayout wrapper;
    private int mYear, mMonth, mDay;
    private int tripPrice = 0;
    Date startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        btnDatePicker=(Button)findViewById(R.id.izberiDatum);
        btnDatePicker2=(Button)findViewById(R.id.izberiDatum3);
        classSpinner =(Spinner) findViewById(R.id.spinner2);
        classSpinnerPovratna =(Spinner) findViewById(R.id.spinner4);
        destinationSpinner =(Spinner) findViewById(R.id.destinacijeSpinner);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtDatePovratna=(EditText)findViewById(R.id.in_date3);
        povratna=(CheckBox) findViewById(R.id.povratna);
        wrapper=(ConstraintLayout) findViewById(R.id.wrapper);

        btnDatePicker.setOnClickListener(this);
        btnDatePicker2.setOnClickListener(this);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        txtDate.setText(String.format("%d.%d.%d", mDay, mMonth + 1, mYear));
        txtDatePovratna.setText(String.format("%d.%d.%d", mDay, mMonth + 1, mYear));
        startDate = new Date();
        setTripPrice();

        AdapterView.OnItemSelectedListener  priceChangeListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setTripPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        };

        classSpinner.setOnItemSelectedListener(priceChangeListener);
        destinationSpinner.setOnItemSelectedListener(priceChangeListener);
        povratna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTripPrice();
                if (povratna.isChecked()){
                    wrapper.setVisibility(View.VISIBLE);
                }
                else{
                    wrapper.setVisibility(View.INVISIBLE);
                }
            }
        });
        classSpinnerPovratna.setOnItemSelectedListener(priceChangeListener);
    }

    @Override
    public void onClick(final View v) {

        if (v == btnDatePicker || v == btnDatePicker2) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            if (v == btnDatePicker){
                                txtDate.setText(String.format("%d.%d.%d", dayOfMonth, monthOfYear + 1, year));
                                txtDatePovratna.setText(String.format("%d.%d.%d", dayOfMonth, monthOfYear + 1, year));
                                try {
                                    startDate = new SimpleDateFormat("dd/MM/yyyy").parse(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                txtDatePovratna.setText(String.format("%d.%d.%d", dayOfMonth, monthOfYear + 1, year));
                            }


                        }
                    }, mYear, mMonth, mDay);
            if (v == btnDatePicker2){
                datePickerDialog.getDatePicker().setMinDate(startDate.getTime());
            }
            else{
                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
            }

            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime() + 10000000000l);
            datePickerDialog.show();
        }


    }



    private void setTripPrice(){
        tripPrice = 50 + destinationSpinner.getSelectedItemPosition() * 3;
        tripPrice += classSpinner.getSelectedItemPosition() * 50;
        if (povratna.isChecked()){
            tripPrice += 30 + classSpinnerPovratna.getSelectedItemPosition() * 30;
        }
        ((TextView)findViewById(R.id.cenaLeta)).setText(String.format("%d €", tripPrice));
    }

    public void cancelClicked(View view){
        System.exit(0);
    }

    public void next(View view){
//        We use Intent for passing data between activities.
//        is object that can be passed between activities and that can contain data.
//        we have to provide the caller and the collie
        Intent intent= new Intent(this, Osebe.class);
        intent.putExtra("cenaLeta", tripPrice);
        startActivity(intent);

    }


}
