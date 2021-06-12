package com.myproject.Object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.myproject.Assets;
import com.myproject.Config.MyActor;

public class Carta extends MyActor {

    private final int WIDTH = 106;
    private final int HEIGHT = 167;


    private String tipo;
    private int mes;
    private String special;
    //ENUM: NORMAL, RED, BLUE, BDB, RAIN, FLOWER, MOON


    //GETTERS / SETTERS

    public String getImage() {
        return image;
    }

    public String getTipo() {
        return tipo;
    }

    public int getMes() {
        return mes;
    }

    public String getSpecial() {
        return special;
    }

    //CONSTRUCTORS

    public Carta(String tipo, int mes, String special, String image) {
        this.tipo = tipo;
        this.mes = mes;
        this.special = special;
        this.image = image;
        setWidth(WIDTH); setHeight(HEIGHT);
//        animation = Assets.getAnimation(image, 10, Animation.PlayMode.LOOP);
    }

    public Carta() {
    }

    //FUNCTIONS

}
