package Logic;

import DataBase.StartConnector;

/**
 * Filters class - holds the singer's features.
 */
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

    /**
     * @return genre = chosen genre
     */
    public String getGenre() {
        return genre;
    }
    /**
     * @return loudness = chosen loudness
     */
    public String getLoudness() {
        return loudness;
    }
    /**
     * @return tempo = chosen tempo
     */
    public String getTempo() {
        return tempo;
    }
    /**
     * connect
     */
    public void getCon(Filters filters){
        if (genre!=null){
            StartConnector m=new StartConnector();
            m.initialize(filters);
        }
    }
}