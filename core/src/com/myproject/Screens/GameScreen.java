package com.myproject.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.myproject.Config.BaseImageButton;
import com.myproject.Config.BaseScreen;
import com.myproject.Config.MyActor;
import com.myproject.GameLogic;
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
    public ArrayList<Carta> yakus;
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
            c.setPosition(i, 27);
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
            onPlayCardAction(carta);

//        System.out.println("Juego " + selectedCard.image + " a esta carta: " + playingCard.image);
            desactivarListeners();
        }

    private void onPlayCardAction(Carta carta) {
        for (Carta c: cartasEnJuego.toArray(new Carta[0])) {
            if (selected) {
                if (c == carta) {
                    playingCard = carta;

                    if (selectedCard.getMes()==(playingCard.getMes())) {

                        selectedCard.remove();
                        carta.remove();

                        yakus.add(selectedCard);
                        yakus.add(playingCard);

                        cartasEnJuego.remove(playingCard);
                    }

                    else {
                        selectedCard.setHeight(c.HEIGHT);
                        selectedCard.setWidth(c.WIDTH);

                        cartasEnJuego.add(selectedCard);
                    }

                    cartasMano.remove(selectedCard);
                    Carta nueva = mazo.getRandomCard();
                    System.out.println(nueva.image);
                    cartasEnJuego.add(nueva);
                    System.out.println(GameLogic.checkyakus(yakus));

                    Carta trobadaCard = null;

                    for (Carta card : cartasEnJuego) {
                        if (card.getMes() == nueva.getMes() && !card.image.equals(nueva.image)){
                            yakus.add(card);
                            yakus.add(nueva);
//
                            System.out.println("coincide con:" + card.image);
                            trobadaCard = card;

                            card.remove();
                            nueva.remove();
                            break;
                        }
                    }

                    if (trobadaCard!=null) {
                            cartasEnJuego.remove(nueva);
                            cartasEnJuego.remove(trobadaCard);
                    }

                    mostrarCartasEnJuego();
                    showYakus();

                    selected = false;
                }
            }
        }
    }


    public void mostrarCartasEnJuego() {
        int dx = 302, dy;
        Carta c;
        for (int i = 0; i < cartasEnJuego.size(); i+=2) {

            //coloca las cartas en juego en su sitio, da igual cuantas haya
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    c = cartasEnJuego.get(i);
                    dy = 480;
                } else {
                    try {
                        c = cartasEnJuego.get(i + 1);
                        dy =301;
                    }catch(Exception e){
                        break;
                    }
                }

                if (c != null) {
                    c.setPosition(dx, dy);
                    stage.addActor(c);
                }

            }
            dx += 100 + 15;
        }

    }

    public void initialGameState() {
        GameLogic.combo = null;
        cartasEnJuego = new ArrayList<>();
        yakus = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Carta c = mazo.getRandomCard();
            cartasEnJuego.add(c);
        }
    }

    public void showYakus() {
        Carta chiquita;

        int dyB = 489;
        int dyT = 489;
        int dyR = 489;
        int dyL = 489;

        for (Carta c : yakus) {
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
