package DataBase;
import GUI.Solution;
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

    public void ReadFile(String fileName) {

    }

    public boolean openConnection() {

        System.out.print("Trying to connect... ");

        // creating the connection. Parameters should be taken from config file.
        String host = "localhost";
        String port = "3306";
        String schema = "databaseproject";
        String user = "root";
        String password = "miri1995";

        //change the path here
     /*   File file = new File("src/config.txt");
        BufferedReader r = null;
        String schema="";
        String user="";
        String password="";

        try {
            r = new BufferedReader(new FileReader(file));
             schema = r.readLine();

            user = r.readLine();

             password = r.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
     /*   StringBuffer schema = new StringBuffer();
        try {
            File file = new File("src/config.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                schema=L
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
           // System.out.println("Contents of file:");
          //  System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String schema=stringBuffer[0];
        String user=stringBuffer[1];
        String password=stringBuffer[2];

        System.out.print(schema);
        System.out.print(user);
        System.out.print(password);*/
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
        Query query =new Query();
        String q3= query.UserInput(filters.getGenre(),filters.getLoudness(),filters.getTempo());

           try (Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(q3);) {
               while (rs.next() == true) {
                 artists.add(rs.getString("artist_name"));
               }
               Solution.getInstance(artists);

             //  demoExecuteUpdate(artists,query,filters.getGenre(),filters.getLoudness(),filters.getTempo());
           } catch (SQLException e) {
               System.out.println("ERROR executeQuery - " + e.getMessage());
           }

       }


    /**
     * Shows executeUpdate
     */
    public void demoExecuteUpdate(List<String> artists, Query query, String genre, String loudness, String tempo) {
        int result;
        String create=query.CreatHistoryTable();
        try (Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(create);
            while (rs.next() == true) {
                result = stmt.executeUpdate("INSERT INTO History(artists,genre,loudness,tempo) " + "VALUES(artists,genre,loudness,tempo)");
                //result = stmt.executeUpdate("INSERT INTO demo(fname, lname) " + "VALUES('Ryan','Gosling')");
                // result = stmt.executeUpdate("DELETE FROM demo");
                System.out.println("Success - executeUpdate, result = " + result);
            }
        } catch (SQLException e) {
            System.out.println("ERROR executeUpdate - " + e.getMessage());
        }
    }

    /**
     * Shows transaction management
     */
    public void demoTransactions() {

        try (Statement stmt = conn.createStatement();) {
            conn.setAutoCommit(false);
            stmt.executeUpdate("INSERT INTO demo(fname, lname) VALUES('Transaction','Demo')");
            stmt.executeUpdate("INSERT INTO demo(fname, lname) VALUES('Transaction - should fail')");

            // committing
            System.out.println("Commiting transaction..");
            conn.commit();

            System.out.println("Error, transaction should fail and not reach this code");

        } catch (SQLException e) {
            System.out.println("We have an exception, transaction is not complete. Exception: " + e.getMessage());
            try {
                conn.rollback();
                System.out.println("Rollback Successfully :)");
            } catch (SQLException e2) {
                System.out.println("ERROR demoTransactions (when rollbacking) - " + e.getMessage());
            }
        } finally {
            safelySetAutoCommit();
        }
    }

    /**
     * Attempts to set the connection back to auto-commit, ignoring errors.
     */
   private void safelySetAutoCommit() {
        try {
            conn.setAutoCommit(true);
        } catch (Exception e) {
        }
    }

    /**
     * Shows long insert
     */
    public void demoWithoutPreparedStatement() {
        long time = System.currentTimeMillis();
        try (Statement stmt = conn.createStatement();) {

            int i = 0;
            while (i < 2000) {
               // stmt.executeUpdate(
                //        "INSERT INTO demo(fname, lname) VALUES('" + Names.getFName() + "','" + Names.getLName() + "')");

                if (i % 200 == 0)
                    System.out.println("Statement - current record: " + i);

                i++;
            }

            System.out.println("Success - demoWithoutPreparedStatement");

            printTimeDiff(time);
        } catch (SQLException e) {
            System.out.println("ERROR demoWithoutPreparedStatement - " + e.getMessage());
        }
    }

    /**
     * Shows the PreparedStatement()
     */
    public void demoWithPreparedStatement() {
        long time = System.currentTimeMillis();
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO demo(fname,lname) VALUES(?,?)");) {

            int i = 0;
            while (i < 2000) {
             //   pstmt.setString(1, Names.getFName());
               // pstmt.setString(2, Names.getLName());
                pstmt.executeUpdate();

                if (i % 200 == 0)
                    System.out.println("PreparedStatement - current record: " + i);

                i++;
            }

            System.out.println("Success - demoWithPreparedStatement");

            printTimeDiff(time);
        } catch (SQLException e) {
            System.out.println("ERROR demoWithPreparedStatement - " + e.getMessage());
        }
    }

    /**
     * Shows the Batch PreparedStatement()
     */
    public void demoBatchPreparedStatement() {
        long time = System.currentTimeMillis();
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO demo(fname,lname) VALUES(?,?)");) {

            int i = 0;
            while (i < 2000) {
            //    pstmt.setString(1, Names.getFName());
              //  pstmt.setString(2, Names.getLName());
                pstmt.addBatch();

                if (i % 200 == 0)
                    System.out.println("Batch - current record: " + i);

                i++;
            }

            pstmt.executeBatch();

            System.out.println("Success - demoWithPreparedStatement");

            printTimeDiff(time);
        } catch (SQLException e) {
            System.out.println("ERROR demoWithPreparedStatement - " + e.getMessage());
        }
    }

    /**
     * Shows the Batch PreparedStatementTransaction()
     */
    public void demoBatchPreparedStatementTransaction() {
        long time = System.currentTimeMillis();
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO demo(fname,lname) VALUES(?,?)");) {

            conn.setAutoCommit(false);

            int i = 0;
            while (i < 2000) {
            //    pstmt.setString(1, Names.getFName());
              //  pstmt.setString(2, Names.getLName());
                pstmt.addBatch();

                if (i % 200 == 0)
                    System.out.println("Batch - current record: " + i);

                i++;
            }

            pstmt.executeBatch();
            conn.commit();

            System.out.println("Success - demoWithPreparedStatement + transaction");

            printTimeDiff(time);
        } catch (SQLException e) {
            System.out.println("ERROR demoWithPreparedStatement - " + e.getMessage());
        } finally {
            safelySetAutoCommit();
        }
    }

    /**
     * Shows how to retrieve the generated ID (by "auto incremental")
     */
    public void demoGetGeneratedID() {
        Statement stmt = null;
        ResultSet rs = null;
        int id;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO demo(fname, lname) VALUES('John','Legend')", new String[] { "ID" });
            rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);

            System.out.println("Success - GetGeneratedID, the generated ID is: " + id);

        } catch (SQLException e) {
            System.out.println("ERROR executeUpdate - " + e.getMessage());
        } finally {
            safelyClose(rs, stmt);
        }
    }

    /**
     * Attempts to close all the given resources, ignoring errors
     *
     * @param resources
     */
    private void safelyClose(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            try {
                resource.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Prints the time difference from now to the input time.
     */
    private void printTimeDiff(long time) {
        time = (System.currentTimeMillis() - time) / 1000;
        System.out.println("Took " + time + " seconds");
    }

}
