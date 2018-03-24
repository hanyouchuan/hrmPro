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
import org.tton.hrm.domain.Job;
import org.tton.hrm.service.JobService;
import org.tton.hrm.util.common.HrmConstants;
import org.tton.hrm.util.tag.PageModel;

/**
 * ClassName: JobController <br/>
 * Description: TODO <br/>
 * Date: 2018年1月21日 下午2:25:25 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Controller
public class JobController {

    private static final Log logger = LogFactory.getLog(JobController.class);
    
    @Autowired
    @Qualifier("jobService")
    private JobService jobService;

    /**
     * 列表
     * @param pageIndex
     * @param job
     * @param model
     * @return
     */
    @RequestMapping(value = "job/selectJob")
    public String selectJob(Integer pageIndex, @ModelAttribute Job job, Model model) {
        logger.info(HrmConstants.JOBINFO + DeptController.class.getName() + ".selectJob");
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        System.out.println("job:" + job);
        System.out.println("pageIndex:" + pageIndex);
        List<Job> jobs = jobService.findJob(job, pageModel);
        model.addAttribute("jobs", jobs);
        model.addAttribute("pageModel", pageModel);
        return "job/job";

    }

    /**
     * 处理删除职位请求
     * @param String ids 需要删除的id字符串
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "job/removeJob")
    public ModelAndView removeJob(String ids, ModelAndView mv) {
        logger.info(HrmConstants.JOBINFO + DeptController.class.getName() + ".removeJob");
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            jobService.removeJobById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/job/selectJob");
        return mv;
    }

    /**
     * 处理添加请求
     * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
     * @param Job  job  要添加的职位对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "job/addJob")
    public ModelAndView addJob(String flag, @ModelAttribute Job job, ModelAndView mv) {
        logger.info(HrmConstants.JOBINFO + DeptController.class.getName() + ".addJob");
        if (flag.equals("1")) {
            mv.setViewName("/job/showAddJob");
        } else {
            jobService.addJob(job);
            mv.setViewName("redirect:/job/selectJob");
        }
        return mv;
    }

    /**
     * 处理修改职位请求
     * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param Job job 要修改部门的对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "job/updateJob")
    public ModelAndView updateDept(String flag, @ModelAttribute Job job, ModelAndView mv) {
        logger.info(HrmConstants.JOBINFO + DeptController.class.getName() + ".updateDept");
        if (flag.equals("1")) {
            Job target = jobService.findJobById(job.getId());
            mv.addObject("job", target);
            mv.setViewName("/job/showUpdateJob");
        } else {
            jobService.modifyJob(job);
            System.out.println(job);
            mv.setViewName("redirect:/job/selectJob");
        }
        return mv;
    }

}
