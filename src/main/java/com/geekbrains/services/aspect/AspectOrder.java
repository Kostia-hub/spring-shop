package com.geekbrains.services.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class AspectOrder {

    @Pointcut("execution(public * com.geekbrains.services.OrderService.*(..))")
    public void callAspectOrderPublic() {
    }

    @Around("callAspectOrderPublic()")
    public Object aroundCallAt(ProceedingJoinPoint call) throws Throwable {
        //Instant start = Instant.now();
        long startTime = System.currentTimeMillis();
        try {
            return call.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("Total execution time: " + (endTime-startTime) + "ms. Метод: " + call.toShortString());
        }
    }
}
