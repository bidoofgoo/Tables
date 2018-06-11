package nl.bidoofgoo.apps.tables;

public class ScoreModel {

    // Model for score
    // Data to get from / write to the database
    private int score = 0;
    private String naam = "";

    public ScoreModel(){

    }

    public ScoreModel(int score, String naam){
        this.score = score;
        this.naam = naam;
    }

    // Getters & setters
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public String toString() {
        return naam + " got score " + score;
    }
}
