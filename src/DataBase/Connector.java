package DataBase;
import Logic.Solution;
import Logic.Filters;

import java.io.*;
import java.sql.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

/**
 * Different types of JDBC usage
 */
public class Connector {
    public static int counter;
    Connection conn; // DB connection
    List<String> artists = new ArrayList<String>();
    /**
     * Empty constructor
     */
    public Connector() {
        this.conn = null;
    }

    /**
     *
     * @return true if the connection was successfully set
     */



    public boolean openConnection() {

        System.out.print("Trying to connect... ");

        // creating the connection. Parameters should be taken from config file.
        String host = "localhost";
        String port = "3306";
    /*    String schema = "databaseproject";
        String user = "root";
        String password = "miri1995";*/
        String schema = "";
        String user = "";
        String password = "";

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/config"));
             schema = br.readLine();
             user = br.readLine();
             password = br.readLine();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ioe) {
                System.out.println("Error in closing the BufferedReader");
            }
        }


        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + schema, user, password);
        } catch (SQLException e) {
            System.out.println("Unable to connect - " + e.getMessage());
            conn = null;
            return false;
        }
        System.out.println("Connected!");
        return true;
    }

    /**
     * close the connection
     */
    public void closeConnection() {
        // closing the connection
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Unable to close the connection - " + e.getMessage());
        }

    }



    public void ExecuteQuery(Filters filters) {
        artists.clear();
        Query query =new Query();
        String q3= query.UserInput(filters.getGenre(),filters.getLoudness(),filters.getTempo());

           try (Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(q3);) {
               while (rs.next() == true) {
                 artists.add(rs.getString("artist_name"));
              //   System.out.print(rs.getString("artist_name").toString());
               }

                counter++;
              // System.out.print(counter);
              //  for(int i=0;i<counter;i++){
                    Solution.getInstance(artists);
              //  }

           } catch (SQLException e) {
               System.out.println("ERROR executeQuery - " + e.getMessage());
           }

       }


}
