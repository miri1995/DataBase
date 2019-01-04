package Logic;

import java.awt.*;
import java.util.List;

public class Solution {

    private String artistName;
    private List<String> allArtists;

    public Solution(List<String> allArtists)
    {
        this.allArtists=allArtists;
       // this.artistName=artistName;

    }

    public Solution(){
       artistName=getArtistName();
    }

    public String getArtistName() {
        return artistName;
    }



}
