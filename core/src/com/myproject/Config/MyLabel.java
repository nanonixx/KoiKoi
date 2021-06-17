package com.myproject.Config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MyLabel extends Label {
    static LabelStyle sLabelStyle = new LabelStyle();
    private static final com.badlogic.gdx.Gdx Gdx = new Gdx();

    static {
//        sLabelStyle.font = new BitmapFont(com.badlogic.gdx.Gdx.files.internal("fonts/Arial/Arial.fnt"), Gdx.files.internal("fonts/Arial/Arial.png"), false);
        sLabelStyle.font = new BitmapFont();

    }

    LabelStyle labelStyle;
    public MyLabel(){
        super("", sLabelStyle);
//
//        labelStyle = new LabelStyle();
//        labelStyle.font = new BitmapFont();
//
//        setStyle(labelStyle);
    }

    public MyLabel(Color color){
        this();
        getStyle().fontColor = color;
        getStyle().font.getData().setScale(0.5f);
    }

    public MyLabel(CharSequence charSequence, Color color, float x, float y){
        this(color);
        setText(charSequence);
        setPosition(x, y);
        setFontScale(2.0f);
        labelStyle = sLabelStyle;
    }
    public MyLabel(CharSequence charSequence, Color color, float x, float y, float size){
        this(color);
        setText(charSequence);
        setPosition(x, y);
        setFontScale(size);
        labelStyle = sLabelStyle;
    }


    public MyLabel(float x, float y, Color color){
        this(color);
        setPosition(x, y);
    }

    public MyLabel(CharSequence charSequence, float x, float y, Color color){
        this(x, y, color);
        setText(charSequence);
    }
}