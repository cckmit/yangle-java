package com.ater.common.aspect;

import com.ater.common.annotation.SysLog;
import com.ater.common.utils.HttpContextUtils;
import com.ater.common.utils.IPUtils;
import com.ater.modules.entity.SysLogEntity;
import com.ater.modules.entity.SysUserEntity;
import com.ater.modules.entity.User;
import com.ater.modules.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 *
 * @author
 * @create 2017-04-01
 **/
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.ater.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperationContent(syslog.value());
        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setUserIp(IPUtils.getIpAddr(request));
        //用户名
        Object obj = SecurityUtils.getSubject().getPrincipal();
        String username;
        if(obj!=null){
            username = ((User) SecurityUtils.getSubject().getPrincipal()).getUserName();
        }else
            username = "管理员";
        sysLog.setUserName(username);
        sysLog.setExecutePhase(time);
        sysLog.setOperationTime(new Date());
        //保存系统日志
        sysLogService.save(sysLog);
    }
}
