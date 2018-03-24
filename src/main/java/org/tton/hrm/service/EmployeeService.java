package org.tton.hrm.service;

import java.util.List;

import org.tton.hrm.domain.Employee;
import org.tton.hrm.util.tag.PageModel;

/**
 * 
 * ClassName: EmployeeService <br/>
 * Description: TODO <br/>
 * Date: 2018年1月21日 下午4:29:21 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 *
 */

public interface EmployeeService {

    /**
     * 
     * findEmployee:TODO Description <br/>
     * TODO Describe applicable conditions of method - Optional.<br/>
     * TODO Description of method execution process - Optional.<br/>
     * TODO Describe usage of method - Optional.<br/>
     * 
     * @exception TODO
     *                Description <br/>
     * @param employee
     * @param pageModel
     * @return
     *            TODO Description <br/>
     */
    
    List<Employee> findEmployee(Employee employee,PageModel pageModel);
    
    /**
     * 
     * removeEmployeeById:TODO Description <br/>
     * TODO Describe applicable conditions of method - Optional.<br/>
     * TODO Description of method execution process - Optional.<br/>
     * TODO Describe usage of method - Optional.<br/>
     * 
     * @exception TODO
     *                Description <br/>
     * @param id
     *            TODO Description <br/>
     */
    void removeEmployeeById(Integer id);
    
    /**
     * 
     * findEmployeeById:TODO Description <br/>
     * TODO Describe applicable conditions of method - Optional.<br/>
     * TODO Description of method execution process - Optional.<br/>
     * TODO Describe usage of method - Optional.<br/>
     * 
     * @exception TODO
     *                Description <br/>
     * @param id
     * @return
     *            TODO Description <br/>
     */
    Employee findEmployeeById(Integer id);
    
   /**
    * 
    * addEmployee:TODO Description <br/>
    * TODO Describe applicable conditions of method - Optional.<br/>
    * TODO Description of method execution process - Optional.<br/>
    * TODO Describe usage of method - Optional.<br/>
    * 
    * @exception TODO
    *                Description <br/>
    * @param employee
    *            TODO Description <br/>
    */
    void addEmployee(Employee employee);
   /**
    * 
    * modifyEmployee:TODO Description <br/>
    * TODO Describe applicable conditions of method - Optional.<br/>
    * TODO Description of method execution process - Optional.<br/>
    * TODO Describe usage of method - Optional.<br/>
    * 
    * @exception TODO
    *                Description <br/>
    * @param employee
    *            TODO Description <br/>
    */
    void modifyEmployee(Employee employee);
    
}
