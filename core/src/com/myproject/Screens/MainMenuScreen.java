package com.myproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.MyGame;

public class MainMenuScreen extends BaseScreen {

    private Texture background;
    private BaseImageButton buttonOptions;
    private BaseImageButton buttonSP;
    private BaseImageButton buttonBack;
    private BaseImageButton buttonQuit;
    private BaseImageButton buttonPlay;
    private BaseImageButton buttonMP;

    public MainMenuScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        background = new Texture("backgrounds/bg_test.png");

        buttonPlay = new BaseImageButton("buttons/playButton.png", "buttons/playButtonPressed.png", 277, 70, 108, 300);
        buttonPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonPlay.remove();
                buttonOptions.remove();
                buttonQuit.remove();

                stage.addActor(buttonSP);
                stage.addActor(buttonMP);
                stage.addActor(buttonBack);

//                setScreen(new GameScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(buttonPlay);

        buttonOptions = new BaseImageButton("buttons/optionsButton.png", "buttons/playButtonPressed.png", 277, 70, 108, 214);
        buttonOptions.onClick(()-> setScreen(new SettingsScreen(game)));
        stage.addActor(buttonOptions);

        buttonQuit = new BaseImageButton("buttons/quitButton.png", "buttons/playButtonPressed.png", 277, 70, 108, 128);
        buttonQuit.onClick(()-> Gdx.app.exit());
        stage.addActor(buttonQuit);

       buttonSP = new BaseImageButton("buttons/singleplayerButton.png", "buttons/playButtonPressed.png", 277, 70, 108, 300);
       buttonSP.onClick(()-> setScreen(new GameScreen(game)));

       buttonMP = new BaseImageButton("buttons/multiplayerButton.png", "buttons/playButtonPressed.png", 277, 70, 108, 214);

       buttonBack = new BaseImageButton("buttons/backButton.png", "buttons/playButtonPressed.png", 277, 70, 108, 128);
        buttonBack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonSP.remove();
                buttonMP.remove();
                buttonBack.remove();

                stage.addActor(buttonPlay);
                stage.addActor(buttonOptions);
                stage.addActor(buttonQuit);

//                setScreen(new GameScreen(game));
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
