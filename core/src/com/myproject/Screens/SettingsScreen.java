package com.myproject.Screens;

import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;

public class SettingsScreen extends BaseScreen {
    public SettingsScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        BaseImageButton buttonBack = new BaseImageButton("buttons/backButton.png", "buttons/playButtonPressed.png", 277, 70, 108, 128);
        buttonBack.onClick(()-> setScreen(new MainMenuScreen(game)));
        stage.addActor(buttonBack);
    }
}
