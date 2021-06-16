package com.myproject.Screens.userManual;

import com.badlogic.gdx.graphics.Texture;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;
import com.myproject.Screens.HowToPlayScreen;

public class GenInfoScreen extends BaseScreen {
    public GenInfoScreen(MyGame game) {
        super(game);
    }

    Texture background;
    private BaseImageButton buttongoBack, buttonNext;

    @Override
    public void show() {
        super.show();
        background = new Texture("backgrounds/general1.png");

        buttongoBack = new BaseImageButton("goBack", 140, 55, 847, 105);
        buttonNext = new BaseImageButton("next", 140, 55, 1014, 105);

        buttongoBack.onClick(()-> setScreen(new HowToPlayScreen(game)));
        stage.addActor(buttongoBack);

        buttonNext.onClick(()-> setScreen(new DynsInfoScreen(game)));
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
