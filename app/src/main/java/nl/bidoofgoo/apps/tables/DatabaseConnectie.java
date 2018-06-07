package nl.bidoofgoo.apps.tables;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseConnectie {

    private static FirebaseDatabase firebaseDb;
    private static DatabaseReference dbReference;

    public static void setupDatabase(){
        firebaseDb = FirebaseDatabase.getInstance();
        dbReference = firebaseDb.getReference();

        dbReference.child("test").setValue("wat is dit een test");


    }

    public static void getScores(){
        // Read from the database

    }

    public static void pushScore(ScoreModel score){
        DatabaseReference postPlace = dbReference.child("Scores").push();
        postPlace.setValue(score);
    }
}
