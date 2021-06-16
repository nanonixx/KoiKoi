package com.myproject.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;

public class SettingsScreen extends BaseScreen {
    private Texture background;

    public SettingsScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        BaseImageButton buttonBack = new BaseImageButton("buttons/backButton.png", "buttons/playButtonPressed.png", 277, 70, 108, 128);

        background = new Texture("backgrounds/SettingsScreen.png");

        buttonBack.onClick(()-> setScreen(new MainMenuScreen(game)));
        stage.addActor(buttonBack);
    }


    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

}
