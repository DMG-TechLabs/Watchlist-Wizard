package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.ConsoleHandler;
// import java.util.logging.Filter;
// import java.util.logging.LogRecord;
// import java.util.logging.StreamHandler;

public class Logs {
    private static boolean consoleLog = true;
    private static int max_lines = 10000;
    private static int max_files = 3;
    private static Level default_level = Level.FINE; 
    private static String default_file = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/")+ "/data/logs/logs";

    public static Logger createLoger(String logger_name){
        return createLoger(logger_name, default_level, default_file+"_"+logger_name);
    }

    public static Logger createLoger(String logger_name, Level level){
        return createLoger(logger_name, level, null);
    }

    public static Logger createLoger(String logger_name, Level level, String log_file){
        Logger logger = Logger.getLogger(logger_name);
        // try {
        //     LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
        // } catch (SecurityException | IOException e1) {
        //     // e1.printStackTrace();
        // }

        logger.setLevel(level);
        logger.setFilter(new MyFilter());
        if(consoleLog || log_file == null){
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(level);
            ch.setFormatter(new MyFormatter());
            logger.addHandler(ch);
        }

        if (log_file != null) {
            try {
                Handler fileHandler = new FileHandler(log_file, max_lines, max_files);
                fileHandler.setLevel(level);
                fileHandler.setFormatter(new MyFormatter());
                logger.addHandler(fileHandler);
            } catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
        }

        logger.log(level, logger_name+" logger created!");
        return logger;
    }
}

    
        // SEVERE (highest value)
        // WARNING
        // INFO
        // CONFIG
        // FINE
        // FINER
        // FINEST (lowest value)


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
        return new Date(record.getMillis()) + " | " 
                + "("
                + record.getLoggerName()
                + ") "
                + "| "
                + "("
                + record.getLevel()
                + ") "
                + record.getSourceClassName() 
                + " -> "
                + record.getSourceMethodName()
                + " >> "
                + record.getMessage() + "\n";
    }

}

final class MyFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord log) {
    //don't log CONFIG logs in file
    
    return true;
    }

}