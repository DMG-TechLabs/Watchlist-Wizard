package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import kdesp73.madb.MADB;

public class Database {
        private static final String FILEPATH = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") +  "/data/MoviesDatabase.accdb";

        public static MADB db(){
                try {
                        return new MADB(FILEPATH);
                } catch (SQLException ex) {
                        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
        }
}
