package com.mvc.interceptor;

import com.mvc.domain.DomainType;
import com.mvc.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SecurityInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (request.getRequestURI().indexOf("/admin") != -1) {
            if (request.getSession().getAttribute("user") == null || ((User) request.getSession().getAttribute("user")).getRole() != DomainType.Admin) {
                response.sendError(404);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        try {
            if (modelAndView != null) {

                if (request.getSession().getAttribute("user") != null) {
                    modelAndView.addObject("user", request.getSession().getAttribute("user"));
                    modelAndView.addObject("isLogin", true);
                    if (((User) request.getSession().getAttribute("user")).getRole() == DomainType.Admin) {
                        modelAndView.addObject("isAdmin", true);
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger("securityError").log(Level.WARNING, "/*****加载用户信息出错*****/");
            Logger.getLogger("loadSetting").log(Level.WARNING, e.getMessage());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    }
}
