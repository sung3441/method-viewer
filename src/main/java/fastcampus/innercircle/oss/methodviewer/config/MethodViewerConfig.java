package fastcampus.innercircle.oss.methodviewer.config;

import fastcampus.innercircle.oss.methodviewer.aspects.MethodViewerAspect;
import fastcampus.innercircle.oss.methodviewer.printers.Print;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class MethodViewerConfig {

    // TODO baseDir에 해당하는 패키지 메서드만 aop 타게 하고 싶은데..
    @Value("${methodviewer.basedir:}")
    private String baseDir;

    @Value("${methodviewer.printer.type:systemout}")
    private String printerType;

    @Value("${methodviewer.printer.loglevel:INFO}")
    private String logLevel;

    @Bean
    public MethodViewerAspect methodViewerAspect(Print print) {
        String basePackage = StringUtils.hasText(baseDir) ? baseDir : getDefaultBaseDir();
        return new MethodViewerAspect(basePackage, print);
    }

    private String getDefaultBaseDir() {
        try {
            String applicationClassName = System.getProperty("sun.java.command");
            if (applicationClassName != null) {
                Class<?> applicationClass = Class.forName(applicationClassName.split(" ")[0]);
                return applicationClass.getPackage().getName();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
