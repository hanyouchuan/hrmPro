package org.tton.hrm.service;

import java.util.List;

import org.tton.hrm.domain.User;
import org.tton.hrm.util.tag.PageModel;


public interface UserService {

    User login(String loginname, String password);

    User findUserById(Integer id);

    List<User> findUser(User user, PageModel pageModel);

    void removeuserById(Integer id);

    void modifyUser(User user);

    void addUser(User user);

}
