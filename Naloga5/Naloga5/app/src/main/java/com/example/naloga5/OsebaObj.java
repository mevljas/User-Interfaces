package com.example.naloga5;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class OsebaObj implements Serializable {
    String ime;
    String priimek;
    String datumRoj;
    String spol;
    int cena;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public OsebaObj(String ime, String priimek, String datumRoj, String spol) {
        this.ime = ime;
        this.priimek = priimek;
        this.datumRoj = datumRoj;
        this.spol = spol;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate localDate = LocalDate.parse(datumRoj, formatter);
        Period age = Period.between(localDate, LocalDate.now());
        int years = age.getYears();
        if (years < 2) {
            this.cena = 0;
        } else if (years <= 12) {
            this.cena = 20;
        } else {
            this.cena = 40;
        }
    }

    public String getIme() {
        return ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public String getDatumRoj() {
        return datumRoj;
    }

    public String getSpol() {
        return spol;
    }

    public int getCena() {
        return cena;

    }
}
