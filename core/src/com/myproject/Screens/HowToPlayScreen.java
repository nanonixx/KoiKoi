package com.myproject.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;
import com.myproject.Screens.userManual.CardInfoScreen;
import com.myproject.Screens.userManual.DynsInfoScreen;
import com.myproject.Screens.userManual.GenInfoScreen;
import com.myproject.Screens.userManual.YakusInfoScreen;

public class HowToPlayScreen extends BaseScreen {
    public HowToPlayScreen(MyGame game) {
        super(game);
    }

    Texture background;
    private BaseImageButton buttonInfo, buttonDynamics, buttonCards, buttonYakus;
    private BaseImageButton buttongoBack, buttonPrev, buttonNext, buttonBack;

    Image main, genInfo, dyns, cards, yakus;

    @Override
    public void show() {
        super.show();
        background = new Texture("backgrounds/howToPlayMain.png");

        buttongoBack = new BaseImageButton("goBack", 140, 55, 847, 105);
        buttonPrev = new BaseImageButton("prev", 140, 55, 847, 105);
        buttonNext = new BaseImageButton("next", 140, 55, 1014, 105);
        buttonBack = new BaseImageButton("back", 274, 65, 116, 59);


        buttonInfo = new BaseImageButton("ir", 140, 55, 702, 446);
        buttonInfo.onClick(()-> setScreen(new GenInfoScreen(game)));
        stage.addActor(buttonInfo);

        buttonDynamics = new BaseImageButton("ir", 140, 55, 702, 370);
        buttonDynamics.onClick(()-> setScreen(new DynsInfoScreen(game)));
        stage.addActor(buttonDynamics);

        buttonCards = new BaseImageButton("ir", 140, 55, 702, 287);
        buttonCards.onClick(()-> setScreen(new CardInfoScreen(game)));
        stage.addActor(buttonCards);

        buttonYakus = new BaseImageButton("ir", 140, 55, 702, 209);
        buttonYakus.onClick(()-> setScreen(new YakusInfoScreen(game)));
        stage.addActor(buttonYakus);



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
