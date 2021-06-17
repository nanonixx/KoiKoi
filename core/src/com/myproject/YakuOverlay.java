package com.myproject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.MyActor;

public class YakuOverlay extends MyActor {

    private static Image yakuPopUp;

    private static BaseImageButton closeOverlayBtn = new BaseImageButton("close", 43, 43, 100, 100);
    private static BaseImageButton basicBtn = new BaseImageButton( "done",265, 35, 200, 455);

    public static void showyakuOverlay(Stage stage) {
        yakuPopUp = new Image(new Texture("overlay/yakus_popup.png"));
        yakuPopUp.setPosition(155, 166);
        stage.addActor(yakuPopUp);




        showOverlay(stage, "basics");


    }

    private static void showOverlay(Stage stage, String overlayName) {
        stage.addActor(basicBtn);
        Image basicsImg = new Image(new Texture("overlay/"+overlayName+".png"));
        basicsImg.setPosition(((1280f/2)-(579f/2)), (720f/2)-(354f/2));

        basicBtn.onClick(() -> {
            stage.addActor(basicsImg);
            createCloseButton(basicsImg);
            stage.addActor(closeOverlayBtn);
        });

//        basicBtn.addListener(new InputListener() {
//            public void enter (InputEvent event, float x, float y, int pointer, int button) {
//                stage.addActor(basicsImg);
//            }
//
//            public void exit (InputEvent event, float x, float y, int pointer, int button) {
//                basicsImg.remove();
//            }
//        });

//        basicBtn.addListener(new InputListener() {
//            public boolean exit(InputEvent event, float x, float y, int pointer, int button) {
//            basicsImg.remove();
//                return false;
//            }
//        });

    }

    private static void createCloseButton(Image basicsImg) {
        closeOverlayBtn.setPosition(895,510);

        closeOverlayBtn.onClick(() -> {
            basicsImg.remove();
            closeOverlayBtn.remove();
        });
    }
}
