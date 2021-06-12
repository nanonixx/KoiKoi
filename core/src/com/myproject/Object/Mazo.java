package com.myproject.Object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Mazo {
//    public Carta(String tipo, int mes, String special)

    static Random random = new Random();
    public List<Carta> cartaList = new ArrayList<>();

    public Mazo(){


        cartaList.addAll(Arrays.asList(

    //ENERO
        new Carta("BASE", 1, "NORMAL"),
        new Carta("BASE", 1, "NORMAL"),
        new Carta("RIBBON", 1, "RED"),
        new Carta("LIGHT", 1, "MOON"),

   //FEBRERO
        new Carta("BASE", 2, "NORMAL"),
        new Carta("BASE", 2, "NORMAL"),
        new Carta("TANE", 2, "NORMAL"),
        new Carta("RIBBON", 2, "RED"),

   //MARZO
        new Carta("BASE", 3, "NORMAL"),
        new Carta("BASE", 3, "NORMAL"),
        new Carta("LIGHT", 3, "FLOWER"),
        new Carta("RIBBON", 3, "RED"),

    //ABRIL
        new Carta("BASE", 4, "NORMAL"),
        new Carta("BASE", 4, "NORMAL"),
        new Carta("TANE", 4, "NORMAL"),
        new Carta("RIBBON", 4, "NORMAL"),

    //MAYO
        new Carta("BASE", 5, "NORMAL"),
        new Carta("BASE", 5, "NORMAL"),
        new Carta("TANE", 5, "NORMAL"),
        new Carta("RIBBON", 5, "NORMAL"),

    //JUNIO
        new Carta("BASE", 5, "NORMAL"),
        new Carta("BASE", 5, "NORMAL"),
        new Carta("TANE", 5, "BDB"),
        new Carta("RIBBON", 5, "BLUE"),

    //JULIO
        new Carta("BASE", 5, "NORMAL"),
        new Carta("BASE", 5, "NORMAL"),
        new Carta("TANE", 5, "BDB"),
        new Carta("RIBBON", 5, "NORMAL"),

    //AGOSTO
        new Carta("BASE", 8, "NORMAL"),
        new Carta("BASE", 8, "NORMAL"),
        new Carta("TANE", 8, "NORMAL"),
        new Carta("LIGHT", 8, "MOON"),

    //SEPTIEMBRE
        new Carta("BASE", 8, "NORMAL"),
        new Carta("BASE", 8, "NORMAL"),
        new Carta("TANE", 8, "SAKE"),
        new Carta("RIBBON", 8, "BLUE"),

    //OCTUBRE
        new Carta("BASE", 8, "NORMAL"),
        new Carta("BASE", 8, "NORMAL"),
        new Carta("TANE", 8, "BDB"),
        new Carta("RIBBON", 8, "BLUE"),

    //NOVIEMBRE
        new Carta("BASE", 8, "NORMAL"),
        new Carta("LIGHT", 8, "RAIN"),
        new Carta("TANE", 8, "NORMAL"),
        new Carta("RIBBON", 8, "NORMAL"),

    //DICIEMBRE
        new Carta("BASE", 8, "NORMAL"),
        new Carta("BASE", 8, "NORMAL"),
        new Carta("BASE", 8, "NORMAL"),
        new Carta("LIGHT", 8, "NORMAL")


                ));

    }

//    Carta robar(){
//        return cartaList.get(random.nextInt(cartaList.size()));
//    }

    public void seeCards () {
        System.out.println(cartaList.toString());

    }
}
