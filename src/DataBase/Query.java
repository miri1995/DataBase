package DataBase;

/**
 * Query class - responsible for making the queries.
 */
public class Query {

    /**
     * creates the queries according to the user's choices.
     * @param genre = user's choice of genre
     * @param loudness = user's choice of loudness.
     * @param tempo = user's choice of tempo.
     * @return sol = the final query
     */
    public String MapBeat(String genre,String loudness,String tempo){
        int temp=0;
        String q="";
        // the base query which will be the first part of all the quries
        String mapGenre= "SELECT distinct artists.artist_name\n" +
                "from genre\n" +
                "INNER JOIN genreartists on genre.genre_id = genreartists.genre_id\n" +
                "INNER JOIN artists on artists.artist_id = genreartists.artist_id\n" +
                "WHERE artists.artist_id IN ";
        // check that this features are not null
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
        // according to the loudness and tempo chosen by the user creates the continuation of the query.
        switch (loudness){
            case "Weak":
                if(temp==1){
                    q=  mapGenre+ "(Select song_artist_id FROM songs where song_tempo < 85 AND song_loudness > -16"+
                            notNull+")";
                }else if(temp==2){
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between 85 and 170 AND song_loudness>-16"+
                            notNull+")";
                }else
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>170 AND song_loudness>-16"+
                            notNull+")";
                break;
            case "Normal":
                if(temp==1){
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo<85 and 170 AND song_loudness BETWEEN -32 and -16"+
                            notNull+")";
                }else if(temp==2){
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between 85 and 170 AND song_loudness BETWEEN -32 and -16"+
                            notNull+")";
                }else
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>170 AND song_loudness BETWEEN -32 and -16"+
                            notNull+")";
                break;
            case "Strong":
                if(temp==1){
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo<85 AND not song_loudness>-32"+
                            notNull+")";
                }else if(temp==2){
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between 85 and 170 AND not song_loudness>-32"+
                            notNull+")";
                }else
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>170 AND not song_loudness>-32"+
                            notNull+")";
                break;
        }
        // sends to a function that is responsible for Concatenation of the strings into final query.
        String sol=GetSol(q,genre);
        //returns the final query
        return sol;
    }

    /**
     * receives the user's choices and compose from them the matching query.
     * @param genre = user's choice of genre
     * @param loudness = user's choice of loudness.
     * @param tempo = user's choice of tempo.
     * @return q = the matching query
     */
    public String UserInput(String genre,String loudness,String tempo){
        String q=MapBeat(genre,loudness,tempo);
        return q;
    }

    /**
     * composes all the query parts into one query.
     * @param BeatQ  = the combination of the tempo and loudness chosen by the user.
     * @param genre = user's choice of genre.
     * @return lastQ = the final query
     */
    public String GetSol(String BeatQ, String genre){
        String hotness=" order by artists.artist_hotness DESC";
        String lastQ=BeatQ+" AND genre.genre=\""+genre+"\""+hotness;

        return lastQ;
    }

}