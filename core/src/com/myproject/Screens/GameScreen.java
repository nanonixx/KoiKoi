package com.myproject.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.Config.MyActor;
import com.myproject.Config.MyLabel;
import com.myproject.GameLogic;
import com.myproject.Juego;
import com.myproject.MyGame;
import com.myproject.Object.Carta;
import com.myproject.Object.Mazo;
import com.myproject.Object.Yaku;
import com.myproject.YakuOverlay;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends BaseScreen {

    private Texture background;
    private BaseImageButton buttonBack;
    private BaseImageButton buttonInfo;

    private BaseImageButton mePlantoBtn;
    private BaseImageButton koiKoiBtn;

    private Image addCards;

    private MyLabel rondaLabel;
    private MyLabel multLabel;
    private MyLabel scoreP1Label;
    private MyLabel scoreAILabel;
    private MyLabel comboScore;

    private MyLabel scorep1ask;

    private Image yakuPopUp;


    Juego juego = new Juego(game, stage, this);

    public boolean selected = false; //activado si la carta ha sido seleccionada

    public GameScreen(MyGame game) {
        super(game);


        juego.initialGameState();


    }

    @Override
    public void show() {
        background = new Texture("backgrounds/GameScreen.png");

        rondaLabel = new MyLabel(String.valueOf(juego.rondas), Color.valueOf("#541651"), 535, 673);
        stage.addActor(rondaLabel);
        multLabel = new MyLabel("x" + String.valueOf(juego.mult), Color.valueOf("541651"), 791, 677);
        stage.addActor(multLabel);
        scoreP1Label = new MyLabel(String.valueOf(juego.score), Color.WHITE, 177, 605, 1.5f);
        stage.addActor(scoreP1Label);
        scoreAILabel = new MyLabel(String.valueOf(juego.score), Color.WHITE, 1136, 605, 1.5f);
        stage.addActor(scoreAILabel);

        buttonInfo = new BaseImageButton("info", 54, 54, 1227, 666);
        buttonInfo.onClick(() -> YakuOverlay.showyakuOverlay(stage));
        stage.addActor(buttonInfo);

        mePlantoBtn = new BaseImageButton("done", 209, 98, 412, 243);
        koiKoiBtn = new BaseImageButton("koikoi", 209, 98, 666, 243);

        buttonBack = new BaseImageButton("backMini", 54, 54, 0, 666);
        buttonBack.onClick(() -> setScreen(new MainMenuScreen(game)));
        stage.addActor(buttonBack);

        yakuPopUp = new Image(new Texture("overlay/popupYaku.png"));
        yakuPopUp.setPosition(((1280f / 2) - (557f / 2)), (720f / 2) - (354f / 2));


        addCards = new Image(new Texture("elementos/addCard.png"));
        addCards.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                juego.selectedCard.setHeight(158);
                juego.selectedCard.setWidth(100);

                juego.cartasEnJuego.add(juego.selectedCard);
                juego.cartasMano.remove(juego.selectedCard);
               juego.acabarTurno(true);

                return super.touchDown(event, x, y, pointer, button);
            }
        });
        desactivarListeners();

        mostrarMano();
        mostrarCartasEnJuego();

    }

    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
