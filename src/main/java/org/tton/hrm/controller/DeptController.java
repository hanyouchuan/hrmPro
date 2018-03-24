package org.tton.hrm.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tton.hrm.domain.Dept;
import org.tton.hrm.service.DeptService;
import org.tton.hrm.util.common.HrmConstants;
import org.tton.hrm.util.tag.PageModel;

/**
 * 
 * ClassName: DeptController <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午9:45:33 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 *
 */
@Controller
public class DeptController {

    private static final Log logger = LogFactory.getLog(DeptController.class);
    @Autowired
    @Qualifier("deptService")
    private DeptService deptService;

    /**
     * 部门列表
     * @param model 
     * @param pageIndex
     * @param dept
     * @return
     *            TODO Description <br/>
     */
    @RequestMapping(value = "/dept/selectDept")
    public String selectDept(Model model, Integer pageIndex, @ModelAttribute Dept dept) {
        logger.info(HrmConstants.DEPTINFO + DeptController.class.getName() + ".selectDept");
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Dept> depts = deptService.findDept(dept, pageModel);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);

        return "dept/dept";
    }

    /**
     * 删除部门信息
     * @param ids 要删除的部门id字符串
     * @param mv  
     * @return
     */
    @RequestMapping(value = "/dept/removeDept")
    public ModelAndView removeDept(String ids, ModelAndView mv) {
        logger.info(HrmConstants.DEPTINFO + DeptController.class.getName() + ".removeDept");
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            deptService.removeDeptById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/dept/selectDept");
        return mv;

    }

    /**
     * 更新部门信息
     * @param flag 标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param mv 
     * @param dept 要修改部门的对象
     * @return
     */
    @RequestMapping(value = "/dept/updateDept")
    public ModelAndView updateDept(String flag, ModelAndView mv, @ModelAttribute Dept dept) {
        logger.info(HrmConstants.DEPTINFO + DeptController.class.getName() + ".updateDept");
        if (flag.equals("1")) {
            Dept target = deptService.findDeptById(dept.getId());
            mv.addObject("target", target);
            mv.setViewName("dept/showUpdateDept");
        } else {
            deptService.modifyDept(dept);
            mv.setViewName("redirect:/dept/selectDept");
        }
        return mv;
    }

    /**
     *添加部门
     * @param flag 标记， 1表示跳转到添加页面，2表示执行添加操作
     * @param dept 要添加的部门对象
     * @param mv
     * @return
     */
    @RequestMapping(value = "dept/addDept")
    public ModelAndView addDept(String flag, @ModelAttribute Dept dept, ModelAndView mv) {
        logger.info(HrmConstants.DEPTINFO + DeptController.class.getName() + ".addDept");
        if (flag.equals("1")) {
            mv.setViewName("dept/showAddDept");
        } else {
            deptService.addDept(dept);
            mv.setViewName("redirect:/dept/selectDept");
        }
        return mv;

    }

}
