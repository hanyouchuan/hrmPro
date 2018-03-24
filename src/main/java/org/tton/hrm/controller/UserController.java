package org.tton.hrm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tton.hrm.domain.User;
import org.tton.hrm.service.UserService;
import org.tton.hrm.util.common.HrmConstants;
import org.tton.hrm.util.tag.PageModel;

 /**
 * ClassName: UserController <br/>
 * Description: TODO <br/>
 * Date: 2018年1月29日 下午2:42:02 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Controller
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);
    /**
     * 自动注入UserService
     * */
    @Autowired
    @Qualifier("userService")
    private UserService userService;
        
    @RequestMapping(value="/{formname}")
    public String loginForm(@PathVariable String formname){
        logger.info(HrmConstants.USERINFO + DeptController.class.getName() + ".loginForm");
        System.out.println("===="+formname+"====");
        return formname;
    }
    
    /**
     * 处理登录请求
     * @param String loginname  登录名
     * @param String password 密码
     * @return 跳转的视图
     * */
    @RequestMapping(value="/login")
     public ModelAndView login(@RequestParam("loginname") String loginname,
             @RequestParam("password") String password,
             HttpSession session,
             ModelAndView mv){
        logger.info(HrmConstants.USERINFO + DeptController.class.getName() + ".login");
        // 调用业务逻辑组件判断用户是否可以登录
        User user = userService.login(loginname, password);
        if(user != null){
            // 将用户保存到HttpSession当中
            session.setAttribute(HrmConstants.USER_SESSION, user);
            // 客户端跳转到main页面
            mv.setViewName("redirect:/main");
        }else{
            // 设置登录失败提示信息
            mv.addObject("message", "登录名或密码错误!请重新输入");
            // 服务器内部跳转到登录页面
            mv.setViewName("forward:/loginForm");
        }
        return mv;
        
    }
    
    /**
     * 处理查询请求
     * @param pageIndex 请求的是第几页
     * @param employee 模糊查询参数
     * @param Model model
     * */
    @RequestMapping(value="/user/selectUser")
     public String selectUser(Integer pageIndex,
             @ModelAttribute User user,
             Model model){
        logger.info(HrmConstants.USERINFO + DeptController.class.getName() + ".selectUser");
        System.out.println("user = " + user);
        System.out.println("pageIndex:"+pageIndex);
        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        /** 查询用户信息     */
        List<User> users = userService.findUser(user, pageModel);
        model.addAttribute("users", users);
        model.addAttribute("pageModel", pageModel);
        return "user/user";
        
    }
    
    /**
     * 处理删除用户请求
     * @param String ids 需要删除的id字符串
     * @param ModelAndView mv
     * */
    @RequestMapping(value="/user/removeUser")
     public ModelAndView removeUser(String ids,ModelAndView mv){
        logger.info(HrmConstants.USERINFO + DeptController.class.getName() + ".removeUser");
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除员工
            userService.removeuserById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/user/selectUser");
        // 返回ModelAndView
        return mv;
    }
    
    
    /**
     * 处理修改用户请求
     * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param User user  要修改用户的对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value="/user/updateUser")
     public ModelAndView updateUser(
             String flag,
             @ModelAttribute User user,
             ModelAndView mv){
        logger.info(HrmConstants.USERINFO + DeptController.class.getName() + ".updateUser");
        if(flag.equals("1")){
            // 根据id查询用户
            User target = userService.findUserById(user.getId());
            // 设置Model数据
            mv.addObject("user", target);
            // 返回修改员工页面
            mv.setViewName("user/showUpdateUser");
        }else{
            // 执行修改操作
            userService.modifyUser(user);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }
        // 返回
        return mv;
    }
    
    
    /**
     * 处理添加请求
     * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
     * @param User user  要添加用户的对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value="/user/addUser")
     public ModelAndView addUser(
             String flag,
             @ModelAttribute User user,
             ModelAndView mv){
        logger.info(HrmConstants.USERINFO + DeptController.class.getName() + ".addUser");
        if(flag.equals("1")){
            // 设置跳转到添加页面
            mv.setViewName("user/showAddUser");
        }else{
            System.out.println(user);
            // 执行添加操作
            userService.addUser(user);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }
        // 返回
        return mv;
    }
}
