package com.myproject.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;

public class HowToPlayScreen extends BaseScreen {
    public HowToPlayScreen(MyGame game) {
        super(game);
    }

    Texture background;
    private BaseImageButton buttonInfo, buttonDynamics, buttonCards, buttonYakus;

    Image main, genInfo, dyns, cards, yakus;

    @Override
    public void show() {
        super.show();
        background = new Texture("backgrounds/howToPlayMain.png");

        main = new Image(new Texture("elementos/userManual.png"));
        main.setPosition(144, 172);
        stage.addActor(main);

        genInfo = new Image(new Texture("elementos/genInfo.png"));
        genInfo.setPosition(129, 62);

        dyns = new Image(new Texture("elementos/DynsInfo.png"));
        dyns.setPosition(129, 62);

        cards = new Image(new Texture("elementos/cardsInfo.png"));
        cards.setPosition(129, 62);

        yakus = new Image(new Texture("elementos/yakusInfo.png"));
        yakus.setPosition(129, 62);



        buttonInfo = new BaseImageButton("ir", 140, 55, 702, 446);
        buttonInfo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(genInfo);

                main.remove();
                buttonInfo.remove();
                buttonDynamics.remove();
                buttonCards.remove();
                buttonYakus.remove();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(buttonInfo);

        buttonDynamics = new BaseImageButton("ir", 140, 55, 702, 370);
        buttonDynamics.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(dyns);

                main.remove();
                buttonInfo.remove();
                buttonDynamics.remove();
                buttonCards.remove();
                buttonYakus.remove();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(buttonDynamics);

        buttonCards = new BaseImageButton("ir", 140, 55, 702, 287);
        buttonCards.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(cards);

                main.remove();
                buttonInfo.remove();
                buttonDynamics.remove();
                buttonCards.remove();
                buttonYakus.remove();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(buttonCards);

        buttonYakus = new BaseImageButton("ir", 140, 55, 702, 209);
        buttonYakus.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(yakus);

                main.remove();
                buttonInfo.remove();
                buttonDynamics.remove();
                buttonCards.remove();
                buttonYakus.remove();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(buttonYakus);

    }


    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }


}
