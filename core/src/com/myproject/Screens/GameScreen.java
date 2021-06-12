package com.myproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.myproject.Assets;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
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

    public GameScreen(MyGame game) {
        super(game);

        initialGameState();
        addToMano();
        activarListeners();
    }

    @Override
    public void show() {
        background = new Texture("backgrounds/GameScreen.png");

        buttonBack = new BaseImageButton("buttons/back.png", "buttons/back.png", 54, 54, 0, 666);
        buttonBack.onClick(()-> setScreen(new MainMenuScreen(game)));
        stage.addActor(buttonBack);

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
    }


        public void touched(Carta carta) {
            selectedCard = carta;
            carta.remove();
            cartasMano.remove(carta);

                cartasEnJuego.forEach(c -> {
                    c.setListener(() -> {
                       playingCard = carta;
                       c.remove();
                       cartasEnJuego.remove(carta);
                    });
                });
        }

    public void mostrarCartasEnJuego() {
        int cont = 0;
        int dx = 319, dy;
        Carta c;
        for (int i = 0; i < cartasEnJuego.size(); i+=2) {


            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    c = cartasEnJuego.get(i);
                    dy = 472;
                } else {
                    c = cartasEnJuego.get(i+1);
                    dy = 293;
                }

                if (c != null) {
                    c.setPosition(dx, dy);
                    stage.addActor(c);
                }

            }
            dx += 106 + 12;


//            switch (cont){
//                case 0: c.setPosition(319, 472);
//                    break;
//                case 1: c.setPosition(319, 293);
//                    break;
//                case 2: c.setPosition(437, 472);
//                    break;
//                case 3: c.setPosition(437, 293);
//                    break;
//                case 4: c.setPosition(555, 472);
//                    break;
//                case 5: c.setPosition(555, 293);
//                    break;
//                case 6: c.setPosition(673, 472);
//                    break;
//                case 7: c.setPosition(673, 293);
//                    break;
//                case 8: c.setPosition(791, 472);
//                    break;
//                case 9: c.setPosition(791, 293);
//                    break;
//            }

//            cont++;
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
