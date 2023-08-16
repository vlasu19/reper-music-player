package com.xxnan.reper.aspect;


import com.xxnan.reper.annotation.AutoFill;
import com.xxnan.reper.common.context.BaseContext;
import com.xxnan.reper.common.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class AutoFillAspect {
    @Pointcut("execution(* com.xxnan.reper.mapper.*.*(..)) && @annotation(com.xxnan.reper.annotation.AutoFill)")
    public void autoFillPointCut(){}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始进行公共字段自动填充");
        //获取数据库操作类型
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autoFill=signature.getMethod().getAnnotation(AutoFill.class);//获取方法上的注解对象
        OperationType operationType=autoFill.value();

        //获取拦截到方法的参数,获得想要的实体对象，约定mapper里把实体放第一个参数
        Object[] args= joinPoint.getArgs();
        if(args==null||args.length==0){return;}
        Object entity=args[0];

        LocalDateTime now=LocalDateTime.now();
        Integer id= BaseContext.getCurrentId();

        if(operationType==OperationType.INSERT){
            try {
                Method sct= entity.getClass().getDeclaredMethod("setCreateTime",LocalDateTime.class);
//                Method scu= entity.getClass().getDeclaredMethod("setCreateUser",Long.class);
                Method sut= entity.getClass().getDeclaredMethod("setUpdateTime",LocalDateTime.class);
//                Method suu= entity.getClass().getDeclaredMethod("setUpdateUser",Long.class);

                //通过反射为对象属性赋值
                sct.invoke(entity,now);
//                scu.invoke(entity,id);
                sut.invoke(entity,now);
//                suu.invoke(entity,id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operationType==OperationType.UPDATE){

            try {
                Method sut= entity.getClass().getDeclaredMethod("setUpdateTime",LocalDateTime.class);
//                Method suu= entity.getClass().getDeclaredMethod("setUpdateUser",Long.class);

                sut.invoke(entity,now);
//                suu.invoke(entity,id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
