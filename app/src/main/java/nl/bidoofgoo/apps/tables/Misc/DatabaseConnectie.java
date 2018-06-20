package nl.bidoofgoo.apps.tables.Misc;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import nl.bidoofgoo.apps.tables.Models.ScoreModel;

public class DatabaseConnectie {

    private static FirebaseDatabase firebaseDb;
    private static DatabaseReference dbReference;
    private static DatabaseReference dbPost;
    private static Query dbOrdered;
    private static ArrayList<ScoreModel> scores = new ArrayList<ScoreModel>();


    public static void setupDatabase(){
        scores.add(new ScoreModel(0, "Offline"));
        firebaseDb = FirebaseDatabase.getInstance();
        dbReference = firebaseDb.getReference();

        dbReference.child("test").setValue("wat is dit een test");

        dbPost = dbReference.child("Scores");
        dbOrdered = dbPost.orderByChild("score").limitToLast(10);


        dbOrdered.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scores = new ArrayList<ScoreModel>();

                for (DataSnapshot post: dataSnapshot.getChildren()){
                    Log.d("database", "onDataChange: " + post);
                    scores.add(post.getValue(ScoreModel.class));
                }

                Collections.reverse(scores);

                Log.d("database", "got " + scores);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database", "Hey the database is broken");
            }
        });
    }

    public static ArrayList<ScoreModel> getScores() {
        return scores;
    }

    public static void pushScore(ScoreModel score){
        DatabaseReference postPlace = dbReference.child("Scores").push();
        postPlace.setValue(score);
    }
}
