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
import org.tton.hrm.domain.Employee;
import org.tton.hrm.domain.Job;
import org.tton.hrm.service.DeptService;
import org.tton.hrm.service.EmployeeService;
import org.tton.hrm.service.JobService;
import org.tton.hrm.util.common.HrmConstants;
import org.tton.hrm.util.tag.PageModel;

/**
 * ClassName: EmployeeController <br/>
 * Description: TODO <br/>
 * Date: 2018年1月21日 下午4:04:32 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Controller
public class EmployeeController {

    private static final Log logger = LogFactory.getLog(EmployeeController.class);
    
    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;
    @Autowired
    @Qualifier("jobService")
    private JobService jobService;
    @Autowired
    @Qualifier("deptService")
    private DeptService deptService;

    /**
     * 处理查询请求
     * @param pageIndex 请求的是第几页
     * @param String job_id 职位编号
     * @param String dept_id 部门编号
     * @param employee 模糊查询参数
     * @param Model model
     * */
    @RequestMapping(value = "/employee/selectEmployee")
    public String selectEmployee(Integer pageIndex, Integer job_id, Integer dept_id, @ModelAttribute Employee employee,
            Model model) {
        logger.info(HrmConstants.EMPLOYEEINFO + DeptController.class.getName() + ".selectEmployee");
        genericAssociation(job_id, dept_id, employee);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Job> jobs = jobService.findAllJob();
        List<Dept> depts = deptService.findAllDept();
        List<Employee> employees = employeeService.findEmployee(employee, pageModel);
        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);
        model.addAttribute("employees", employees);
        model.addAttribute("pageModel", pageModel);
        return "/employee/employee";

    }

    /**
     * 处理添加员工请求
     * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
     * @param String job_id 职位编号
     * @param String dept_id 部门编号
     * @param Employee employee 接收添加参数
     * @param ModelAndView mv 
     * */
    @RequestMapping(value = "/employee/addEmployee")
    public ModelAndView addEmployee(String flag, Integer job_id, Integer dept_id, @ModelAttribute Employee employee,
            ModelAndView mv) {
        logger.info(HrmConstants.EMPLOYEEINFO + DeptController.class.getName() + ".addEmployee");
        if (flag.equals("1")) {
            List<Job> jobs = jobService.findAllJob();
            List<Dept> depts = deptService.findAllDept();
            mv.addObject("jobs", jobs);
            mv.addObject("depts", depts);
            mv.setViewName("/employee/showAddEmployee");
        } else {
            genericAssociation(job_id, dept_id, employee);
            employeeService.addEmployee(employee);
            mv.setViewName("redirect:/employee/selectEmployee");
        }
        return mv;
    }

    /**
     * 处理删除员工请求
     * @param String ids 需要删除的id字符串
     * @param ModelAndView mv
     * */
    public ModelAndView removeEmployee(String ids, ModelAndView mv) {
        logger.info(HrmConstants.EMPLOYEEINFO + DeptController.class.getName() + ".removeEmployee");
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            employeeService.removeEmployeeById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/employee/selectEmployee");
        return mv;
    }

    /**
     * 处理修改员工请求
     * @param String flag 标记，1表示跳转到修改页面，2表示执行修改操作
     * @param String job_id 职位编号
     * @param String dept_id 部门编号
     * @param Employee employee  要修改员工的对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value="employee/updateEmployee")
    public ModelAndView modifyEmployee(String flag, Integer job_id, Integer dept_id, @ModelAttribute Employee employee,
            ModelAndView mv) {
        logger.info(HrmConstants.EMPLOYEEINFO + DeptController.class.getName() + ".modifyEmployee");
        if(flag.equals("1")){
            List<Job> jobs= jobService.findAllJob();
            List<Dept> depts=deptService.findAllDept();
            Employee target = employeeService.findEmployeeById(employee.getId());
            mv.addObject("jobs",jobs);
            mv.addObject("depts",depts);
            mv.addObject("employee",target);
            mv.setViewName("/employee/showUpdateEmployee");
        }else {
            genericAssociation(job_id, dept_id, employee);
            employeeService.modifyEmployee(employee);
            mv.setViewName("redirect:/employee/selectEmployee");
        }
        return mv;
    }

    /**
     * 由于部门和职位在Employee中是对象关联映射，
     * 所以不能直接接收参数，需要创建Job对象和Dept对象
     * */

    @RequestMapping(value = "/employee/removeEmployee")
    public void genericAssociation(Integer job_id, Integer dept_id, Employee employee) {
        logger.info(HrmConstants.EMPLOYEEINFO + DeptController.class.getName() + ".genericAssociation");
        if (job_id != null) {
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if (dept_id != null) {
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }

}
