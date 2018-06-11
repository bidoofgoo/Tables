package nl.bidoofgoo.apps.tables;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseConnectie {

    private static FirebaseDatabase firebaseDb;
    private static DatabaseReference dbReference;
    private static DatabaseReference dbPost;
    private static ArrayList<ScoreModel> scores;

    public static void setupDatabase(){
        firebaseDb = FirebaseDatabase.getInstance();
        dbReference = firebaseDb.getReference();

        dbReference.child("test").setValue("wat is dit een test");

        dbPost = dbReference.child("Scores");

        dbPost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scores = new ArrayList<ScoreModel>();

                for (DataSnapshot post: dataSnapshot.getChildren()){
                    Log.d("database", "onDataChange: " + post);
                    scores.add(post.getValue(ScoreModel.class));
                }

                Log.d("database", "got " + scores);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database", "Hey the database is broken");
            }
        });
    }

    public static void getScores(){
        // Read from the database

    }

    public static void pushScore(ScoreModel score){
        DatabaseReference postPlace = dbReference.child("Scores").push();
        postPlace.setValue(score);
    }
}
