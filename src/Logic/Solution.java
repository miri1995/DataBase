package Logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {

   // private String artistName;
    private  List<String> allArtists;



    public Solution(List<String> allArtists)
    {
        allArtists = new ArrayList<String>();
        this.allArtists=allArtists;

      /*  for(int i=0;i<(allArtists.size()-1);i++){
            artistName=allArtists.get(i);
        }*/
       // this.artistName=artistName;
        System.out.print(allArtists);

    }

    public Solution(){
       // this.allArtists = getArtistName();
    }

    public List<String> getArtistName() {
        System.out.print("p"+allArtists);
        return this.allArtists;
    }



}
