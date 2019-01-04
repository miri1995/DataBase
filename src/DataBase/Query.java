package DataBase;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class Query {

    private boolean search=true;

    public String MapBeat(String genre,String loudness,String tempo){
        int temp=0;
        String q="";
        String mapGenre= "SELECT distinct artists.artist_name\n" +
                "from genre\n" +
                "INNER JOIN genreartists on genre.genre_id = genreartists.genre_id\n" +
                "INNER JOIN artists on artists.artist_id = genreartists.artist_id\n" +
                "INNER JOIN databaseproject.songs ON artists.artist_id=songs.song_artist_id\n";
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
                    q=  mapGenre+ "WHERE song_tempo<85 AND song_loudness>-16";
                }else if(temp==2){
                    // q5;
                    q= mapGenre+
                            "WHERE song_tempo between 85 and 170 AND song_loudness>-16";
                }else
                    // q8;
                    q= mapGenre+
                            "WHERE song_tempo>170 AND song_loudness>-16";
                break;
            case "Normal":
                if(temp==1){
                    // q3;
                    q= mapGenre+
                            "WHERE song_tempo<85 and 170 AND song_loudness BETWEEN -32 and -16";
                }else if(temp==2){
                    //  q6;
                    q= mapGenre+
                            "WHERE song_tempo between 85 and 170 AND song_loudness BETWEEN -32 and -16";
                }else
                    //  q9;
                    q= mapGenre+
                            "WHERE song_tempo>170 AND song_loudness BETWEEN -32 and -16";
                break;
            case "Strong":
                if(temp==1){
                    // q4;
                    q= mapGenre+
                            "WHERE song_tempo<85 AND not song_loudness>-32";
                }else if(temp==2){
                    // q7;
                    q= mapGenre+
                            "WHERE song_tempo between 85 and 170 AND not song_loudness>-32";
                }else
                    //q10;
                    q= mapGenre+
                            "WHERE song_tempo>170 AND not song_loudness>-32";
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
        String notNull=" AND songs.song_loudness IS NOT NULL AND songs.song_tempo IS NOT NULL";

        String lastQ=BeatQ+" AND genre.genre=\""+genre+"\""+notNull+hotness;
        //String lastQ=BeatQ;
        return lastQ;
    }

    public String CreatHistoryTable(){
        String sol="";
        if(search){
            String c="CREATE TABLE History (\n" +
                    "    artist_name varchar(255),\n" +
                    "    genre varchar(255),\n" +
                    "    loudness varchar(255),\n" +
                    "    tempo varchar(255),\n" +
                    ");";
            String createTable="CREATE table History(artist_name artist_name);";
            String col1=" alter table History add column(genre varchar(256));";
            String col2=" alter table History add column(loudness varchar(256));";
            String col3=" alter table History add column(tempo varchar(256));";
             sol=c;
            search=false;
        }
        return sol;
    }
}
