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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tton.hrm.domain.Notice;
import org.tton.hrm.domain.User;
import org.tton.hrm.service.NoticeService;
import org.tton.hrm.util.common.HrmConstants;
import org.tton.hrm.util.tag.PageModel;

/**
 * ClassName: NoticeController <br/>
 * Description: TODO <br/>
 * Date: 2018年1月23日 下午1:06:05 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Controller
public class NoticeController {

    private static final Log logger = LogFactory.getLog(NoticeController.class);
    @Autowired
    @Qualifier("noticeService")
    NoticeService noticeService;

    /**
     * 公告列表
     * @param model
     * @param pageIndex
     * @param notice
     * @return
     */
    @RequestMapping(value = "/notice/selectNotice")
    public String selectNotice(Model model, Integer pageIndex, @ModelAttribute Notice notice) {
        logger.info(HrmConstants.NOTICEINFO + DeptController.class.getName() + ".selectNotice");
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        List<Notice> notices = noticeService.findNotice(notice, pageModel);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("notices", notices);

        return "/notice/notice";
    }

    /**
     * 预览公告
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "notice/previewNotice")
    public String previewNotice(Model model, Integer id) {
        logger.info(HrmConstants.NOTICEINFO + DeptController.class.getName() + ".previewNotice");
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice", notice);
        return "notice/previewNotice";
    }

    /**
     * 处理删除公告请求
     * @param String ids 需要删除的id字符串
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "/notice/removeNotice")
    public ModelAndView removeNotice(String ids, ModelAndView mv) {
        logger.info(HrmConstants.NOTICEINFO + DeptController.class.getName() + ".removeNotice");

        String[] idarray = ids.split(",");
        for (String id : idarray) {
            noticeService.removeNoticeById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/notice/selectNotice");
        return mv;
    }

    /**
     * 处理添加请求
     * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
     * @param Notice notice  要添加的公告对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "/notice/addNotice")
    public ModelAndView addNotice(String flag, Notice notice, ModelAndView mv, HttpSession session) {
        logger.info(HrmConstants.NOTICEINFO + DeptController.class.getName() + ".addNotice");
        System.out.println("----" + flag + "---");
        if (flag.equals("1")) {
            mv.setViewName("notice/showAddNotice");
        } else {
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            notice.setUser(user);
            noticeService.addNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        return mv;
    }

    /**
     * 处理修改请求
     * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param Notice notice  要修改的公告对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "/notice/updateNotice")
    public ModelAndView updateNotice(String flag, Notice notice, ModelAndView mv) {
        logger.info(HrmConstants.NOTICEINFO + DeptController.class.getName() + ".updateNotice");
        if (flag.equals("1")) {
            Notice target = noticeService.findNoticeById(notice.getId());
            mv.addObject("notice", target);
            mv.setViewName("notice/showUpdateNotice");
        } else {
            noticeService.modifyNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }

        return mv;
    }

}
