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

    private static BaseImageButton closeOverlayBtn;
    private static BaseImageButton closeOverlayBtnGeneral;
    private static BaseImageButton basicBtn;

    public static void showyakuOverlay(Stage stage) {
        closeOverlayBtn = new BaseImageButton("close", 43, 43, 100, 100);
        closeOverlayBtnGeneral = new BaseImageButton("close", 43, 43, 100, 100);
        basicBtn = new BaseImageButton( "transparent",265, 35, 200, 461);

        yakuPopUp = new Image(new Texture("overlay/yakus_popup.png"));
        yakuPopUp.setPosition(155, 166);

        closeOverlayBtnGeneral.setPosition(1044, 523);

        closeOverlayBtnGeneral.onClick(() -> {
            yakuPopUp.remove();
            closeOverlayBtnGeneral.remove();
            basicBtn.remove();
        });

        stage.addActor(yakuPopUp);
        stage.addActor(closeOverlayBtnGeneral);

        showOverlay(stage, "basics");


    }

    private static void showOverlay(Stage stage, String overlayName) {
        stage.addActor(basicBtn);
        Image basicsImg = new Image(new Texture("overlay/"+overlayName+".png"));
        basicsImg.setPosition(((1280f/2)-(579f/2)), (720f/2)-(354f/2));

        basicBtn.onClick(() -> {
            stage.addActor(basicsImg);
            createCloseButton(basicsImg, 895,510);
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

    private static void createCloseButton(Image img, int dx, int dy) {
        closeOverlayBtn.setPosition(dx, dy);

        closeOverlayBtn.onClick(() -> {
            img.remove();
            closeOverlayBtn.remove();
        });
    }
}
