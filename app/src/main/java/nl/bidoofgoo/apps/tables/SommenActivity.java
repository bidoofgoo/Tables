package nl.bidoofgoo.apps.tables;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;

import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.Function;
import nl.bidoofgoo.apps.tables.Models.Multiplicatie;

public class SommenActivity extends AppCompatActivity {

    private Multiplicatie[] mults;
    private int hoeveelsteVraag;

    private TextView linkerGetalUI;
    private TextView rechterGetalUI;
    private TextView input;
    private Button nextButton;
    private ProgressBar timerBar;

    // Endless Mode
    private boolean endless = false;
    private TextView scoreUI;
    int score = 0;
    int currentSom = 0;
    int currentMax = 5;
    int currentMin = 0;
    int toNext = 7;
    CountDownTimer timer;
    final int timerSeconden = 10;

    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sommen);

        linkerGetalUI = (TextView) findViewById(R.id.lNummer);
        rechterGetalUI = (TextView) findViewById(R.id.rNummer);
        input = (TextView) findViewById(R.id.input);
        nextButton = (Button) findViewById(R.id.nextButton);
        scoreUI = (TextView) findViewById(R.id.scoreSpot);
        timerBar = (ProgressBar) findViewById(R.id.timer);

        timerBar.setProgress(0);

        int getal = getIntent().getExtras().getInt("tafelRechts");

        setupButtons();

        String type = getIntent().getExtras().getString("type");
        if (type.equals("uitdaging"))
            genereerUitdaging();
        else if(type.equals("oefenen"))
            genereerTafels(getal);
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
                Intent scoreScherm = new Intent(SommenActivity.this, printResults.class);
                scoreScherm.putExtra("score", score);
                startActivity(scoreScherm);
            }else{
                laadVraag();
            }
        }else{
            // Als het andwoord fout is
            if (!antwoord){
                endEndless();
            }else{
                resetTimer();
                // Maak een willekeurige uidaging
                currentSom++;
                // Als dit de 7e vraag is, verhoog de moeilijkheid
                if (currentSom % toNext == 0){
                    currentMax += 2;
                    currentMin += 1;
                }

                mults[0].genereerUitdaging(0, currentMax);
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
            mults[i].genereerUitdaging(0, 10);
        }

        hoeveelsteVraag = 0;
    }

    // Genereer 10 tafels
    public void genereerTafels(int getal){
        mults = new Multiplicatie[10];

        for (int i = 0; i < 10; i++) {
            mults[i] = new Multiplicatie(i + 1, getal);
        }

        hoeveelsteVraag = 0;
    }

    // Zorgt ervoor dat er een endless stream komt aan vragen
    public void setEndless(){
        endless = true;

        mults = new Multiplicatie[1];

        mults[0] = new Multiplicatie();
        mults[0].genereerUitdaging(currentMin, currentMax);
        hoeveelsteVraag = 0;
        score = 0;
        resetTimer();

        updateScore();
    }

    private void endEndless(){
        this.finish();
        timer.cancel();
        Intent scoreScherm = new Intent(SommenActivity.this, printResults.class);
        scoreScherm.putExtra("score", score);
        startActivity(scoreScherm);
    }

    private void resetTimer(){
        timerBar.setProgress(100);

        if (timer != null)
            timer.cancel();

        timer = new CountDownTimer(timerSeconden * 1000, 1000) {
            @Override
            public void onTick(long passed) {
                Log.d("tmr", "onTick: " + passed);
                timerBar.setProgress((int)(passed / 100));
            }

            @Override
            public void onFinish() {
                endEndless();
            }
        };

        timer.start();
    }

    private void updateScore(){
        scoreUI.setText("ScoreModel: " + score);
    }

    public int buttonno = 0;
    private void setupButtons(){
        buttons = new Button[11];

        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);
        buttons[9] = findViewById(R.id.button9);
        buttons[10] = findViewById(R.id.buttonDel);

        for (buttonno = 0; buttonno < 10; buttonno++){
            ButtonClick.setButtonClickFunction(buttons[buttonno], getResources(), new Function() {
                int getalToTest = buttonno + 0;

                @Override
                public void whatToDo() {
                    editInput(getalToTest);
                }
            });
        }

        ButtonClick.setButtonClickFunction(buttons[10], getResources(), new Function() {
            @Override
            public void whatToDo() {
                editInput();
            }
        });
    }

    private void editInput(int getal){
        if (input.getText().toString().length() < 5){
            input.setText(input.getText().toString() + getal);
        }
    }

    private void editInput(){
        String str = input.getText().toString();
        if (str.length() > 0){
            input.setText(str.substring(0, str.length() - 1));;
        }
    }

}
