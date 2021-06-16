package com.myproject.Screens.userManual;

import com.badlogic.gdx.graphics.Texture;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;

public class CardInfoScreen extends BaseScreen {
    public CardInfoScreen(MyGame game) {
        super(game);
    }

    Texture background;
    private BaseImageButton  buttonPrev, buttonNext;

    @Override
    public void show() {
        super.show();
        background = new Texture("backgrounds/cards2.png");

        buttonPrev = new BaseImageButton("prev", 140, 55, 847, 105);
        buttonNext = new BaseImageButton("next", 140, 55, 1014, 105);

        buttonPrev.onClick(()-> setScreen(new DynsInfoScreen(game)));
        stage.addActor(buttonPrev);

        buttonNext.onClick(()-> setScreen(new YakusInfoScreen(game)));
        stage.addActor(buttonNext);



    }
    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

}
