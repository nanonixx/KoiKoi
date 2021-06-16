package com.myproject;


import com.myproject.Object.Carta;
import com.myproject.Object.Mazo;

import java.util.ArrayList;

public class Juego {

    private final MyGame game;
    int ronda;
    int mult;
    int scoreP1;
    int scoreP2;

    public ArrayList<Carta> cartasMano;
    public ArrayList<Carta> cartasEnJuego;
    public ArrayList<Carta> yakus;

    public Mazo mazo = new Mazo();
    public Carta selectedCard;
    public Carta playingCard;


    public boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public Juego(MyGame game) {
        this.game = game;
    }

    public void gameCourse() {

    initialGameState();





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

    public void playCard(Carta carta, Carta selectedCar) {
        for (Carta c: cartasMano) {
            if (c == carta) {
                selectedCard = carta;
                selected = true;
                break;
            }
        }


    }

    public void onPlayCardAction(Carta carta) {
        for (Carta c: cartasEnJuego.toArray(new Carta[0])) {
            if (isSelected()) {
                if (c == carta) {
                    playingCard = carta;

                    if (selectedCard.getMes()==(playingCard.getMes())) {

                        selectedCard.remove();
                        carta.remove();

                        yakus.add(selectedCard);
                        yakus.add(playingCard);

                        cartasEnJuego.remove(playingCard);
                        acabarTurno();
                    }
                }
            }
        }
    }

    public void acabarTurno() {
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


       selected = false;
    }


}
