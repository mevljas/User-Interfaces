package com.example.naloga5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.regex.Pattern;

public class CreditCard extends AppCompatActivity {

    public static CreditCard activity;

    EditText ime, priimek, kartica;
    String cardDefault;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        activity = this;

        ime = (EditText) findViewById(R.id.karticaIme);
        priimek = (EditText) findViewById(R.id.karticaPriimek);
        kartica = (EditText) findViewById(R.id.KarticaStevilka);


    }

    public void cancel(View view) {
        Intent result = new Intent(this, Osebe.class);
        result.putExtra("card", cardDefault);
        setResult(RESULT_CANCELED, result);
        finish();


    }

    public void checkData(final View v) {
        String imeText = ime.getText().toString();
        String priimekText = priimek.getText().toString();
        String karticaText = kartica.getText().toString();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        String karticaCleared = karticaText.replace("-", "");

        if (imeText.isEmpty() || imeText.length() < 2 || !Pattern.matches("[A-Za-z]+", imeText)) {
            Toast.makeText(context, "Napaka: ime ni pravilno vnešeno.", duration).show();
            ime.setBackgroundColor(Color.RED);
        } else if (priimekText.isEmpty() || priimekText.length() < 2 || !Pattern.matches("[A-Za-z]+", priimekText)) {
            Toast.makeText(context, "Napaka: priimek ni pravilno vnešen.", duration).show();
            ime.setBackgroundColor(Color.WHITE);
            priimek.setBackgroundColor(Color.RED);
        } else if (karticaCleared.isEmpty() || karticaCleared.trim().length() != 16 || !karticaCleared.trim().matches("[0-9]+")) {
            Toast.makeText(context, "Napaka: številka kartice ni pravilno vnešena.", duration).show();
            kartica.setBackgroundColor(Color.RED);
            ime.setBackgroundColor(Color.WHITE);
            priimek.setBackgroundColor(Color.WHITE);
        } else {
            ime.setBackgroundColor(Color.WHITE);
            priimek.setBackgroundColor(Color.WHITE);
            kartica.setBackgroundColor(Color.WHITE);
            Intent result = new Intent(this, Osebe.class);
            result.putExtra("card", karticaText);
            setResult(RESULT_OK, result);
            finish();
        }


    }
}
