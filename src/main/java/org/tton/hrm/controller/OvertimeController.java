package org.tton.hrm.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tton.hrm.domain.OvertimeProject;
import org.tton.hrm.domain.OvertimeRecord;
import org.tton.hrm.service.OvertimeService;
import org.tton.hrm.util.common.HrmConstants;
import org.tton.hrm.util.tag.PageModel;

/**
 * ClassName: OvertimeController <br/>
 * Description: TODO <br/>
 * Date: 2018年1月24日 下午6:47:13 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Controller
public class OvertimeController {

    private static final Log logger = org.apache.commons.logging.LogFactory.getLog(OvertimeController.class);
    @Autowired
    @Qualifier("overtimeService")
    private OvertimeService overtimeService;

    /**
     * 列表
     * @param pageIndex
     * @param model
     * @param overtimeProject
     * @return
     */
    @RequestMapping(value = "/overtime/selectOvertimeProject")
    public String SelectOvertimeProject(Integer pageIndex, Model model, @ModelAttribute OvertimeProject overtimeProject) {
        logger.info(HrmConstants.OVERTIMEINFO + DeptController.class.getName() + ".SelectOvertimeProject");
        PageModel pageModel = new PageModel();
        System.out.println("pageIndex: " + pageIndex);
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<OvertimeProject> overtimeProjects = overtimeService.findOvertimeProjects(overtimeProject, pageModel);
        model.addAttribute("overtimeProjects", overtimeProjects);
        model.addAttribute("pageModel", pageModel);
        System.out.println("pageModel: " + pageModel.getPageSize());

        return "overtime/overtimeProject";
    }

    /**
     * 更新延时加班项请求
     * @param mv
     * @param overtimeProject 要更新的加班项
     * @param flag 1表示显示要修改的加班项  2表示修改更新加班项
     * @return
     */
    @RequestMapping(value = "overtime/updateOvertimeProject")
    public ModelAndView updateOvertimeProject(ModelAndView mv, @ModelAttribute OvertimeProject overtimeProject,
            String flag) {
        logger.info(HrmConstants.OVERTIMEINFO + DeptController.class.getName() + ".updateOvertimeProject");
        if (flag.equals("1")) {
            // 根据id查询用户
            System.out.println("updateOvertimeProject: " + overtimeProject.getId());
            OvertimeProject target = overtimeService.findOvertimeProjectById(overtimeProject.getId());
            System.out.println("target.id: " + target.getId());
            // 设置Model数据
            mv.addObject("overtimeProject", target);
            // 返回修改员工页面
            mv.setViewName("overtime/showUpdateOvertimeProject");
        } else {
            // 执行修改操作
            overtimeService.modifyOvertimeProject(overtimeProject);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/overtime/selectOvertimeProject");
            // 返回

        }
        return mv;
    }

    /**
     * 删除延时加班项
     * @param ids 眼删除的id集合，字符串格式
     * @param mv
     * @return
     */
    @RequestMapping(value = "/overtime/removeOvertimeProject")
    public ModelAndView removeOvertimeProject(String ids, ModelAndView mv) {
        logger.info(HrmConstants.OVERTIMEINFO + DeptController.class.getName() + ".removeOvertimeProject");
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            List<OvertimeRecord> overtimeRecords = overtimeService.findOvertimeRecordsById(Integer.parseInt(id));
            for (OvertimeRecord overtimeRecord : overtimeRecords) {
                overtimeService.removeOvertimeRecordByProjectId(overtimeRecord.getOvertimeProject().getId());
            }
            overtimeService.removeOvertimeProject(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/overtime/selectOvertimeProject");
        return mv;
    }

    /**
     * 选择延时加班记录
     * @param pageIndex
     * @param id  选择的加班记录
     * @param overtimeRecord 
     * @param model
     * @return
     */
    @RequestMapping(value = "/overtime/selectOvertimeRecord")
    public String selectOvertimeRecord(Integer pageIndex, Integer id, @ModelAttribute OvertimeRecord overtimeRecord,
            Model model) {
        logger.info(HrmConstants.NOTICEINFO + DeptController.class.getName() + ".selectOvertimeRecord");
        PageModel pageModel = new PageModel();

        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<OvertimeRecord> overtimeRecords = overtimeService.findOvertimeRecordsById(id);
        model.addAttribute("overtimeRecords", overtimeRecords);
        model.addAttribute("projectId", id);
        model.addAttribute("pageModel", pageModel);
        return "overtime/overtimeRecord";
    }

    /**
     * 更新延时加班项
     * @param mv
     * @param flag
     * @param projectId
     * @param overtimeRecord
     * @return
     */
    @RequestMapping(value = "/overtime/updateOvertimeRecord")
    public ModelAndView updateOvertimeRecord(ModelAndView mv, String flag, String projectId,
            @ModelAttribute OvertimeRecord overtimeRecord) {
        logger.info(HrmConstants.NOTICEINFO + DeptController.class.getName() + ".updateOvertimeRecord");
        if (flag.equals("1")) {
            OvertimeRecord target = overtimeService.findOvertimeRecordById(overtimeRecord.getId());
            mv.addObject("overtimeRecord", target);
            mv.setViewName("overtime/showUpdateOvertimeRecord");
        } else {
            overtimeService.modifyOvertimeRecord(overtimeRecord);
            System.out.println("projectId: " + projectId);
            mv.addObject("id", projectId);
            mv.setViewName("redirect:/overtime/selectOvertimeRecord");
        }
        return mv;
    }

    /**
     * 添加延时加班项
     * @param mv
     * @param flag
     * @param overtimeProject
     * @return
     */
    @RequestMapping(value = "/overtime/addOvertimeProject")
    public ModelAndView addOvertimeProject(ModelAndView mv, String flag, @ModelAttribute OvertimeProject overtimeProject) {
        logger.info(HrmConstants.NOTICEINFO + DeptController.class.getName() + ".addOvertimeProject");
        if (flag.equals("1")) {
            mv.setViewName("overtime/showAddOvertimeProject");
        } else {
            overtimeService.addOvertimeProject(overtimeProject);
            mv.setViewName("redirect:/overtime/selectOvertimeProject");
        }
        return mv;
    }

    /**
     * 添加延时加班记录
     * @param mv
     * @param flag
     * @param id
     * @param overtimeRecord
     * @return
     */
    @RequestMapping(value = "/overtime/addOvertimeRecord")
    public ModelAndView addOvertimeRecord(ModelAndView mv, String flag,String id, @ModelAttribute OvertimeRecord overtimeRecord) {
        logger.info(HrmConstants.OVERTIMEINFO + DeptController.class.getName() + ".addOvertimeRecord");
        if (flag.equals("1")) {
            mv.addObject("projectId", id);
            mv.setViewName("overtime/showAddOvertimeRecord");
        } else {
            System.out.println("xxxxxxxxxxx");
            OvertimeProject overtimeProject = new OvertimeProject();
            overtimeProject.setId(Integer.parseInt(id));
            overtimeRecord.setOvertimeProject(overtimeProject);
            overtimeService.addOvertimeRecord(overtimeRecord);
            System.out.println("id: "+id);
            mv.addObject("id", id);
            mv.setViewName("redirect:/overtime/selectOvertimeRecord");
        }
        return mv;
    }

}
