package nl.bidoofgoo.apps.tables;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.Function;
import nl.bidoofgoo.apps.tables.Misc.Variables.HighscoreEndless;
import nl.bidoofgoo.apps.tables.Misc.Variables.PlaysEndless;
import nl.bidoofgoo.apps.tables.Misc.Variables.ResetKeys;

public class Statistiek extends AppCompatActivityMusic {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiek);

        TextView keyamount = findViewById(R.id.keyamount);
        TextView scoreamount = findViewById(R.id.scoreamount);
        TextView playsamount = findViewById(R.id.playamount);
        Button terug = findViewById(R.id.home);

        keyamount.setText(ResetKeys.getValue() + "");
        scoreamount.setText(HighscoreEndless.getValue() + "");
        playsamount.setText(PlaysEndless.getValue() + "");

        ButtonClick.setButtonClickFunction(terug, getResources(), new Function() {
            @Override
            public void whatToDo() {

                finish();
            }
        });
    }
}
