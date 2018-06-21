package nl.bidoofgoo.apps.tables;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.Function;

public class kiesTafelActivity extends AppCompatActivityMusic{

    private Button[] buttons;

    public void onCreate(Bundle savedInstanceScore){
        super.onCreate(savedInstanceScore);

        setContentView(R.layout.activity_kiestafel);

        setupButtons();

        Button home = (Button)findViewById(R.id.home);

        ButtonClick.setButtonClickFunction(home, getResources(), new Function() {
            @Override
            public void whatToDo() {
                finish();
            }
        });

    }

    private static int buttonno = 0;
    private void setupButtons(){
        buttons = new Button[12];

        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        buttons[9] = findViewById(R.id.button10);
        buttons[10] = findViewById(R.id.button11);
        buttons[11] = findViewById(R.id.button12);

        for (buttonno = 0; buttonno < buttons.length; buttonno++){
            ButtonClick.setButtonClickFunction(buttons[buttonno], getResources(), new Function() {
                int getalToTest = buttonno + 0;

                @Override
                public void whatToDo() {
                    Intent tafelScherm = new Intent(kiesTafelActivity.this, SommenActivity.class);
                    tafelScherm.putExtra("tafelRechts", getalToTest + 1);
                    tafelScherm.putExtra("type", "oefenen");
                    startActivity(tafelScherm);;
                    finish();
                }
            });
        }
}

}
