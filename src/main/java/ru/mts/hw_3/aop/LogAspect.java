package ru.mts.hw_3.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.annotations.Logging;

import java.util.Optional;

@Aspect
@Slf4j
@Component
public class LogAspect {
    private static final String ENTER = ">> {}";
    private static final String EXIT = "<< {}";

    @Around("@annotation(logging) && execution(public * ru.mts.hw_3.service..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        String annotationValue = fetchAnnotationValue(joinPoint, logging);
        Logger logger = LogManager.getLogger(fetchJoinPointObject(joinPoint));

        logger.info(ENTER, annotationValue);

        Object result = joinPoint.proceed();

        logger.info(EXIT, annotationValue);

        return result;
    }

    private String fetchAnnotationValue(ProceedingJoinPoint joinPoint, Logging logging) {
        return Optional.ofNullable(logging)
                .map(Logging::value)
                .filter(StringUtils::isNotBlank)
                .orElse(joinPoint.getSignature().getName());
    }

    private Object fetchJoinPointObject(ProceedingJoinPoint joinPoint) {
        return Optional.of(joinPoint)
                .map(JoinPoint::getTarget)
                .orElseGet(joinPoint::getThis);
    }
}
