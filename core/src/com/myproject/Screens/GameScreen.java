package com.myproject.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.Config.MyActor;
import com.myproject.MyGame;
import com.myproject.Object.Carta;
import com.myproject.Object.Mazo;

import java.util.ArrayList;

public class GameScreen extends BaseScreen {

    private Texture background;
    private BaseImageButton buttonBack;
    public Mazo mazo = new Mazo();

    public ArrayList<Carta> cartasMano;
    public ArrayList<Carta> cartasEnJuego;
    Carta selectedCard;
    Carta playingCard;

    boolean selected = false; //activado si la carta ha sido seleccionada

    public GameScreen(MyGame game) {
        super(game);

        initialGameState();
        addToMano();

    }

    @Override
    public void show() {
        background = new Texture("backgrounds/GameScreen.png");

        buttonBack = new BaseImageButton("buttons/back.png", "buttons/back.png", 54, 54, 0, 666);
        buttonBack.onClick(()-> setScreen(new MainMenuScreen(game)));
        stage.addActor(buttonBack);


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

    public void addToMano(){
        cartasMano = new ArrayList<>();

        for (int i = 75; i < 1100; i+=122+22) {
            Carta c = mazo.getRandomCard();
            cartasMano.add(c);
            c.setWidth(122);
            c.setHeight(192);
            c.setPosition(i, 47);
        }
    }

    public void mostrarMano(){
        for (Carta c : cartasMano)
            stage.addActor(c);
    }

    private void activarListeners() {
            cartasMano.forEach(carta -> {
                carta.setListener(() -> {
                    touched(carta);
                });
            });
            cartasEnJuego.forEach(carta -> {
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
            cartasEnJuego.forEach(MyActor::removeListener);
        }
    }

        public void touched(Carta carta) {

            for (Carta c: cartasMano) {
                if (c == carta) {
                    selectedCard = carta;
                    selected = true;
                    break;
                }
            }
            for (Carta c: cartasEnJuego.toArray(new Carta[0])) {
                if (selected) {
                    if (c == carta) {
                        playingCard = carta;

                        if (selectedCard.getMes()==(playingCard.getMes())) {

                            selectedCard.remove();
                            carta.remove();

                            cartasMano.remove(selectedCard);
                            cartasEnJuego.remove(playingCard);

                            mostrarCartasEnJuego();

                            selected = false;
                        }
                    }
                }
            }

//        System.out.println("Juego " + selectedCard.image + " a esta carta: " + playingCard.image);
            desactivarListeners();
        }



    public void mostrarCartasEnJuego() {
        int dx = 319, dy;
        Carta c;
        for (int i = 0; i < cartasEnJuego.size(); i+=2) {

            //coloca las cartas en juego en su sitio, da igual cuantas haya
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    c = cartasEnJuego.get(i);
                    dy = 472;
                } else {
                    try {
                        c = cartasEnJuego.get(i + 1);
                        dy = 293;
                    }catch(Exception e){
                        break;
                    }
                }

                if (c != null) {
                    c.setPosition(dx, dy);
                    stage.addActor(c);
                }

            }
            dx += 106 + 12;
        }

    }

    public void initialGameState() {
        cartasEnJuego = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Carta c = mazo.getRandomCard();
            cartasEnJuego.add(c);
        }
    }


}
