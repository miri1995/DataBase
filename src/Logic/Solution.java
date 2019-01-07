package Logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static Solution single_instance = null;
    private final  List<String> allArtists;
    private static int counter=0;

    private Solution(List<String> allArtists)
    {
        this.allArtists=allArtists;
    }

    public synchronized static Solution getInstance(List<String> allArtists)
    {
       // single_instance=null;
        if (single_instance == null || counter>0) {
            //System.out.print(allArtists);
            single_instance = new Solution(allArtists);
        }
      //  System.out.print(single_instance.getallArtists());
        return single_instance;
    }

    public List<String> getallArtists(){
        return this.allArtists;
    }

    public void clearallArtists(){
         this.allArtists.clear();
         counter++;
         System.out.print(counter);
    }




}
