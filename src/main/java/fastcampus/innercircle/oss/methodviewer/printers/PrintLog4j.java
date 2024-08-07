package fastcampus.innercircle.oss.methodviewer.printers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintLog4j implements Print {
    private static final Logger logger = LogManager.getLogger(PrintLog4j.class);
    private final Level logLevel;

    public PrintLog4j(Level logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void print(String message) {
        logger.log(logLevel, message);
    }
}
