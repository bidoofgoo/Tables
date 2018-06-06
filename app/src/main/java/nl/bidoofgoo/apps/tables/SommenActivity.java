package nl.bidoofgoo.apps.tables;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class SommenActivity extends AppCompatActivity {

    private Multiplicatie[] mults;
    private int hoeveelsteVraag;

    private TextView linkerGetalUI;
    private TextView rechterGetalUI;
    private TextView input;
    private Button nextButton;

    // Endless Mode
    private boolean endless = false;
    private TextView scoreUI;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sommen);

        linkerGetalUI = (TextView) findViewById(R.id.lNummer);
        rechterGetalUI = (TextView) findViewById(R.id.rNummer);
        input = (TextView) findViewById(R.id.input);
        nextButton = (Button) findViewById(R.id.nextButton);
        scoreUI = (TextView) findViewById(R.id.scoreSpot);



        String type = getIntent().getExtras().getString("type");
        if (type.equals("uitdaging"))
            genereerUitdaging();
        else if(type.equals("oefenen"))
            genereerTafels(7);
        else
            setEndless();

        laadVraag();

        ButtonClick.setButtonClickFunction(nextButton, getResources(), new Function() {
            @Override
            public void whatToDo() {
                String ingevult = input.getText().toString();
                if (!ingevult.equals(""))
                    antwoord(Integer.parseInt(ingevult));
            }
        });
    }

    // De functie die wordt doorgevoerd zodra er iets is ingevult en de gebruiker drukt op volgende
    public void antwoord(int antwoordIngevult){
        // Kijk of het juist is beantwoord
        boolean antwoord = (mults[hoeveelsteVraag].getAntwoord() == antwoordIngevult);

        // Code om score te berekenen
        if (antwoord){
            score += (mults[0].getCijferL() - 1) * (mults[0].getCijferR() - 1) + 1;
            updateScore();
        }

        if (!endless){

            // Vul het antwoord in in de multiplicatie
            mults[hoeveelsteVraag].setJuistGeantwoord(antwoord);

            // Volgende vraag
            hoeveelsteVraag++;
            // Tenzij alle vragen zijn beantwoord, ga dan terug
            if (hoeveelsteVraag > 9){
                // Even zodat hij niet vast loopt zodra je alles hebt aangepast
                this.finish();
            }else{
                laadVraag();
            }
        }else{
            // Als het andwoord fout is
            if (!antwoord){
                this.finish();
            }else{
                // Maak een willekeurige uidaging
                mults[0].genereerUitdaging();
                laadVraag();
            }
        }
    }

    // Laad de huidige vraag in op de pagina
    private void laadVraag(){
        linkerGetalUI.setText(Integer.toString(mults[hoeveelsteVraag].getCijferL()));
        rechterGetalUI.setText(Integer.toString(mults[hoeveelsteVraag].getCijferR()));
        input.setText("");
    }



    // Genereer 10 willekeurige tafels.
    public void genereerUitdaging(){
         mults = new Multiplicatie[10];

        for (int i = 0; i < 10; i++) {
            mults[i] = new Multiplicatie();
            mults[i].genereerUitdaging();
        }

        hoeveelsteVraag = 0;
    }

    // Genereer 10 tafels
    public void genereerTafels(int getal){
        mults = new Multiplicatie[10];

        for (int i = 0; i < 10; i++) {
            mults[i] = new Multiplicatie(getal, i+1);
        }

        hoeveelsteVraag = 0;
    }

    // Zorgt ervoor dat er een endless stream komt aan vragen
    public void setEndless(){
        endless = true;

        mults = new Multiplicatie[1];

        mults[0] = new Multiplicatie();
        mults[0].genereerUitdaging();
        hoeveelsteVraag = 0;
        score = 0;

        updateScore();
    }

    private void updateScore(){
        scoreUI.setText("Score: " + score);
    }

}
