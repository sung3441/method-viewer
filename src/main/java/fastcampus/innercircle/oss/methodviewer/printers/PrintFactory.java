package fastcampus.innercircle.oss.methodviewer.printers;

import org.apache.logging.log4j.Level;

public class PrintFactory {
    public static Print create(String printerType, String logLevel) {
        return switch (printerType.toLowerCase()) {
            case "log4j" -> new PrintLog4j(Level.toLevel(logLevel));
            case "logback" -> new PrintLogback(org.slf4j.event.Level.valueOf(logLevel));
            default -> new PrintSystemOut();
        };
    }
}
