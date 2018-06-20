package nl.bidoofgoo.apps.tables;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.Function;

public class SelectMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);

        // Instanciate all buttons
        Button tafel = (Button) findViewById(R.id.buttonTafel);
        Button uitdaging = (Button) findViewById(R.id.buttonUitdaging);
        Button eindeloos = (Button) findViewById(R.id.buttonEindeloos);
        Button home = (Button) findViewById(R.id.home);

        ButtonClick.setButtonClickFunction(home, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                finish();
            }
        });

        // Button events
        ButtonClick.setButtonClickFunction(uitdaging, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent tafelScherm = new Intent(SelectMode.this, SommenActivity.class);
                tafelScherm.putExtra("type", "uitdaging");
                startActivity(tafelScherm);
            }
        });

        ButtonClick.setButtonClickFunction(tafel, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent kiesTafel = new Intent(SelectMode.this, kiesTafelActivity.class);
                startActivity(kiesTafel);
            }
        });

        ButtonClick.setButtonClickFunction(eindeloos, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent tafelScherm = new Intent(SelectMode.this, SommenActivity.class);
                tafelScherm.putExtra("type", "eindeloos");
                startActivity(tafelScherm);
            }
        });
    }
}