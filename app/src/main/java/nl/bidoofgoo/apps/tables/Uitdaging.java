package nl.bidoofgoo.apps.tables;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Uitdaging extends AppCompatActivity {

    private Multiplicatie[] mults;
    private int hoeveelsteVraag;

    private TextView linkerGetalUI;
    private TextView rechterGetalUI;
    private EditText input;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uitdaging);

        linkerGetalUI = (TextView) findViewById(R.id.lNummer);
        rechterGetalUI = (TextView) findViewById(R.id.rNummer);
        input = (EditText) findViewById(R.id.invoer);
        nextButton = (Button) findViewById(R.id.nextButton);

        genereerUitdaging();
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

}
