package com.myproject.Object;

public class Carta {

    private String tipo;
    private int mes;
    private String special;
    //ENUM: NORMAL, RED, BLUE, BDB, RAIN, FLOWER, MOON


    public String getTipo() {
        return tipo;
    }

    public int getMes() {
        return mes;
    }

    public String getSpecial() {
        return special;
    }

    public Carta(String tipo, int mes, String special) {
        this.tipo = tipo;
        this.mes = mes;
        this.special = special;
    }
}
