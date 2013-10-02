package com.mvc.interceptor;

import com.mvc.service.ArticleService;
import com.mvc.service.CategoryService;
import com.mvc.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingLoadInterceptor implements HandlerInterceptor {
    @Autowired
    private SettingService settingService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        try {
            if (modelAndView != null) {
                Map<String, String> map = settingService.getSettings();
                for (String key : map.keySet()) {
                    modelAndView.addObject(key, map.get(key));
                }

                if (request.getRequestURI().indexOf("/admin/") == -1) {
                    modelAndView.addObject("lastArticle", articleService.getLastArticle());
                    modelAndView.addObject("monthCategory", categoryService.getMonthCategory());
                    modelAndView.addObject("categories", categoryService.getCategorys());

                }
            }
        } catch (Exception e) {
            Logger.getLogger("loadSetting").log(Level.WARNING, "/*****加载设置时发生错误*****/");
            Logger.getLogger("loadSetting").log(Level.WARNING, e.getMessage());
            throw e;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
