package calender.calender.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogAdvice {

    @Before("execution(* calender.calender.service.CommentService.write(Long, Long, String)) && args(a, b, c)")
    public void logBefore(Long a, Long b, String c) {

        log.info(a + "ccxxzcx" + b + "saaa"+  c);
    }
}
