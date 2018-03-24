package org.tton.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tton.hrm.dao.UserDao;
import org.tton.hrm.domain.User;
import org.tton.hrm.service.UserService;
import org.tton.hrm.util.tag.PageModel;

 /**
 * ClassName: UserServiceImpl <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:49:18 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.UserService#login(java.lang.String,
     *      java.lang.String) <br/>
     */
    @Transactional(readOnly = true)
    public User login(String loginname, String password) {
        System.out.println("UserServiceImpl login-->>");
        return userDao.selectByLoginnameAndPassword(loginname, password);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.UserService#findUserById(java.lang.Integer) <br/>
     */
    @Cacheable(value="findUserById")
    @Transactional(readOnly = true)
    public User findUserById(Integer id) {
        System.out.println("UserServiceImpl findUserById-->>");
        return userDao.selectById(id);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.UserService#findUser(org.tton.hrm.domain.User,
     *      org.tton.hrm.util.tag.PageModel) <br/>
     */
    @Cacheable(value="findUser")
    @Transactional(readOnly = true)
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        int recordCount = userDao.count(params);
        System.out.println("recordCount->>" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        List<User> users = userDao.selectByPage(params);
        return users;
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.UserService#removeuserById(java.lang.Integer) <br/>
     */
    @CacheEvict({"findUserById","findUser"})
    public void removeuserById(Integer id) {
        // TODO Auto-generated method stub
        userDao.deleteById(id);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.UserService#modifyUser(org.tton.hrm.domain.User) <br/>
     */
    @CacheEvict({"findUserById","findUser"})
    public void modifyUser(User user) {
        // TODO Auto-generated method stub
        userDao.update(user);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.UserService#addUser(org.tton.hrm.domain.User) <br/>
     */
    @CacheEvict({"findUserById","findUser"})
    public void addUser(User user) {
        // TODO Auto-generated method stub
        userDao.save(user);
    }

}
