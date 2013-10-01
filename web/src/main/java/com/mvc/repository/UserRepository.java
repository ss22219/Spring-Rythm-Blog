package com.mvc.repository;

import com.mvc.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepository extends BaseRepository<User> {

    public User getUser(String userName, String password) {
        List<User> list = super.getSession().createQuery("from User where userName=? and password=?")
                .setParameter(0, userName)
                .setParameter(1, password).list();
        return list.size() == 1 ? list.get(0) : null;
    }

    public boolean checkUserName(String userName) {
        List<User> list = super.getSession().createQuery("from User where userName=?")
                .setParameter(0, userName).list();
        return list.size() > 0 ? false : true;
    }

    public boolean checkEmail(String email) {
        List<User> list = super.getSession().createQuery("from User where email=?")
                .setParameter(0, email).list();
        return list.size() > 0 ? false : true;
    }
}
