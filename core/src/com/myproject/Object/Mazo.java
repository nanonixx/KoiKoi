package com.myproject.Object;

import com.badlogic.gdx.graphics.Texture;

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
        new Carta("BASE", 1, "NORMAL", "january3"),
        new Carta("BASE", 1, "NORMAL", "january4"),
        new Carta("RIBBON", 1, "RED", "january2"),
        new Carta("LIGHT", 1, "MOON", "january1"),

   //FEBRERO
        new Carta("BASE", 2, "NORMAL", "february3"),
        new Carta("BASE", 2, "NORMAL", "february4"),
        new Carta("TANE", 2, "NORMAL", "february1"),
        new Carta("RIBBON", 2, "RED", "february2"),

   //MARZO
        new Carta("BASE", 3, "NORMAL", "march3"),
        new Carta("BASE", 3, "NORMAL", "march4"),
        new Carta("LIGHT", 3, "FLOWER", "march1"),
        new Carta("RIBBON", 3, "RED", "march2"),

    //ABRIL
        new Carta("BASE", 4, "NORMAL", "april3"),
        new Carta("BASE", 4, "NORMAL", "april4"),
        new Carta("TANE", 4, "NORMAL", "april1"),
        new Carta("RIBBON", 4, "NORMAL", "april2"),

    //MAYO
        new Carta("BASE", 5, "NORMAL", "may4"),
        new Carta("BASE", 5, "NORMAL", "may3"),
        new Carta("TANE", 5, "NORMAL", "may1"),
        new Carta("RIBBON", 5, "NORMAL", "may2"),

    //JUNIO
        new Carta("BASE", 5, "NORMAL", "june4"),
        new Carta("BASE", 5, "NORMAL", "june3"),
        new Carta("TANE", 5, "BDB", "june1"),
        new Carta("RIBBON", 5, "BLUE", "june2"),

    //JULIO
        new Carta("BASE", 5, "NORMAL", "july4"),
        new Carta("BASE", 5, "NORMAL", "july3"),
        new Carta("TANE", 5, "BDB", "july1"),
        new Carta("RIBBON", 5, "NORMAL", "july2"),

    //AGOSTO
        new Carta("BASE", 8, "NORMAL", "august3"),
        new Carta("BASE", 8, "NORMAL", "august4"),
        new Carta("TANE", 8, "NORMAL", "august2"),
        new Carta("LIGHT", 8, "MOON", "august1"),

    //SEPTIEMBRE
        new Carta("BASE", 8, "NORMAL", "september3"),
        new Carta("BASE", 8, "NORMAL", "september4"),
        new Carta("TANE", 8, "SAKE", "september1"),
        new Carta("RIBBON", 8, "BLUE", "september2"),

    //OCTUBRE
        new Carta("BASE", 8, "NORMAL", "october3"),
        new Carta("BASE", 8, "NORMAL", "october4"),
        new Carta("TANE", 8, "BDB", "october1"),
        new Carta("RIBBON", 8, "BLUE", "october2"),

    //NOVIEMBRE
        new Carta("BASE", 8, "NORMAL", "november4"),
        new Carta("LIGHT", 8, "RAIN", "november1"),
        new Carta("TANE", 8, "NORMAL", "november2"),
        new Carta("RIBBON", 8, "NORMAL", "november3"),

    //DICIEMBRE
        new Carta("BASE", 8, "NORMAL", "december4"),
        new Carta("BASE", 8, "NORMAL", "december3"),
        new Carta("BASE", 8, "NORMAL", "december2"),
        new Carta("LIGHT", 8, "NORMAL", "december1")


                ));

    }

//    Carta robar(){
//        return cartaList.get(random.nextInt(cartaList.size()));
//    }

    public void seeCards () {
        System.out.println(cartaList.toString());

    }

    public Carta getAprilCard(float x, float y){
        Carta a;
        for (Carta c :cartaList) {
            if (c.image.equals("april2")){
                a = c;
                a.setPosition(x, y);
                return a;

            }
        }
        return null;

    }
}
