package Logic;

import DataBase.MainJDBC;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

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
            MainJDBC m=new MainJDBC();
            m.q(filters);
        }
    }
}
