package nl.bidoofgoo.apps.tables;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class printResults extends AppCompatActivity{

    private Button enterScore;
    private TextView scoreUi;
    private EditText name;
    int score;

    public void onCreate(Bundle savedInstanceScore){
        super.onCreate(savedInstanceScore);

        setContentView(R.layout.activity_results);

        score = getIntent().getExtras().getInt("score");

        scoreUi = findViewById(R.id.editText2);
        scoreUi.setText(Integer.toString(score));

        name = findViewById(R.id.nameInput);

        enterScore = findViewById(R.id.enterScore);

        ButtonClick.setButtonClickFunction(enterScore, getResources(), new Function() {
                    @Override
                    public void whatToDo() {
                        Intent enterScoreScherm = new Intent(printResults.this, LeaderBoardActivity.class);
                        startActivity(enterScoreScherm);
                        DatabaseConnectie.pushScore(new ScoreModel(score, name.getText().toString()));
                        finish();
                    }
                }
        );

    }

}
