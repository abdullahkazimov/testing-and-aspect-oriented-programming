package abdkzmv.wm2.assignment3.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
@Aspect
public class MovieControllerAspect {
    @Pointcut("execution(* abdkzmv.wm2.assignment3.service.MovieService.*(..))")
    private void methodPointCut() {
    }

    @Before("methodPointCut()")
    public void before(JoinPoint jp) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        log.info("The method {} has arguments {}", method, args);
    }

    @AfterReturning(value = "methodPointCut()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        String method = jp.getSignature().getName();
        log.info("Exiting method {} with result {}", method, result);
    }

    @SneakyThrows
    @Around("methodPointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        StopWatch watch = new StopWatch();
        watch.start();
        var result = pjp.proceed();
        watch.stop();
        log.info("method {} took {} millis to execute ", pjp.getSignature(), watch.getTotalTimeMillis());
        return result;
    }
}
