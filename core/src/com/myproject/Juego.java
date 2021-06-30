package com.myproject;


import com.myproject.Config.MyStage;
import com.myproject.Object.Carta;
import com.myproject.Object.Mazo;
import com.myproject.Object.Yaku;
import com.myproject.Screens.GameScreen;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class Juego {

    private final MyGame game;
    MyStage stage;
    GameScreen gs;

    public ArrayList<Carta> cartasMano;
    public ArrayList<Carta> cartasManoAI;
    public ArrayList<Carta> cartasEnJuego;
    public ArrayList<Carta> yakus;
    public ArrayList<Carta> yakusAI;

    public ArrayList<Yaku> pCombos, AIcombos;

    public Carta selectedCard, playingCard;
    public Mazo mazo;

    public boolean turn = true, gameOver = false;
    public int rondas = 1, score = 50, scoreAI = 50, mult = 1, yakuSizeP = 0, yakuSizeAI = 0;



    public Juego(MyGame game, MyStage stage, GameScreen gs) {
        this.stage = stage;
        this.game = game;
        this.gs = gs;
    }

    public void doGame () {
        initialGameState();

    }

    public ArrayList<Carta> addToMano(Mazo mazo) {
        ArrayList<Carta> manita = new ArrayList<>();
        for (int i = 75; i < 1100; i += 122 + 22) {
            Carta c = mazo.getRandomCard();
            manita.add(c);
            c.setWidth(122);
            c.setHeight(192);
            c.setPosition(i, 27);
        }
        return manita;
    }

    public void initialGameState() {
        mazo = new Mazo();
        cartasMano = addToMano(mazo);
        cartasManoAI = addToMano(mazo);
        cartasEnJuego = new ArrayList<>();
        yakus = new ArrayList<>();
        yakusAI = new ArrayList<>();

        System.out.println("INITIAL GAME STATE");

        mult = 1;
        yakuSizeP = 0;
        yakuSizeAI = 0;

        for (int i = 0; i < 8; i++) {
            Carta c = mazo.getRandomCard();
            cartasEnJuego.add(c);
        }
    }

    public void AIplay() {
        Carta cartaMano = null;
        Carta cartaMesa = null;
        for (Carta cMano : cartasManoAI) {
            for (Carta cMesa : cartasEnJuego) {
                if (cMesa.getMes() == cMano.getMes()) {

                    cartaMesa = cMesa;
                    cartaMano = cMano;
                    break;
                }
            }
        }
        if (cartaMano == null) {
            //poner una random fuera
            cartasManoAI.get(0).setHeight(158);
            cartasManoAI.get(0).setWidth(100);
            cartasEnJuego.add(cartasManoAI.get(0));
            cartasManoAI.remove(0);

        }
        if (cartaMano != null) {
            yakusAI.add(cartaMesa);
            yakusAI.add(cartaMano);
            cartaMesa.remove();
            cartasEnJuego.remove(cartaMesa);
            cartasManoAI.remove(cartaMano);
        }

        acabarTurno(false);
        if (cartaMano != null)
            System.out.println("AI jugado ya " + cartaMano.getImage() + " en " + cartaMesa.getImage());
        System.out.println("AI tiene estos yakus: " + yakusAI.size());
    }

      public void acabarTurno(boolean turno) {

        Carta nueva = mazo.getRandomCard();
        System.out.println(nueva.image);
        cartasEnJuego.add(nueva);


        Carta trobadaCard = null;

        for (Carta card : cartasEnJuego) {
            if (card.getMes() == nueva.getMes() && !card.image.equals(nueva.image)) {

                if (turno) {
                    yakus.add(card);
                    yakus.add(nueva);
                } else {
                    yakusAI.add(card);
                    yakusAI.add(nueva);
                }

                trobadaCard = card;

                card.remove();
                nueva.remove();
                break;
            }
        }

        if (trobadaCard != null) {
            cartasEnJuego.remove(nueva);
            cartasEnJuego.remove(trobadaCard);
        }


        gs.showYakus();
        gs.showYakusAI();

        gs.mostrarCartasEnJuego();
        gs.selected = false;


        AIcombos = GameLogic.checkyakus(yakusAI);
    }



}
