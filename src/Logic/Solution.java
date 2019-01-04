package Logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {


    private  List<String> allArtists;

    public Solution(List<String> allArtists)
    {
        this.allArtists = new ArrayList<String>();
        this.allArtists=allArtists;
        System.out.print(allArtists);

    }

    public Solution(){
        this.allArtists = getArtistName();
    }

    public List<String> getArtistName() {
        System.out.print("p"+allArtists);
        return this.allArtists;
    }



}
