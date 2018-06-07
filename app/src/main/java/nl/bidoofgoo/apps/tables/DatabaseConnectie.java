package nl.bidoofgoo.apps.tables;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseConnectie {

    public static void setupDatabase(){
        FirebaseDatabase firebaseDb = FirebaseDatabase.getInstance();
        final DatabaseReference dbReference = firebaseDb.getReference("test");

        dbReference.setValue("Hello world");


    }
}
