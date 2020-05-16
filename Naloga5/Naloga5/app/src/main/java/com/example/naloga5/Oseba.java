package com.example.naloga5;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Oseba extends AppCompatActivity implements
        View.OnClickListener {

    public static Oseba activity;

    Button btnDatePicker;
    Button izbrisi;
    EditText txtDate, ime, priimek;
    RadioButton moski;
    RadioButton zenski;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oseba);

        activity = this;

        btnDatePicker = (Button) findViewById(R.id.izberiDatum2);
        izbrisi = (Button) findViewById(R.id.izbrisi);
        txtDate = (EditText) findViewById(R.id.datumRoj);
        ime = (EditText) findViewById(R.id.ime);
        priimek = (EditText) findViewById(R.id.priimek);

        btnDatePicker.setOnClickListener(this);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        txtDate.setText(String.format("%02d.%02d.%d", mDay, mMonth + 1, 1900));
        moski = (RadioButton) findViewById(R.id.moski);
        zenski = (RadioButton) findViewById(R.id.zenski);
        Intent intent = getIntent();
        if (intent.hasExtra("osebaObj")) {
            OsebaObj osebaObj = (OsebaObj) intent.getSerializableExtra("osebaObj");
            ime.setText(osebaObj.getIme());
            priimek.setText(osebaObj.getPriimek());
            txtDate.setText(osebaObj.getDatumRoj());
            if (osebaObj.getSpol().equals("moški")) {
                System.out.println("moski");
                moski.setChecked(true);
            } else {
                System.out.println("zenski");
                zenski.setChecked(true);
            }
            izbrisi.setVisibility(View.VISIBLE);
        }

    }

    public void cancel(View view) {
        finish();


    }

    @Override
    public void onClick(final View v) {

        if (v == btnDatePicker) {

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

                            txtDate.setText(String.format("%02d.%02d.%d", dayOfMonth, monthOfYear + 1, year));


                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void checkDAta(final View v) {
        String imeText = ime.getText().toString();
        String priimekText = priimek.getText().toString();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        if (imeText.isEmpty() || imeText.length() < 2 || !Pattern.matches("[A-Za-z]+", imeText)) {
            Toast.makeText(context, "Napaka: ime ni pravilno vnešeno.", duration).show();
            ime.setBackgroundColor(Color.RED);
        } else if (priimekText.isEmpty() || priimekText.length() < 2 || !Pattern.matches("[A-Za-z]+", priimekText)) {
            Toast.makeText(context, "Napaka: priimek ni pravilno vnešen.", duration).show();
            ime.setBackgroundColor(Color.WHITE);
            priimek.setBackgroundColor(Color.RED);
        } else {
            ime.setBackgroundColor(Color.WHITE);
            priimek.setBackgroundColor(Color.WHITE);
            OsebaObj osebaObj = new OsebaObj(imeText, priimekText, txtDate.getText().toString(), moski.isChecked() ? "moški" : "ženski");
            Intent result = new Intent(this, Osebe.class);
            result.putExtra("osebaObj", (Serializable) osebaObj);
            setResult(RESULT_OK, result);
            finish();
        }


    }

    public void deletePerson(final View v) {
        Intent result = new Intent(this, Osebe.class);
        setResult(-5, result);
        finish();
    }
}
