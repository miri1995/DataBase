package DataBase;

import Logic.Filters;


public class StartConnector {

    public void initialize(Filters filters) {

        Connector connector;

        // creating the example object
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