//        super.dispose();
    }



    public void mostrarMano() {
        for (Carta c : juego.cartasMano)
            stage.addActor(c);
    }

    private void activarListeners() {
        juego.cartasMano.forEach(carta -> {
            carta.setListener(() -> {
                touched(carta);
            });
        });
        juego.cartasEnJuego.forEach(carta -> {
            carta.setListener(() -> {
                touched(carta);
            });
        });
    }

    private void desactivarListeners() {
        activarListeners();
//        Cosingas.juego.mano.cartaList.forEach(MyActor::removeListener);
//        endTurn.removeListener();
        if (!selected) {
            juego.cartasEnJuego.forEach(MyActor::removeListener);
        }
    }

    public void touched(Carta carta) {

        for (Carta c : juego.cartasMano) {
            if (c == carta) {
                juego.selectedCard = carta;
                carta.setY(47);
                selected = true;
            } else {
                c.setY(27);
            }
        }
        onPlayCardAction(carta);

//        System.out.println("Juego " + selectedCard.image + " a esta carta: " + playingCard.image);
        desactivarListeners();
    }

    private void onPlayCardAction(Carta carta) {
        for (Carta c : juego.cartasEnJuego.toArray(new Carta[0])) {
            if (selected) {
                if (c == carta) {
                    juego.playingCard = carta;

                    if (juego.selectedCard.getMes() == (juego.playingCard.getMes())) {

                        juego.selectedCard.remove();
                        carta.remove();

                        juego.yakus.add(juego.selectedCard);
                        juego.yakus.add(juego.playingCard);

                        juego.cartasEnJuego.remove(juego.playingCard);
                        juego.cartasMano.remove(juego.selectedCard);
                        juego.selectedCard = null;
                        juego.acabarTurno(true);

                        juego.pCombos = GameLogic.checkyakus(juego.yakus);
                        showIfYaku(juego.pCombos, true);

                        showIfYaku(juego.AIcombos, false);
                    }
                }
            }
        }
    }





    public void mostrarCartasEnJuego() {
        int dx = 302, dy = 0;
        Carta c = null;
        addCards.remove();

        for (int i = 0; i < juego.cartasEnJuego.size(); i += 2) {

            //coloca las cartas en juego en su sitio, da igual cuantas haya
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    try {
                        c = juego.cartasEnJuego.get(i);
                        dy = 480;
                    } catch (Exception e) {
                    }
                } else {
                    try {
                        c = juego.cartasEnJuego.get(i + 1);
                        dy = 301;
                    } catch (Exception e) {
                    }
                }

                if (c != null) {
                    c.setPosition(dx, dy);
                    stage.addActor(c);
                } else {

                }

            }
            dx += 100 + 15;
        }

        addCards.setPosition(dx, 480);
        stage.addActor(addCards);

    }

    public void showYakus() {
        Carta chiquita;

        int dyB = 489;
        int dyT = 489;
        int dyR = 489;
        int dyL = 489;

        for (Carta c : juego.yakus) {
            chiquita = c;
            chiquita.setHeight(66);
            chiquita.setWidth(40);

            switch (c.getTipo()) {
                case "BASE":

                    chiquita.setPosition(64, dyB);
                    dyB -= 21;
                    stage.addActor(chiquita);
                    break;

                case "TANE":
                    chiquita.setPosition(112, dyT);
                    dyT -= 21;
                    stage.addActor(chiquita);
                    break;

                case "RIBBON":
                    chiquita.setPosition(160, dyR);
                    dyR -= 21;
                    stage.addActor(chiquita);
                    break;

                case "LIGHT":
                    chiquita.setPosition(208, dyL);
                    dyL -= 21;
                    stage.addActor(chiquita);
                    break;
            }

        }
    }

    public void showYakusAI() {
        Carta chiquita;

        int dyB = 489;
        int dyT = 489;
        int dyR = 489;
        int dyL = 489;

        for (Carta c : juego.yakusAI) {
            chiquita = c;
            chiquita.setHeight(66);
            chiquita.setWidth(40);

            switch (c.getTipo()) {
                case "BASE":

                    chiquita.setPosition(1027, dyB);
                    dyB -= 21;
                    stage.addActor(chiquita);
                    break;

                case "TANE":
                    chiquita.setPosition(1075, dyT);
                    dyT -= 21;
                    stage.addActor(chiquita);
                    break;

                case "RIBBON":
                    chiquita.setPosition(1123, dyR);
                    dyR -= 21;
                    stage.addActor(chiquita);
                    break;

                case "LIGHT":
                    chiquita.setPosition(1171, dyL);
                    dyL -= 21;
                    stage.addActor(chiquita);
                    break;
            }

        }
    }

    private void showIfYaku(ArrayList<Yaku> combos, boolean player) {
        float dy = 240f;

        if (player) {
            float dx = 282f;

            for (Yaku yk : combos) {
                Image combo = new Image(new Texture("elementos/" + yk.getName() + "Frame.png"));
                combo.setPosition(dx, dy);
                stage.addActor(combo);
                dx = dx + 100 + 12;
            }

            if (juego.yakuSizeP != combos.size() && player) {
                askKoiKoi();
                juego.yakuSizeP = combos.size();
            } else juego.AIplay();
        } else {
            float dx = 1000f;
            for (Yaku yk : combos) {
                Image combo = new Image(new Texture("elementos/" + yk.getName() + "Frame.png"));
                dx -= 10;
                combo.setPosition(dx, dy);
                stage.addActor(combo);
                dx = dx - 100 - 12;
            }

            if (juego.yakuSizeAI != combos.size()) {
                Random rnd = new Random();
                boolean rndBool = rnd.nextBoolean();
                if (rndBool) System.out.println("AI uses KoiKoi!");
                else if (!rndBool) System.out.println("AI se planta!");

                juego.yakuSizeAI = combos.size();
            }


        }

    }

    public void askKoiKoi() {
        stage.addActor(yakuPopUp);
        int scoreYaku = 0;
        Image combo = null;

        float dx = 570f;
        float dy = 414f;

        for (Yaku yk : juego.pCombos) {
            combo = new Image(new Texture("elementos/" + yk.getName() + "Frame.png"));
            combo.setPosition(dx, dy);
            stage.addActor(combo);
            dx = dx + 100 + 12;
            scoreYaku += yk.getPoints();
        }
        System.out.println(scoreYaku);
        comboScore = new MyLabel(String.valueOf(scoreYaku), Color.WHITE, 570f, 375, 1.5f);
        stage.addActor(comboScore);

        Image finalCombo = combo;
        int finalScoreYaku = scoreYaku;
        mePlantoBtn.onClick(() -> {
            juego.rondas++;
            yakuPopUp.remove();
            finalCombo.remove();
            mePlantoBtn.remove();
            comboScore.remove();
            koiKoiBtn.remove();
            juego.score += finalScoreYaku * juego.mult;
            juego.initialGameState();

        });
        stage.addActor(mePlantoBtn);

        koiKoiBtn.onClick(() -> {
            juego.mult = 2;
            yakuPopUp.remove();
            finalCombo.remove();
            comboScore.remove();
            mePlantoBtn.remove();
            koiKoiBtn.remove();
        });
        stage.addActor(koiKoiBtn);
        scorep1ask = new MyLabel(String.valueOf(juego.score), Color.WHITE, 495, 362);

    }


}
