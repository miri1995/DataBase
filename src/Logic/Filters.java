package Logic;

import DataBase.StartConnector;

public class Filters {


    private String genre;
    private String loudness;
    private String tempo;

    public Filters(String genre, String loudness, String tempo)
    {
        this.genre=genre;
        this.loudness = loudness;
        this.tempo = tempo;
        getCon(this);
    }

    public Filters(){
        genre=getGenre();
        loudness=getLoudness();
        tempo=getTempo();
    }

    public String getGenre() {
        return genre;
    }
    public String getLoudness() {
        return loudness;
    }
    public String getTempo() {
        return tempo;
    }

    public void getCon(Filters filters){
        if (genre!=null){
            StartConnector m=new StartConnector();
            m.initialize(filters);
        }
    }
}
