package nl.bidoofgoo.apps.tables;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.Function;
import nl.bidoofgoo.apps.tables.Misc.Variables;
import nl.bidoofgoo.apps.tables.Models.Multiplicatie;

public class SommenActivity extends AppCompatActivity {

    // De sommen
    private Multiplicatie[] mults;
    private int hoeveelsteVraag;

    // Algemene user interface elementen
    private TextView linkerGetalUI;
    private TextView rechterGetalUI;
    private TextView input;
    private Button nextButton;
    private ProgressBar timerBar;
    private String type;

    // Oefenen variabelen
    private int antwoordenGoed = 0;
    private int antwoordenFout = 10;

    // Endless Mode
    private boolean endless = false;
    private TextView scoreUI;
    private ConstraintLayout keyLayout;
    private TextView keys;
    int score = 0;
    int currentSom = 0;
    int currentMax = 5;
    int currentMin = 0;
    int toNext = 7;
    CountDownTimer timer;
    final int timerSeconden = 10;
    final int keyTreshold = 500;
    int gottenKeys = 0;
    Button keyButton;
    ImageView keyButtonImage;

    // Alle buttons
    private Button[] buttons;

    // De create functie, initialiseer alle variabelen, stel gamemodes in en
    // zet de event listeners aan
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
        keyLayout = (ConstraintLayout) findViewById(R.id.keyconstraint);
        keys = (TextView) findViewById(R.id.keys);
        keyButton = (Button) findViewById(R.id.keyButton);
        keyButtonImage = (ImageView) findViewById(R.id.keyButtonImage);

        timerBar.setProgress(0);

        // Stel de eventlisteners van de knoppen in
        setupButtons();

        // Stel de gamemode in
        type = getIntent().getExtras().getString("type");
        if (type.equals("uitdaging")) {
            genereerUitdaging();
            hideKeys();
        } else if (type.equals("oefenen")){
            int getal = getIntent().getExtras().getInt("tafelRechts");
            genereerTafels(getal);
            hideKeys();
        }
        else
            setEndless();

        // zet de huidige vraag op de pagina
        laadVraag();
    }

    // Functie die ervoor zorgt dat de timer stopt zodra je de activity verlaat
    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null)
            timer.cancel();
    }

    // De functie die wordt doorgevoerd zodra er iets is ingevult en de gebruiker drukt op volgende
    public void antwoord(int antwoordIngevult){
        // Kijk of het juist is beantwoord
        boolean antwoord = (mults[hoeveelsteVraag].getAntwoord() == antwoordIngevult);

        // Code om score te berekenen
        if (antwoord){
            score += (mults[0].getCijferL() - 1) * (mults[0].getCijferR() - 1) + 1;

            antwoordenGoed += 1;
            antwoordenFout = 10 - antwoordenGoed;

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
                Intent scoreScherm = new Intent(SommenActivity.this, oefenresultsActivity.class);
                scoreScherm.putExtra("goed", antwoordenGoed);
                scoreScherm.putExtra("fout", antwoordenFout);
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

                // Als je boven 500 keer het aantal keys dat je dit potje hebt gekregen
                // Dus voor iedere 500 punten.
                if (score > ((gottenKeys + 1) * keyTreshold)){
                    gottenKeys++;

                    Variables.upKeys(this);
                    updateKeys();
                }

                mults[0].genereerUitdaging(currentMin, currentMax);
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
        updateKeys();
        endless = true;

        mults = new Multiplicatie[1];

        mults[0] = new Multiplicatie();
        mults[0].genereerUitdaging(currentMin, currentMax);
        hoeveelsteVraag = 0;
        score = 0;
        resetTimer();

        updateScore();
    }

    // Verstop de timer key functies
    private void hideKeys(){
        keyLayout.setVisibility(View.GONE);
        keyButton.setVisibility(View.GONE);
        keyButtonImage.setVisibility(View.GONE);
    }

    // Update de keys in de ui naar het daadwerkelijke getal
    private void updateKeys(){
        keys.setText(Variables.getResetKey() + "");
    }

    // Eindig endless mode
    private void endEndless(){
        this.finish();
        timer.cancel();
        Intent scoreScherm = new Intent(SommenActivity.this, printResults.class);
        scoreScherm.putExtra("score", score);
        startActivity(scoreScherm);
    }

    // Reset de timer, als die nog niet bestaat
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
                String ingevult = input.getText().toString();
                if (!ingevult.equals(""))
                    antwoord(Integer.parseInt(ingevult));
                else
                    endEndless();
            }
        };

        timer.start();
    }

    // Zet de score in de ui
    private void updateScore(){
        scoreUI.setText("Score: " + score);
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

        // 1 - 9 keys
        for (buttonno = 0; buttonno < 10; buttonno++){
            ButtonClick.setButtonClickFunction(buttons[buttonno], getResources(), new Function() {
                int getalToTest = buttonno + 0;

                @Override
                public void whatToDo() {
                    editInput(getalToTest);
                }
            });
        }

        // Delete key
        ButtonClick.setButtonClickFunction(buttons[10], getResources(), new Function() {
            @Override
            public void whatToDo() {
                editInput();
            }
        });

        // Antwoord key
        ButtonClick.setButtonClickFunction(nextButton, getResources(), new Function() {
            @Override
            public void whatToDo() {
                String ingevult = input.getText().toString();
                if (!ingevult.equals(""))
                    antwoord(Integer.parseInt(ingevult));
            }
        });

        // Use key button
        ButtonClick.setButtonClickFunctionTransp(keyButton, getResources(), new Function() {
            @Override
            public void whatToDo() {
                if (Variables.getResetKey() > 0){
                    Variables.downKeys(SommenActivity.this);
                    updateKeys();
                    resetTimer();
                }
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
