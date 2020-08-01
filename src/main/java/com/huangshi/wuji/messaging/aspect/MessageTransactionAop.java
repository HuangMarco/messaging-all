package com.huangshi.wuji.messaging.aspect;

import com.huangshi.wuji.messaging.annotation.MessageLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class MessageTransactionAop {

    @Pointcut(value = "@annotation(messageLog)", argNames = "messageLog")
    public void messageLogPointCut(MessageLog messageLog){
        System.out.println("连接点中messageLog:::::"+messageLog);
    }

    /***
     * 打印标记了annotation:@MessageLog的方法中传入的参数
     * @param joinPoint
     * @param messageLog
     * @return
     * @throws Throwable
     */
    @Around(value = "messageLogPointCut(messageLog)", argNames = "joinPoint,messageLog")
    public Object around(ProceedingJoinPoint joinPoint, MessageLog messageLog) throws Throwable {
        try {
            System.out.println(messageLog.value());
            System.out.println("环绕式通知中joinPoint:::" + joinPoint.toString());
            //环绕式切面中打印标记了该annotation的方法中传入的参数！！！！
            System.out.println("发送的消息为:::" + Arrays.toString(joinPoint.getArgs()));
            System.out.println("around");
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            System.out.println("around");
        }
    }

    /**
     * 该方法是拦截注解的，只能获取注解中配置参数的内容
     * @param joinPoint
     */
    @Before(value = "@annotation(com.huangshi.wuji.messaging.annotation.MessageLog)")
    public void beforeMessageLog(JoinPoint joinPoint){
        System.out.println("发送的消息为:::" + joinPoint.toString());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MessageLog log = method.getAnnotation(MessageLog.class);
        System.out.println("注解式拦截 " + log.value());

    }


    @Before(value = "execution(* com.huangshi.wuji.messaging.rabbitmq.official.producer..*(..)) and args(message)")
    public void beforeMessageSending(JoinPoint joinPoint, String message){
        System.out.println("Before messageSending:" + joinPoint.getSignature());
        System.out.println("发送的消息内容为:" + message);
    }

}
