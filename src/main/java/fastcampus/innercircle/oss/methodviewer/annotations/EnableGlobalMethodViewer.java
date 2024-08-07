package fastcampus.innercircle.oss.methodviewer.annotations;

import fastcampus.innercircle.oss.methodviewer.config.MethodViewerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MethodViewerConfig.class)
public @interface EnableGlobalMethodViewer {
    String baseDir() default "";
}