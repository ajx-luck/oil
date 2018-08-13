package com.breast.oil.aspect;

import com.breast.oil.services.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthorizeAspect {
    @Autowired
    UserService mUserService;

    @Pointcut("execution(public * com.breast.oil.web.WxToolsController.*.*(..))" )
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String tonken = request.getParameter("tonken");
        String device = request.getParameter("device");
        if("fail".equals(mUserService.checkUserByDevice(device))){
            throw new RuntimeException("授权失败");
        }

    }
}
