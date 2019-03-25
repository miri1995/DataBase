package DataBase;

import Logic.Filters;
import Logic.Priority;

import java.util.concurrent.TimeUnit;

/**
 * StartConnector class - start the connection with the database.
 */
public class StartConnector {
    /**
     * start the connection when the user chooses the features of the singer.
     * @param filters = holds the values of genre,loudness and tempo that the user chose.
     */
    public void initialize(Filters filters,Priority priority) {
        Connector connector;
        connector = new Connector();
        // connecting
        if (!connector.openConnection())
            return;
        //System.out.print(filters.getGenre());
        // executeQuery
        connector.ExecuteQuery(filters,priority);
        // close the connection
        connector.closeConnection();
      /*  try {
            Thread.sleep(1500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }*/
        System.out.println("Done :)");
    }

   /* public Priority initialize2(Priority ){

    }*/
}