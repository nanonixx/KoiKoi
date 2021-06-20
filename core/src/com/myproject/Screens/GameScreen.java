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
    public Mazo mazo = new Mazo();

    private MyLabel rondaLabel;
    private MyLabel multLabel;
    private MyLabel scoreP1Label;
    private MyLabel scoreAILabel;

    private MyLabel scorep1ask;

    private Image yakuPopUp;

    public ArrayList<Carta> cartasMano;
    public ArrayList<Carta> cartasManoAI;
    public ArrayList<Carta> cartasEnJuego;
    public ArrayList<Carta> yakus;
    public ArrayList<Carta> yakusAI;

    ArrayList<Yaku> pCombos;
    ArrayList<Yaku> AIcombos;

    Carta selectedCard;
    Carta playingCard;

    boolean turn = true, gameOver = false;
    int rondas = 1, score = 50, scoreAI = 50, mult = 1, yakuSizeP = 0, yakuSizeAI = 0;


    boolean selected = false; //activado si la carta ha sido seleccionada

    public GameScreen(MyGame game) {
        super(game);

        cartasMano = addToMano();
        cartasManoAI = addToMano();
            initialGameState();



    }

    @Override
    public void show() {
        background = new Texture("backgrounds/GameScreen.png");

        rondaLabel = new MyLabel(String.valueOf(rondas), Color.valueOf("#541651"), 535, 673);
        stage.addActor(rondaLabel);
        multLabel = new MyLabel("x"+String.valueOf(mult), Color.valueOf("541651"), 791, 677);
        stage.addActor(multLabel);
        scoreP1Label = new MyLabel(String.valueOf(score), Color.WHITE, 177, 605, 1.5f);
        stage.addActor(scoreP1Label);
        scoreAILabel = new MyLabel(String.valueOf(score), Color.WHITE, 1136, 605, 1.5f);
        stage.addActor(scoreAILabel);
        buttonInfo = new BaseImageButton("info", 54, 54, 1227, 666);
        buttonInfo.onClick(()-> YakuOverlay.showyakuOverlay(stage));
        stage.addActor(buttonInfo);

        mePlantoBtn = new BaseImageButton("done", 201, 98,412, 251);
        koiKoiBtn = new BaseImageButton("koikoi", 201, 98, 666, 251);

        buttonBack = new BaseImageButton("backMini", 54, 54, 0, 666);
        buttonBack.onClick(()-> setScreen(new MainMenuScreen(game)));
        stage.addActor(buttonBack);

        yakuPopUp = new Image(new Texture("overlay/popupYaku.png"));
        yakuPopUp.setPosition(((1280f/2)-(557f/2)), (720f/2)-(354f/2));




        addCards = new Image(new Texture("elementos/addCard.png"));
        addCards.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                selectedCard.setHeight(158);
                selectedCard.setWidth(100);

                cartasEnJuego.add(selectedCard);
                cartasMano.remove(selectedCard);
                acabarTurno(true);

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

    public ArrayList<Carta> addToMano(){
        ArrayList<Carta> manita = new ArrayList<>();

        for (int i = 75; i < 1100; i+=122+22) {
            Carta c = mazo.getRandomCard();
            manita.add(c);
            c.setWidth(122);
            c.setHeight(192);
            c.setPosition(i, 27);
        }

        return manita;
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
                    carta.setY(47);
                    selected = true;
                }
                else{
                    c.setY(27);
                }
            }
            onPlayCardAction(carta);

