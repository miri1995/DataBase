package DataBase;

import Logic.Filters;

/**
 * StartConnector class - start the connection with the database.
 */
public class StartConnector {
    /**
     * start the connection when the user chooses the features of the singer.
     * @param filters = holds the values of genre,loudness and tempo that the user chose.
     */
    public void initialize(Filters filters) {
        Connector connector;
        connector = new Connector();
        // connecting
        if (!connector.openConnection())
            return;
        System.out.print(filters.getGenre());
        // executeQuery
        connector.ExecuteQuery(filters);
        // close the connection
        connector.closeConnection();
        System.out.println("Done :)");
    }

}