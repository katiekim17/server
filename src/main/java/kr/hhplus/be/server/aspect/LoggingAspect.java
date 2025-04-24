package kr.hhplus.be.server.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* kr.hhplus.be.server..*Service.*(..))")
    public void logBefore(JoinPoint joinPoint) {
//        System.out.println(">> 서비스 시작: " + joinPoint.getSignature());
    }

    @AfterReturning("execution(* kr.hhplus.be.server..*Service.*(..))")
    public void logAfter() {
//        System.out.println("<< 서비스 끝");
    }

}
