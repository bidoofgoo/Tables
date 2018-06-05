package nl.bidoofgoo.apps.tables;

public class Multiplicatie {
    private int cijferL = 0;
    private int cijferR = 0;
    private int antwoord = 0;
    private boolean juist;

    public Multiplicatie() {
    }

    // Maak een instantie aan van multiplicatie die gelijk een linker en een rechter getal aangeeft.
    public Multiplicatie(int left, int right) {
        cijferL = left;
        cijferR = right;
        antwoord = left * right;
    }

    // Genereer een willkeurige tafel berekening
    public void genereerUitdaging(){
        int getal1 = (int)(Math.random() * 9) + 1;
        int getal2 = (int)(Math.random() * 9) + 1;

        cijferL = getal1;
        cijferR = getal2;
        antwoord = getal1 * getal2;
    }

    // Getters en setters
    public int getAntwoord() {
        return antwoord;
    }

    public int getCijferL() {
        return cijferL;
    }

    public int getCijferR() {
        return cijferR;
    }

    public void setJuistGeantwoord(boolean juist) {
        this.juist = juist;
    }
}
