package fastcampus.innercircle.oss.methodviewer.printers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class PrintLogback implements Print {
    private static final Logger logger = LoggerFactory.getLogger(PrintLogback.class);
    private final Level logLevel;

    PrintLogback(Level logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void print(String message) {
        switch (logLevel) {
            case ERROR:
                logger.error(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case TRACE:
                logger.trace(message);
                break;
            case INFO:
                logger.info(message);
                break;
            case DEBUG:
            default:
                logger.debug(message);
                break;
        }
    }
}