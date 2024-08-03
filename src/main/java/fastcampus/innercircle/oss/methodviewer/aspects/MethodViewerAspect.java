package fastcampus.innercircle.oss.methodviewer.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodViewerAspect {
    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.SpeedViewer)")
    public Object handleSpeedViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("[메소드 실행속도] " + (endTime - startTime) + " ms");
        return result;
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.ParameterViewer)")
    public Object handleParameterViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println("[메소드 파라미터] ");
        for (Object arg : args) {
            System.out.println(" - " + arg);
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.ReturnValueViewer)")
    public Object handleReturnValueViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        System.out.println("[메소드 반환 값] " + result);
        return result;
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.MethodViewer)")
    public Object handleMethodViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO
        return null;
    }
}
