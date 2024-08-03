package fastcampus.innercircle.oss.methodviewer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@SpeedViewer
@ReturnValueViewer
@ParameterViewer
public @interface MethodViewer {
}
