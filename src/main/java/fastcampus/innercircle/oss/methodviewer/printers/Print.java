package fastcampus.innercircle.oss.methodviewer.printers;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public interface Print {
    void print(String message);

    @Configuration
    class PrintFactory {

        @Value("${methodviewer.printer.type:systemout}")
        private String printerType;

        @Value("${methodviewer.printer.loglevel:DEBUG}")
        private String logLevel;

        @Bean
        public Print createPrint() {
            return switch (printerType.toLowerCase()) {
                case "log4j" -> new PrintLog4j(Level.toLevel(logLevel));
                case "logback" -> new PrintLogback(org.slf4j.event.Level.valueOf(logLevel));
                default -> new PrintSystemOut();
            };
        }
    }
}
