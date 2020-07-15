package com.example.naloga5;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Osebe extends AppCompatActivity {

    public static Osebe activity;

    //    Tell the application whether the request has been returned or not.
    private final int REQUEST = 1;
    private final int REQUEST_MODIFY = 2;
    private final int REQUEST_CARD = 3;
    public int stOseb = 0;
    RecyclerView recyclerView;
    ClickAdapter clickAdapter;
    int modifyRowNum;
    TextView cenaSkupaj, seznamText, creditCard, creditCardLabel;
    int cenaLeta;
    private ArrayList<OsebaObj> listOseb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osebe);
        activity = this;
        listOseb = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setRecyclerView();
        cenaSkupaj = (TextView) findViewById(R.id.cenaSkupaj);
        seznamText = (TextView) findViewById(R.id.seznamText);
        creditCard = (TextView) findViewById(R.id.kartica);
        creditCardLabel = (TextView) findViewById(R.id.karticaLabel);
        Intent intent = getIntent();
        cenaLeta = intent.getIntExtra("cenaLeta", 0);
        setSumPrice();


    }

    public void back(View view) {
        finish();

    }

    public void addPerson(View view) {
        Intent intent = new Intent(this, Oseba.class);
        startActivityForResult(intent, REQUEST);
        seznamText.setTextColor(Color.GRAY);

    }

    @Override
    protected void onActivityResult(int reqC, int resC, Intent data) {
        super.onActivityResult(reqC, resC, data);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        if (reqC == REQUEST) {
            if (resC == RESULT_OK) {
                if (data.hasExtra("osebaObj")) {
                    listOseb.add((OsebaObj) data.getSerializableExtra("osebaObj"));
                    System.out.println(listOseb.get(0));
                    setRecyclerView();
                    Toast.makeText(context, "Vnos osebe uspešen.", duration).show();
                }

            }
            setSumPrice();

        } else if (reqC == REQUEST_MODIFY) {
            if (resC == RESULT_OK) {
                if (data.hasExtra("osebaObj")) {
                    listOseb.remove(modifyRowNum);
                    listOseb.add(modifyRowNum, (OsebaObj) data.getSerializableExtra("osebaObj"));
                    setRecyclerView();
                    Toast.makeText(context, "Sprememba osebe uspešna.", duration).show();
                }

            } else if (resC == -5) {
                listOseb.remove(modifyRowNum);
                setRecyclerView();
            }
            setSumPrice();
        } else if (reqC == REQUEST_CARD) {
            if (resC == RESULT_OK) {
                if (data.hasExtra("card")) {
                    creditCard.setText(data.getStringExtra("card"));
                    Toast.makeText(context, "Vpis kreditne kartice uspešen.", duration).show();
                }

            }
        }
    }

    private void setRecyclerView() {
        // Setting up the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //RecyclerView with a click listener

        clickAdapter = new ClickAdapter(listOseb);
        clickAdapter.setOnEntryClickListener(new ClickAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                modifyRowNum = position;
                Intent intent = new Intent(Osebe.this, Oseba.class);
                intent.putExtra("osebaObj", (Serializable) listOseb.get(modifyRowNum));
                startActivityForResult(intent, REQUEST_MODIFY);

            }
        });
        recyclerView.setAdapter(clickAdapter);
        stOseb = listOseb.size();
    }

    private void setSumPrice() {
        int price = 0;
        for (OsebaObj obj : listOseb) {
            price += obj.getCena();
        }
        price += cenaLeta;
        cenaSkupaj.setText(price + " €");
    }

    public void checkDAta(final View v) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        if (listOseb.isEmpty()) {
            Toast.makeText(context, "Napaka: Vnesti je potrebno vsaj eno osebo.", duration).show();
            seznamText.setTextColor(Color.RED);
            creditCard.setBackgroundColor(Color.WHITE);
        } else if ((creditCard.getText().toString()).equals("xxxx-xxxx-xxxx-xxxx")) {
            Toast.makeText(context, "Napaka: Vnesti je potrebno številko kartice.", duration).show();
            creditCard.setBackgroundColor(Color.RED);
            seznamText.setTextColor(Color.GRAY);
        } else {
            seznamText.setTextColor(Color.GRAY);
            creditCard.setBackgroundColor(Color.WHITE);
            Intent intent = new Intent(this, Summary.class);
            startActivity(intent);
        }


    }

    public void enterCard(View view) {
        Intent intent = new Intent(this, CreditCard.class);
        startActivityForResult(intent, REQUEST_CARD);
        seznamText.setTextColor(Color.GRAY);
        creditCard.setBackgroundColor(Color.WHITE);

    }


}
