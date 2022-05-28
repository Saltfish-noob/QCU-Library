package edu.qcu.controller;

import edu.qcu.domain.SysLog;
import edu.qcu.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private final HttpServletRequest request;

    @Autowired
    public LogAop(HttpServletRequest request){
        this.request=request;
    }

    private ISysLogService sysLogService;

    @Autowired
    public void setSysLogService(ISysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    //开始时间
    private Date visitTime;
    //执行的类
    private Class clazz;
    //访问的方法
    private Method method;

    @Before("execution(* edu.qcu.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        //获取当前时间，即开始访问的时间
        visitTime = new Date();
        //具体要访问的类
        clazz = jp.getTarget().getClass();
        //获取访问方法的名称
        String methodName = jp.getSignature().getName();
        //获取参数列表
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0){
            //只能获取无参方法
            method = clazz.getMethod(methodName);
        }
        else{
            //获取有参方法
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);
        }
    }

    @After("execution(* edu.qcu.controller.*.*(..))")
    public void doAfter(JoinPoint jp)throws Exception{
        //计算访问时长
        Long time = new Date().getTime()-visitTime.getTime();
        String url="";
        //获取url
        if (clazz!=null&&method!=null&&clazz!= LogAop.class){
            //获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation =(RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String[]  classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url=classValue[0]+methodValue[0];
                }
            }
        }
        //获取访问的IP
        String ip=request.getRemoteAddr();

        //不对总览进行记录
        if (!method.getName().equals("findAll")){
            //获取当前操作的用户
            SecurityContext context= SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
            User user =(User) context.getAuthentication().getPrincipal();
            String username = user.getUsername();

            //将日志相关信息封装入SysLog对象
            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime(time);//执行时长
            sysLog.setIp(ip);
            sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
            sysLog.setUrl(url);
            sysLog.setUsername(username);
            sysLog.setVisitTime(visitTime);

            //调用Service完成操作
            sysLogService.save(sysLog);
        }
        else {
            System.gc();
        }
    }
}
