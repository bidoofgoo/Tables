package nl.bidoofgoo.apps.tables;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class printResults extends AppCompatActivity{

    private Button enterScore;
    private TextView score;

    public void onCreate(Bundle savedInstanceScore){
        super.onCreate(savedInstanceScore);

        setContentView(R.layout.activity_results);

        score = findViewById(R.id.editText2);
        score.setText(Integer.toString(getIntent().getExtras().getInt("score")));

    }

}
