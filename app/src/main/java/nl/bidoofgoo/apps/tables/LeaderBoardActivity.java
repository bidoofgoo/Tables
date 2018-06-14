package nl.bidoofgoo.apps.tables;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import nl.bidoofgoo.apps.tables.Lists.ScoreListAdapter;
import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.DatabaseConnectie;
import nl.bidoofgoo.apps.tables.Misc.Function;
import nl.bidoofgoo.apps.tables.Models.ScoreModel;

public class LeaderBoardActivity extends AppCompatActivity {

    private ScoreListAdapter scoreAdapter;
    private ListView scoreLijst;
    private List<ScoreModel> scoreModels;


    public void onCreate(Bundle savedInstanceScore){
        super.onCreate(savedInstanceScore);
        setContentView(R.layout.activity_leaderboards);

        scoreLijst = (ListView) findViewById(R.id.ScoreList);
        scoreModels = DatabaseConnectie.getScores();

        scoreAdapter = new ScoreListAdapter(LeaderBoardActivity.this, 0, scoreModels);

        scoreLijst.setAdapter(scoreAdapter);

        Button home = (Button) findViewById(R.id.home);
        ButtonClick.setButtonClickFunction(home, getResources(), new Function() {
            @Override
            public void whatToDo() {
                finish();
            }
        });
    }
}