//        System.out.println("Juego " + selectedCard.image + " a esta carta: " + playingCard.image);
            desactivarListeners();
            System.out.println(rondas + " " + mult);
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
                        cartasMano.remove(selectedCard);
                        selectedCard=null;
                        acabarTurno(true);

                        pCombos= GameLogic.checkyakus(yakus);
                        showIfYaku(pCombos, true);

                        showIfYaku(AIcombos, false);
                    }
                }
            }
        }
    }



    private void acabarTurno(boolean turno) {

        Carta nueva = mazo.getRandomCard();
        System.out.println(nueva.image);
        cartasEnJuego.add(nueva);


        Carta trobadaCard = null;

        for (Carta card : cartasEnJuego) {
            if (card.getMes() == nueva.getMes() && !card.image.equals(nueva.image)){

               if (turno) {
                   yakus.add(card);
                   yakus.add(nueva);}
               else {
                   yakusAI.add(card);
                   yakusAI.add(nueva);
               }

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


        showYakus();
        showYakusAI();

        mostrarCartasEnJuego();
        selected = false;


        AIcombos= GameLogic.checkyakus(yakusAI);


    }


    public void mostrarCartasEnJuego() {
        int dx = 302, dy = 0;
        Carta c = null;
        addCards.remove();

        for (int i = 0; i < cartasEnJuego.size(); i+=2) {

            //coloca las cartas en juego en su sitio, da igual cuantas haya
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    try {
                        c = cartasEnJuego.get(i);
                        dy = 480;
                    } catch (Exception e) {}
                } else {
                    try {
                        c = cartasEnJuego.get(i + 1);
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

    }

    public void initialGameState() {
        cartasEnJuego = new ArrayList<>();
        yakus = new ArrayList<>();
        yakusAI = new ArrayList<>();

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
        if (cartaMano != null) System.out.println("AI jugado ya "+ cartaMano.getImage() + " en " + cartaMesa.getImage());
        System.out.println("AI tiene estos yakus: " + yakusAI.size());
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

    public void showYakusAI() {
        Carta chiquita;

        int dyB = 489;
        int dyT = 489;
        int dyR = 489;
        int dyL = 489;

        for (Carta c : yakusAI) {
            chiquita =c;
            chiquita.setHeight(66);
            chiquita.setWidth(40);

            switch (c.getTipo()){
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
                Image combo = new Image(new Texture("elementos/"+yk.getName()+"Frame.png"));
                combo.setPosition(dx, dy);
                stage.addActor(combo);
                dx = dx + 100 + 12;
            }

            if (yakuSizeP != combos.size() && player){
                askKoiKoi();
                yakuSizeP = combos.size();
            }
            else AIplay();
        }

        else {
            float dx = 1000f;
            for (Yaku yk : combos) {
                Image combo = new Image(new Texture("elementos/"+yk.getName()+"Frame.png"));
                dx -= 100;
                combo.setPosition(dx, dy);
                stage.addActor(combo);
                dx = dx - 100 - 12;
            }

            if (yakuSizeAI != combos.size()){
                Random rnd = new Random();
                int rndNumber = rnd.nextInt(1);
                if (rndNumber==0) System.out.println("AI uses KoiKoi!");
                else if (rndNumber==1) System.out.println("AI se planta!");
                else System.out.println("wtf como va a ser "+rndNumber);

                yakuSizeAI = combos.size();
            }


        }

    }

    public void askKoiKoi() {
        stage.addActor(yakuPopUp);
        int score = 0;

        float dx = 570f;
        float dy = 420f;

        for (Yaku yk : pCombos) {
            Image combo = new Image(new Texture("elementos/"+yk.getName()+"Frame.png"));
            combo.setPosition(dx, dy);
            stage.addActor(combo);
            dx = dx + 100 + 12;
            score += yk.getPoints();

            mePlantoBtn.onClick(() -> {
                rondas++;
                yakuPopUp.remove();
                combo.remove();
                mePlantoBtn.remove();
                koiKoiBtn.remove();
            });
            stage.addActor(mePlantoBtn);

            koiKoiBtn.onClick(() -> {
                mult = 2;
                yakuPopUp.remove();
                combo.remove();
                mePlantoBtn.remove();
                koiKoiBtn.remove();
            });
            stage.addActor(koiKoiBtn);
        }

        scorep1ask = new MyLabel(String.valueOf(score), Color.WHITE, 495, 362);


    }


}
