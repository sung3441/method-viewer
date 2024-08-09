package fastcampus.innercircle.oss.methodviewer.aspects;

import fastcampus.innercircle.oss.methodviewer.printers.Print;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MethodViewerAspect {

    private final String baseDir;
    private final Print print;

    MethodViewerAspect(String baseDir, Print print) {
        this.baseDir = baseDir;
        this.print = print;
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.SpeedViewer)")
    public Object handleSpeedViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        print("메소드명[" + joinPoint.getSignature().getName() + "], 실행시간[" + ((endTime - startTime)/ (double) 1_000) + "]초");
        return result;
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.ParameterViewer)")
    public Object handleParameterViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        sb.append("메소드명[").append(joinPoint.getSignature().getName()).append("], 파라미터: [");
        for (Object arg : args) {
            sb.append(" - ").append(arg);
        }
        sb.append("]");
        print(sb.toString());
        return joinPoint.proceed();
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.ReturnValueViewer)")
    public Object handleReturnValueViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        print("메소드명[" + joinPoint.getSignature().getName() + "], 반환 값[" + result + "]");
        return result;
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.StackTraceViewer)")
    public Object handleStackTraceViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        sb.append("메소드명[").append(joinPoint.getSignature().getName()).append("], 스택목록[\n");
        for (StackTraceElement element : stackTrace) {
            sb.append(" - ").append(element.toString()).append("\n");
        }
        sb.append("]");
        print(sb.toString());
        return joinPoint.proceed();
    }

    @Around("@annotation(fastcampus.innercircle.oss.methodviewer.annotations.MethodViewer)")
    public Object handleMethodViewer(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        sb.append("메소드명[").append(joinPoint.getSignature().getName()).append("]\n파라미터: [\n");
        for (Object arg : args) {
            sb.append(" - ").append(arg).append("\n");
        }
        sb.append("]\n");
        sb.append("스택목록[\n");
        for (StackTraceElement element : stackTrace) {
            sb.append(" - ").append(element.toString()).append("\n");
        }
        sb.append("]");
        print(sb.toString());
        sb.setLength(0);
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        sb.append("실행시간[").append((endTime - startTime) / (double) 1_000).append("]초\n");
        sb.append("반환 값[").append(result).append("]\n");
        print(sb.toString());
        return result;
    }

    public void print(String message) {
        print.print("\n" + message);
    }
}
