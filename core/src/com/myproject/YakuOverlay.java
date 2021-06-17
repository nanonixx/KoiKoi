package com.myproject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.MyActor;

public class YakuOverlay extends MyActor {

    private Image yakuPopUp;

    BaseImageButton basic = new BaseImageButton();

    public void showyakuOverlay() {
        yakuPopUp = new Image(new Texture("overlay/yakus_popup.png"));
        yakuPopUp.setPosition(155, 166);
        yakuPopUp.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                yakuPopUp.remove();

                return super.touchDown(event, x, y, pointer, button);
            }
        });



    }
}
