package com.myproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;
import com.myproject.Object.Carta;
import com.myproject.Object.Mazo;

public class GameScreen extends BaseScreen {

    private Texture background;
    private BaseImageButton buttonBack;
    public Mazo mazo = new Mazo();

    public GameScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        background = new Texture("backgrounds/GameScreen.png");

        buttonBack = new BaseImageButton("buttons/back.png", "buttons/back.png", 54, 54, 0, 666);
        buttonBack.onClick(()-> setScreen(new MainMenuScreen(game)));
        stage.addActor(buttonBack);

        mostrarCartas();

    }

    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    public void mostrarCartas(){

        for (int i = 0; i <3 ; i++) {
            Carta c =mazo.getAprilCard(66, 74);

            stage.addActor(c);
            System.out.println("cartinga");
        }
    }
}
