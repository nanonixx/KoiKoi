package com.myproject.Screens.userManual;

import com.badlogic.gdx.graphics.Texture;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;
import com.myproject.Screens.HowToPlayScreen;

public class YakusInfoScreen extends BaseScreen {
    public YakusInfoScreen(MyGame game) {
        super(game);
    }

    Texture background;
    private BaseImageButton buttongoBack, buttonPrev;

    @Override
    public void show() {
        super.show();
        background = new Texture("backgrounds/yakus4.png");

        buttongoBack = new BaseImageButton("goBack", 140, 55, 1014, 105);
        buttonPrev = new BaseImageButton("prev", 140, 55, 847, 105);

        buttonPrev.onClick(()-> setScreen(new CardInfoScreen(game)));
        stage.addActor(buttonPrev);

        buttongoBack.onClick(()-> setScreen(new HowToPlayScreen(game)));
        stage.addActor(buttongoBack);

    }

    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

}
