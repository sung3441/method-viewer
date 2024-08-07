package fastcampus.innercircle.oss.methodviewer.aspects;

import fastcampus.innercircle.oss.methodviewer.printers.Print;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodViewerAspect {

    private final String baseDir;
    private final Print print;

    public MethodViewerAspect(String baseDir, Print print) {
        this.baseDir = baseDir;
        this.print = print;
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.SpeedViewer)")
    public Object handleSpeedViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        print.print("Method " + joinPoint.getSignature().getName() + " executed in " + (endTime - startTime) + " ms");
        return result;
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.ParameterViewer)")
    public Object handleParameterViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        print.print("Method " + joinPoint.getSignature().getName() + " called with parameters: ");
        for (Object arg : args) {
            print.print(" - " + arg);
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.ReturnValueViewer)")
    public Object handleReturnValueViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        print.print("Method " + joinPoint.getSignature().getName() + " returned: " + result);
        return result;
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.MethodViewer)")
    public Object handleMethodViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        // ParameterViewer 기능 처리
        Object[] args = joinPoint.getArgs();
        print.print("Method " + joinPoint.getSignature().getName() + " called with parameters: ");
        for (Object arg : args) {
            print.print(" - " + arg);
        }

        // SpeedViewer 기능 처리
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        print.print("Method " + joinPoint.getSignature().getName() + " executed in " + (endTime - startTime) + " ms");

        // ReturnValueViewer 기능 처리
        print.print("Method " + joinPoint.getSignature().getName() + " returned: " + result);

        return result;
    }
}
