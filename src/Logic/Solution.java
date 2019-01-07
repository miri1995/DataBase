package GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static Solution single_instance = null;
    private final  List<String> allArtists;

    private Solution(List<String> allArtists)
    {
        // this.allArtists = new ArrayList<String>();
        this.allArtists=allArtists;

    }

    public synchronized static Solution getInstance(List<String> allArtists)
    {
        if (single_instance == null)
            single_instance = new Solution(allArtists);


        return single_instance;
    }

    public List<String> getallArtists(){
        return this.allArtists;
    }

    public void clearallArtists(){
         this.allArtists.clear();
    }




}
