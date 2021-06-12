package test;

import com.myproject.Object.Carta;
import com.myproject.Object.Mazo;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class MazoTest {

    @org.junit.jupiter.api.Test
    void cardCounter() {
        int contTane = 0;
        int contLight = 0;
        int contRibbon= 0;
        int contBase= 0;

        int contBDB= 0;
        int contBLUE= 0;
        int contRED = 0;

        int contTotal = 0;

        Mazo mazo = new Mazo();


        for (Carta c: mazo.cartaList) {
            if (c.getTipo().equals("TANE")) contTane++;
            if (c.getTipo().equals("LIGHT")) contLight++;
            if (c.getTipo().equals("RIBBON")) contRibbon++;
            if (c.getTipo().equals("BASE")) contBase++;

            if (c.getSpecial().equals("BDB")) contBDB++;
            if (c.getSpecial().equals("BLUE")) contBLUE++;
            if (c.getSpecial().equals("RED")) contRED++;

            contTotal++;
        }
        System.out.println("Tane: ");
        Assertions.assertEquals(9, contTane);
        System.out.println("Ribbon: ");
        Assertions.assertEquals(10, contRibbon);
        System.out.println("Light: ");
        Assertions.assertEquals(5, contLight);
        System.out.println("Base: ");
        Assertions.assertEquals(24, contBase);

        System.out.println("BDB: ");
        Assertions.assertEquals(3, contBDB);
        System.out.println("BLUE: ");
        Assertions.assertEquals(3, contBLUE);
        System.out.println("RED: ");
        Assertions.assertEquals(3, contRED);

        System.out.println("Total cards: ");
        Assertions.assertEquals(48, contTotal);

        mazo.seeCards();

    }
}