package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class Logs {

    // private Logger logger = Logger.getLogger(Logs.class.getName());
    public Logger logger = null;

    public Logs(String logger_name, String log_file, int max_lines, int max_files) {
        logger = Logger.getLogger(logger_name);
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }

        logger.setLevel(Level.FINER);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.FINER);
        logger.addHandler(ch);
        // adding custom handler
        // logger.addHandler(new MyHandler());

        if (log_file != null) {
            try {
                // FileHandler file name with max size and number of log files limit
                Handler fileHandler = new FileHandler(log_file, max_lines, max_files);
                fileHandler.setFormatter(new MyFormatter());
                // setting custom filter for FileHandler
                // fileHandler.setFilter(new MyFilter());
                logger.addHandler(fileHandler);

            } catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Logs(String logger_name) {
        logger = Logger.getLogger(logger_name);
        // try {
        //     LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
        // } catch (SecurityException | IOException e1) {
        //     e1.printStackTrace();
        // }
        logger.addHandler(new ConsoleHandler());
    }

    public void logging(String log_level, String message){
        switch (message) {
            case "error":
                this.logger.log(Level.SEVERE, message);
                break;
            case "warning":
                this.logger.log(Level.WARNING, message);
                break;
            case "info":
                this.logger.log(Level.INFO, message);
                break;
            case "config":
                this.logger.log(Level.CONFIG, message);
                break;
            case "trace":
                this.logger.log(Level.FINE, message);
                break;
            default:
                this.logger.log(Level.FINEST, message);
                break;
        }
    }

    public static void logging(Logger logger, String log_level, String message) {

        // SEVERE (highest value)
        // WARNING
        // INFO
        // CONFIG
        // FINE
        // FINER
        // FINEST (lowest value)
        switch (message) {
            case "error":
                logger.log(Level.SEVERE, message);
                break;
            case "warning":
                logger.log(Level.WARNING, message);
                break;
            case "info":
                logger.log(Level.INFO, message);
                break;
            case "config":
                logger.log(Level.CONFIG, message);
                break;
            case "trace":
                logger.log(Level.FINE, message);
                break;
            default:
                logger.log(Level.FINEST, message);
                break;
        }
    }
}

// final class MyHandler extends StreamHandler {

// @Override
// public void publish(LogRecord record) {
// //add own logic to publish
// super.publish(record);
// }

// @Override
// public void flush() {
// super.flush();
// }

// @Override
// public void close() throws SecurityException {
// super.close();
// }

// }

final class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return new Date(record.getMillis()) + " | " + record.getSourceClassName() + " -> "
                + record.getSourceMethodName()
                + " >> "
                + record.getMessage() + "\n";
    }

}

// final class MyFilter implements Filter {

// @Override
// public boolean isLoggable(LogRecord log) {
// //don't log CONFIG logs in file
// if(log.getLevel() == Level.CONFIG) return false;
// return true;
// }

// }