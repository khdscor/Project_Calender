package calender.calender.aop;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogAdvice {

    @Before("execution(* calender.calender.service.CommentService.*(..))")
    public void logBefore() {

        log.info("======================");
    }

    @Around("execution(* calender.calender.service.CommentService.*(..)))")
    public Object logTime(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        log.info("======================");
        log.info("Target: " + pjp.getTarget());
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        log.info("TIME: " + (end - start));

        return result;
    }
}
