package com.example.naloga5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Summary extends AppCompatActivity {

    Spinner zelenaDestinacija;
    Spinner zeleniRazred;
    TextView datumOdhoda;
    CheckBox povratnaVoz;
    Spinner zeleniRazredPrihoda;
    TextView datumPrihoda;
    TextView kartica;
    TextView cenaSkupaj;
    int steviloOseb;

    TextView summaryDestinacija;
    TextView summarryDatumOdhoda;
    TextView summaryRazredOdhoda;
    TextView summaryDatumPrihoda;
    TextView summaryRazredPrihoda;
    TextView summarySteviloOseb;
    TextView summaryStevilkaKartice;
    TextView znesekSkupaj;
    ConstraintLayout prihodPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        zelenaDestinacija = MainActivity.activity.findViewById(R.id.destinacijeSpinner);
        datumOdhoda = MainActivity.activity.findViewById(R.id.in_date);
        zeleniRazred = MainActivity.activity.findViewById(R.id.spinner2);
        povratnaVoz = MainActivity.activity.findViewById(R.id.povratna);
        datumPrihoda = MainActivity.activity.findViewById(R.id.in_date3);
        zeleniRazredPrihoda = MainActivity.activity.findViewById(R.id.spinner4);
        kartica = Osebe.activity.findViewById(R.id.kartica);
        steviloOseb = Osebe.activity.stOseb;
        cenaSkupaj = Osebe.activity.cenaSkupaj;
        summaryDestinacija = findViewById(R.id.summaryDestinacija);
        summarryDatumOdhoda = findViewById(R.id.summarryDatumOdhoda);
        summaryRazredOdhoda = findViewById(R.id.summaryRazredOdhoda);
        summaryDatumPrihoda = findViewById(R.id.summaryDatumPrihoda);
        summaryRazredPrihoda = findViewById(R.id.summaryRazredPrihoda);
        summarySteviloOseb = findViewById(R.id.summarySteviloOsebSt);
        summaryStevilkaKartice = findViewById(R.id.summaryStevilkaKartice);
        znesekSkupaj = findViewById(R.id.summaryZnesek);
        prihodPanel = findViewById(R.id.prihodPanel);


        summaryDestinacija.setText(zelenaDestinacija.getSelectedItem().toString());
        summarryDatumOdhoda.setText(datumOdhoda.getText());
        summaryRazredOdhoda.setText(zeleniRazred.getSelectedItem().toString());

        if (povratnaVoz.isChecked()) {
            prihodPanel.setVisibility(View.VISIBLE);
            summaryDatumPrihoda.setText(datumPrihoda.getText());
            summaryRazredPrihoda.setText(zeleniRazredPrihoda.getSelectedItem().toString());
        } else {
            prihodPanel.setVisibility(View.INVISIBLE);
        }


        summarySteviloOseb.setText(steviloOseb + "");
        summaryStevilkaKartice.setText(kartica.getText());
        znesekSkupaj.setText(cenaSkupaj.getText());


    }

    public void confirm(View v) {
        Intent intent = new Intent(this, End.class);
        startActivity(intent);
    }

    public void back(View v) {
        finish();
    }
}
