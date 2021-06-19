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
    private static BaseImageButton ribbonBtn;
    private static BaseImageButton taneBtn;
    private static BaseImageButton bdbBtn;
    private static BaseImageButton redBtn;
    private static BaseImageButton blueBtn;
    private static BaseImageButton moonBtn;
    private static BaseImageButton flowerBtn;
    private static BaseImageButton thLightBtn;
    private static BaseImageButton foLightBtn;
    private static BaseImageButton fiLightBtn;

    public static void showyakuOverlay(Stage stage) {
        //instanciamiento botones
        closeOverlayBtn = new BaseImageButton("close", 43, 43, 100, 100);
        closeOverlayBtnGeneral = new BaseImageButton("close", 43, 43, 100, 100);

        basicBtn = new BaseImageButton( "transparent",265, 35, 200, 461);
        ribbonBtn =  new BaseImageButton( "transparent",247, 35, 200, 418);
        taneBtn =  new BaseImageButton( "transparent",295, 35, 200, 376);
        blueBtn =  new BaseImageButton( "transparent",346, 35, 200, 331);
        redBtn =  new BaseImageButton( "transparent",340, 35, 200, 288);
        bdbBtn =  new BaseImageButton( "transparent",355, 35, 200, 240);

        thLightBtn =  new BaseImageButton( "transparent",451, 35, 614, 440);
        foLightBtn =  new BaseImageButton( "transparent",467, 35, 614, 396);
        fiLightBtn =  new BaseImageButton( "transparent",300, 35, 614, 351);
        moonBtn =  new BaseImageButton( "transparent",300, 35, 614, 305);
        flowerBtn =  new BaseImageButton( "transparent",441, 35, 614, 259);


        //pone la imagen con todos los yakus explicados
        yakuPopUp = new Image(new Texture("overlay/yakus_popup.png"));
        yakuPopUp.setPosition(155, 166);

        //boton de cerrar
        closeOverlayBtnGeneral.setPosition(1044, 523);
        closeOverlayBtnGeneral.onClick(() -> {
            yakuPopUp.remove();
            closeOverlayBtnGeneral.remove();
            basicBtn.remove();
            ribbonBtn.remove();
            taneBtn.remove();
            blueBtn.remove();
            redBtn.remove();
            bdbBtn.remove();

            thLightBtn.remove();
            foLightBtn.remove();
            fiLightBtn.remove();
            moonBtn.remove();
            flowerBtn.remove();

        });

        //añade boton e imagen de antes
        stage.addActor(yakuPopUp);
        stage.addActor(closeOverlayBtnGeneral);



        stage.addActor(basicBtn);
        Image img = new Image(new Texture("overlay/basics.png"));
        img.setPosition(((1280f/2)-(579f/2)), (720f/2)-(354f/2));
        basicBtn.onClick(() -> {
            stage.addActor(img);
            createCloseButton(img, 895, 510);
            stage.addActor(closeOverlayBtn);
        });

        stage.addActor(ribbonBtn);
        Image imgrb = new Image(new Texture("overlay/ribbon.png"));
        imgrb.setPosition(((1280f/2)-(376f/2)), (720f/2)-(249f/2));
        ribbonBtn.onClick(() -> {
            stage.addActor(imgrb);
            createCloseButton(imgrb, 790, 460);
            stage.addActor(closeOverlayBtn);
        });

        stage.addActor(taneBtn);
        Image imgtn = new Image(new Texture("overlay/tane.png"));
        imgtn.setPosition(((1280f/2)-(388f/2)), (720f/2)-(249f/2));
        taneBtn.onClick(() -> {
            stage.addActor(imgtn);
            createCloseButton(imgtn, 793, 460);
            stage.addActor(closeOverlayBtn);
        });

        //

        stage.addActor(blueBtn);
        Image imgbl = new Image(new Texture("overlay/aotan.png"));
        imgbl.setPosition(((1280f/2)-(252f/2)), (720f/2)-(142f/2));
        blueBtn.onClick(() -> {
            stage.addActor(imgbl);
            createCloseButton(imgbl, 730, 404);
            stage.addActor(closeOverlayBtn);
        });

        //

        stage.addActor(redBtn);
        Image imgrd = new Image(new Texture("overlay/akatan.png"));
        imgrd.setPosition(((1280f/2)-(252f/2)), (720f/2)-(142f/2));
        redBtn.onClick(() -> {
            stage.addActor(imgrd);
            createCloseButton(imgrd,  730, 404);
            stage.addActor(closeOverlayBtn);
        });

        //

        stage.addActor(bdbBtn);
        Image imgbdb = new Image(new Texture("overlay/bdb.png"));
        imgbdb.setPosition(((1280f/2)-(252f/2)), (720f/2)-(142f/2));
        bdbBtn.onClick(() -> {
            stage.addActor(imgbdb);
            createCloseButton(imgbdb,  730, 404);
            stage.addActor(closeOverlayBtn);
        });

        stage.addActor(thLightBtn);
        Image img3 = new Image(new Texture("overlay/trueLights.png"));
        img3.setPosition(((1280f/2)-(351f/2)), (720f/2)-(142f/2));
        thLightBtn.onClick(() -> {
            stage.addActor(img3);
            createCloseButton(img3,  775, 404);
            stage.addActor(closeOverlayBtn);
        });

        stage.addActor(foLightBtn);
        Image img4 = new Image(new Texture("overlay/trueLights.png"));
        img4.setPosition(((1280f/2)-(351f/2)), (720f/2)-(142f/2));
        foLightBtn.onClick(() -> {
            stage.addActor(img4);
            createCloseButton(img4,  775, 404);
            stage.addActor(closeOverlayBtn);
        });

        stage.addActor(fiLightBtn);
        Image img5 = new Image(new Texture("overlay/fiveLights.png"));
        img5.setPosition(((1280f/2)-(423f/2)), (720f/2)-(142f/2));
        fiLightBtn.onClick(() -> {
            stage.addActor(img5);
            createCloseButton(img5,  809, 404);
            stage.addActor(closeOverlayBtn);
        });

        stage.addActor(moonBtn);
        Image imgmoon = new Image(new Texture("overlay/luna.png"));
        imgmoon.setPosition(((1280f/2)-(186f/2)), (720f/2)-(142f/2));
        moonBtn.onClick(() -> {
            stage.addActor(imgmoon);
            createCloseButton(imgmoon,  703, 404);
            stage.addActor(closeOverlayBtn);
        });

        stage.addActor(flowerBtn);
        Image imgFwr = new Image(new Texture("overlay/flores.png"));
        imgFwr.setPosition(((1280f/2)-(186f/2)), (720f/2)-(142f/2));
        flowerBtn.onClick(() -> {
            stage.addActor(imgFwr);
            createCloseButton(imgFwr,  703, 404);
            stage.addActor(closeOverlayBtn);
        });






    }


    private static void createCloseButton(Image img, int dx, int dy) {
        closeOverlayBtn.setPosition(dx, dy);

        closeOverlayBtn.onClick(() -> {
            img.remove();
            closeOverlayBtn.remove();
        });
    }
}
