package DataBase;



public class Query {


    public String MapBeat(String genre,String loudness,String tempo){
        int temp=0;
        String q="";
        String mapGenre= "SELECT distinct artists.artist_name\n" +
                "from genre\n" +
                "INNER JOIN genreartists on genre.genre_id = genreartists.genre_id\n" +
                "INNER JOIN artists on artists.artist_id = genreartists.artist_id\n" +
                "WHERE artists.artist_id IN ";
        String notNull=" AND songs.song_loudness IS NOT NULL AND songs.song_tempo IS NOT NULL";
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
                    q=  mapGenre+ "(Select song_artist_id FROM songs where song_tempo < 85 AND song_loudness > -16"+
                            notNull+")";
                }else if(temp==2){
                    // q5;
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between 85 and 170 AND song_loudness>-16"+
                            notNull+")";
                }else
                    // q8;
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>170 AND song_loudness>-16"+
                            notNull+")";
                break;
            case "Normal":
                if(temp==1){
                    // q3;
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo<85 and 170 AND song_loudness BETWEEN -32 and -16"+
                            notNull+")";
                }else if(temp==2){
                    //  q6;
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between 85 and 170 AND song_loudness BETWEEN -32 and -16"+
                            notNull+")";
                }else
                    //  q9;
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>170 AND song_loudness BETWEEN -32 and -16"+
                            notNull+")";
                break;
            case "Strong":
                if(temp==1){
                    // q4;
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo<85 AND not song_loudness>-32"+
                            notNull+")";
                }else if(temp==2){
                    // q7;
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between 85 and 170 AND not song_loudness>-32"+
                            notNull+")";
                }else
                    //q10;
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>170 AND not song_loudness>-32"+
                            notNull+")";
                break;
        }
        String sol=GetSol(q,genre);
        return sol;
    }

    public String UserInput(String genre,String loudness,String tempo){
        String q3=MapBeat(genre,loudness,tempo);
        return q3;
    }

    public String GetSol(String BeatQ, String genre){
        String hotness=" order by artists.artist_hotness DESC";
        String lastQ=BeatQ+" AND genre.genre=\""+genre+"\""+hotness;

        return lastQ;
    }

}
