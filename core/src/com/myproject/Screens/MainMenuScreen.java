package com.myproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;

public class MainMenuScreen extends BaseScreen {

    private Texture background;
    private BaseImageButton buttonOptions;
    private BaseImageButton buttonInstr;
    private BaseImageButton buttonSP;
    private BaseImageButton buttonBack;
    private BaseImageButton buttonQuit;
    private BaseImageButton buttonPlay;
    private BaseImageButton buttonMP;

    private Image screenName;

    public MainMenuScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        background = new Texture("backgrounds/bg_test.png");
//        screenName = new Image(new Texture());

        buttonPlay = new BaseImageButton("jugar", 277, 70, 108, 350);
        buttonPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonPlay.remove();
                buttonOptions.remove();
                buttonQuit.remove();

                stage.addActor(buttonSP);
                stage.addActor(buttonMP);
                stage.addActor(buttonBack);

                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(buttonPlay);

        buttonOptions = new BaseImageButton("opciones", 277, 70, 108, 214);
        buttonOptions.onClick(()-> setScreen(new SettingsScreen(game)));
        stage.addActor(buttonOptions);

        buttonQuit = new BaseImageButton("salir", 277, 70, 108, (99));
        buttonQuit.onClick(()-> Gdx.app.exit());
        stage.addActor(buttonQuit);

       buttonSP = new BaseImageButton("sp", 277, 70, 108, 302);
       buttonSP.onClick(()-> setScreen(new GameScreen(game)));

        buttonSP = new BaseImageButton("instr", 277, 70, 108, 269);
        buttonSP.onClick(()-> setScreen(new HowToPlayScreen(game)));

       buttonMP = new BaseImageButton("mp", 277, 70, 108, 217);
        buttonMP.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonSP.remove();
                buttonMP.remove();
                buttonBack.remove();
            //TODO borrar cosinga poner cosinga

                return super.touchDown(event, x, y, pointer, button);
            }
        });

       buttonBack = new BaseImageButton("back", 277, 70, 108, 132);
        buttonBack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonSP.remove();
                buttonMP.remove();
                buttonBack.remove();

                stage.addActor(buttonPlay);
                stage.addActor(buttonOptions);
                stage.addActor(buttonQuit);

                return super.touchDown(event, x, y, pointer, button);
            }
        });


    }

    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }


}
