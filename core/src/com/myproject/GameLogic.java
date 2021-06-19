package com.myproject;

import com.myproject.Object.Carta;
import com.myproject.Object.Yaku;
import com.myproject.Screens.GameScreen;

import java.util.ArrayList;

public class GameLogic {
    private final MyGame game;




    public GameLogic(MyGame game) {
        this.game = game;
    }

    public static ArrayList<Yaku> checkyakus(ArrayList<Carta> cartasGuardadas){
       ArrayList<Yaku> yakus = new ArrayList<>();

        if (checkBasicas(cartasGuardadas)) yakus.add(new Yaku("basic", 1));
        if (checkRibbons(cartasGuardadas)) yakus.add(new Yaku("ribbon", 1));
        if (checkTane(cartasGuardadas)) yakus.add(new Yaku("tane", 1));

        if (checkAotan(cartasGuardadas)) yakus.add(new Yaku("aotan", 5));
        if (checkAkatan(cartasGuardadas)) yakus.add(new Yaku("akatan", 5));
        if (checkBDB(cartasGuardadas)) yakus.add(new Yaku("bdb", 5));

        if (checkLuna(cartasGuardadas)) yakus.add(new Yaku("moon", 5));
        if (checkFlores(cartasGuardadas)) yakus.add(new Yaku("flower", 5));

        if (checkThree(cartasGuardadas)) yakus.add(new Yaku("threeLights", 5));
        if (checkFour(cartasGuardadas)) yakus.add(new Yaku("fourLights", 8));
        if (checkFive(cartasGuardadas)) yakus.add(new Yaku("fiveLights", 10));

        return yakus;

    }

    public static boolean checkBasicas (ArrayList<Carta> cartasGuardadas) {
        int basicas = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getTipo().equals("BASE")) basicas++;
        }
        return basicas >= 10;
    }

    public static boolean checkRibbons (ArrayList<Carta> cartasGuardadas) {
        int ribbon = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getTipo().equals("RIBBON")) ribbon++;
        }
        return ribbon >= 5;
    }

    public static boolean checkTane(ArrayList<Carta> cartasGuardadas) {
        int tane = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getTipo().equals("TANE")) tane++;
        }
        return tane >= 5;
    }

    public static boolean checkAotan(ArrayList<Carta> cartasGuardadas) {
        int aotan = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getSpecial().equals("BLUE")) aotan++;
        }
        return aotan >= 3;
    }

    public static boolean checkAkatan(ArrayList<Carta> cartasGuardadas) {
        int akatan = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getSpecial().equals("RED")) akatan++;
        }
        return akatan >= 3;
    }

    public static boolean checkBDB(ArrayList<Carta> cartasGuardadas) {
        int bdb = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getSpecial().equals("BDB")) bdb++;
        }
        return bdb >= 3;
    }

    public static boolean checkLuna(ArrayList<Carta> cartasGuardadas) {
        int luna = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getSpecial().equals("MOON")) luna++;
            if (c.getSpecial().equals("SAKE")) luna++;

        }
        return luna == 2;
    }

    public static boolean checkFlores(ArrayList<Carta> cartasGuardadas) { //TODO cambiar bool por int puntos
        int flores = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getSpecial().equals("FLOWER")) flores++;
            if (c.getSpecial().equals("SAKE")) flores++;

        }
        return flores == 2;
    }

    public static boolean checkThree(ArrayList<Carta> cartasGuardadas) {
        int three = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getTipo().equals("LIGHT") && !c.getSpecial().equals("RAIN")) three++;
        }
        return three == 3;
    }

    public static boolean checkFour(ArrayList<Carta> cartasGuardadas) {
        int four = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getTipo().equals("LIGHT") && !c.getSpecial().equals("RAIN")) four++;
        }
        return four == 4;
    }
    public static boolean checkFive(ArrayList<Carta> cartasGuardadas) {
        int five = 0;
        for (Carta c : cartasGuardadas) {
            if (c.getTipo().equals("LIGHT")) five++;
        }
        return five == 5;
    }


}
