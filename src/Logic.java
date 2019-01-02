
public class Logic {

    public String MapBeat(String loudness,String tempo){
        int temp=0;
        String q="";
        switch (tempo){
            case "Slow":
                temp=1;
                break;
            case "Medium":
                temp=2;
                break;
            case "Fast":
                temp=3;
                break;
        }

        switch (loudness){
            case "Weak":
                if(temp==1){
                    //q2;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo<85 AND song_loudness>-16";
                }else if(temp==2){
                    // q5;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo between 85 and 170 AND song_loudness>-16";
                }else
                    // q8;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo>170 AND song_loudness>-16";
                break;
            case "Normal":
                if(temp==1){
                    // q3;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo<85 and 170 AND song_loudness BETWEEN -32 and -16";
                }else if(temp==2){
                    //  q6;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo between 85 and 170 AND song_loudness BETWEEN -32 and -16";
                }else
                    //  q9;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo>170 AND song_loudness BETWEEN -32 and -16";
                break;
            case "Strong":
                if(temp==1){
                    // q4;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo<85 AND not song_loudness>-32";
                }else if(temp==2){
                    // q7;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo between 85 and 170 AND not song_loudness>-32";
                }else
                    //q10;
                    q= "SELECT artist_name\n" +
                            "FROM artists\n" +
                            "INNER JOIN songs ON artists.artist_id=songs.song_artist_id\n" +
                            "WHERE song_tempo>170 AND not song_loudness>-32";
                break;
        }
        String sol=GetSol(q,"funk metal");
        return sol;
    }

    public String GetSol(String BeatQ, String genre){
        String hotness=" order by artists.artist_hotness DESC";
        String notNull=" AND song_loudness IS NOT NULL AND song_tempo IS NOT NULL";
        String lastQ=BeatQ+" AND artist_genre=\""+genre+"\""+notNull+hotness;

        return lastQ;
    }
}
