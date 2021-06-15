import controller.DbHandler;
import controller.MVLogic;

import java.sql.SQLException;

public class MainView {
    public static void main(String[] args) throws SQLException {

        // create DB and tables
        DbHandler.createDBAndTables();

        //read csv files with initial data and fill DB
        DbHandler.addDataFromCSVToDataBase();

        // main menu and view interface
        MVLogic.logicMainView();

    }
}
