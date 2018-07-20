package cn.vision.invicloud.web.common.aspect;


import cn.vision.invicloud.support.entity.Log;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.service.ILogService;
import cn.vision.invicloud.web.common.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

@Aspect
@Component
public class SystemLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Autowired
    private ILogService logService;

    @Pointcut("execution(* cn.vision.invicloud.web.controller..*.*(..))")
    public void controllerAspect() {
    }


    @Around("controllerAspect()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String strMethodName = joinPoint.getSignature().getName();
        String strClassName = joinPoint.getTarget().getClass().getName();
        Object[] params = joinPoint.getArgs();
        StringBuffer bfParams = new StringBuffer();
        Enumeration<String> paraNames = null;
        HttpServletRequest request = null;
        if (params != null && params.length > 0) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            paraNames = request.getParameterNames();
            String key;
            String value;
            while (paraNames.hasMoreElements()) {
                key = paraNames.nextElement();
                value = request.getParameter(key);
                bfParams.append(key).append("=").append(value).append("&");
            }
            if (StringUtils.isBlank(bfParams)) {
                bfParams.append(request.getQueryString());
            }
        }
        if(bfParams.length()>3000)bfParams=new StringBuffer("faceimg");

        String strMessage = String.format("[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName, bfParams.toString());
        logger.info(strMessage);

        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.debug("doAround>>>result={},耗时：{}", result, endTime - startTime);

        Subject currentUser = SecurityUtils.getSubject();
        User user = (User) currentUser.getPrincipal();
        if (null != user) {
            Log sysLog = new Log();
            sysLog.setCreateTime(new Date());
            sysLog.setUserId(user.getUserId());
            sysLog.setOptContent(strMessage);
            sysLog.setUserIp(ServletUtils.getIpAddress(request));
            sysLog.setUrl(request.getRequestURI());
            sysLog.setMethod(request.getMethod());
            sysLog.setUserAgent(request.getHeader("User-Agent"));
            sysLog.setSpendTime((int) (endTime - startTime));
            logService.insert(sysLog);
        }

        return result;
    }

}
