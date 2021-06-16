package com.myproject.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.Config.MyActor;
import com.myproject.GameLogic;
import com.myproject.Juego;
import com.myproject.MyGame;
import com.myproject.Object.Carta;
import com.myproject.Object.Mazo;

import java.util.ArrayList;

public class GameScreen extends BaseScreen {

    Juego juego = new Juego(game);
    private Texture background;
    private BaseImageButton buttonBack;
    private Image addCards;


    public ArrayList<Carta> yakus;
    boolean turn = true;
    boolean gameOver = false;
    

    



    //activado si la carta ha sido seleccionada

    public GameScreen(MyGame game) {
        super(game);

        addToMano();
        while (!gameOver) {
            if (turn) { //turno player

               juego.initialGameState();
                juego.acabarTurno();
            } else { //turno CPU

            }
            showYakus();
            mostrarMano();

            turn = !turn;
        }


    }

    @Override
    public void show() {
        background = new Texture("backgrounds/GameScreen.png");

        buttonBack = new BaseImageButton("buttons/back.png", "buttons/back.png", 54, 54, 0, 666);
        buttonBack.onClick(()-> setScreen(new MainMenuScreen(game)));
        stage.addActor(buttonBack);

        addCards = new Image(new Texture("elementos/addCard.png"));
        addCards.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                juego.selectedCard.setHeight(158);
                juego.selectedCard.setWidth(100);

                juego.cartasEnJuego.add(juego.selectedCard);
                juego.acabarTurno();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
        desactivarListeners();

        mostrarMano();
        mostrarcartasEnJuego();

    }

    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    public void addToMano(){
        juego.cartasMano = new ArrayList<>();

        for (int i = 75; i < 1100; i+=122+22) {
            Carta c = juego.mazo.getRandomCard();
            juego.cartasMano.add(c);
            c.setWidth(122);
            c.setHeight(192);
            c.setPosition(i, 27);
        }
    }

    public void mostrarMano(){
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
        if (!juego.isSelected()) {
            juego.cartasEnJuego.forEach(MyActor::removeListener);
        }
    }

        public void touched(Carta carta) {

            juego.playCard(carta, juego.selectedCard);
            desactivarListeners();

        }








    public void mostrarcartasEnJuego() {
        int dx = 302, dy = 0;
        Carta c = null;
        addCards.remove();

        for (int i = 0; i < juego.cartasEnJuego.size(); i+=2) {

            //coloca las cartas en juego en su sitio, da igual cuantas haya
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    try {
                        c = juego.cartasEnJuego.get(i);
                        dy = 480;
                    } catch (Exception e) {}
                } else {
                    try {
                        c = juego.cartasEnJuego.get(i + 1);
                        dy = 301;
                    }catch(Exception e){
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
        System.out.println("Añado addcards este ya");

    }



    public void showYakus() {
        Carta chiquita;

        int dyB = 489;
        int dyT = 489;
        int dyR = 489;
        int dyL = 489;

        for (Carta c : juego.yakus) {
            chiquita =c;
            chiquita.setHeight(66);
            chiquita.setWidth(40);

            switch (c.getTipo()){
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


}
