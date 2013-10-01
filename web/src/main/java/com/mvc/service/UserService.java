package com.mvc.service;

import com.mvc.exception.ServiceException;
import com.mvc.model.User;
import com.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SettingService settingService;

    public User login(String userName, String password) {
        return userRepository.getUser(userName, password);
    }

    public boolean checkUserName(String userName) {
        return userRepository.checkUserName(userName);
    }

    public boolean checkEmail(String email) {
        return userRepository.checkEmail(email);
    }

    public void register(User user) throws ServiceException {
        if (!settingService.getSetting("allRegister").equals("1")) {
            throw new ServiceException("注册功能已关闭，具体请联系管理员。");
        } else if (!checkEmail(user.getEmail())) {
            throw new ServiceException("该邮箱已经被注册。");
        } else if (!checkUserName(user.getUserName())) {
            throw new ServiceException("该用户名已经被注册。");
        } else {
            int defaultRole = Integer.parseInt(settingService.getSetting("defaultRole"));
            int defaultStatus = Integer.parseInt(settingService.getSetting("defaultStatus"));
            user.setRegisterDate(new Date());
            userRepository.save(user);
        }
    }

    public String lostPassword(String userName) throws ServiceException {
        if(!checkEmail(userName) || !checkUserName(userName)){
            throw new ServiceException("真抱歉，我们的Mail服务器崩溃了，你可以联系管理员找回密码。");
        }
        else{
            throw new ServiceException("找不到该用户或邮箱。");
        }
    }
}
