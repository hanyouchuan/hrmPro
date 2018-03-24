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
import org.tton.hrm.dao.EmployeeDao;
import org.tton.hrm.domain.Employee;
import org.tton.hrm.service.EmployeeService;
import org.tton.hrm.util.tag.PageModel;

 /**
 * ClassName: EmployeeServiceImpl <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:48:54 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.EmployeeService#findEmployee(org.tton.hrm.domain.Employee,
     *      org.tton.hrm.util.tag.PageModel) <br/>
     */
    @Transactional(readOnly = true)
    @Cacheable(value="findEmployee")
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employee", employee);
        int recordCount = employeeDao.count(params);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        List<Employee> employees = employeeDao.selectByPage(params);

        return employees;
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.EmployeeService#removeEmployeeById(java.lang.Integer) <br/>
     */
    @CacheEvict(value={"findEmployee","findEmployeeById"})
    public void removeEmployeeById(Integer id) {
        // TODO Auto-generated method stub
        employeeDao.deleteById(id);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.EmployeeService#findEmployeeById(java.lang.Integer) <br/>
     */
    @Cacheable(value="findEmployeeById")
    @Transactional(readOnly = true)
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectById(id);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.EmployeeService#addEmployee(org.tton.hrm.domain.Employee) <br/>
     */
    @CacheEvict(value={"findEmployee","findEmployeeById"})
    public void addEmployee(Employee employee) {
        // TODO Auto-generated method stub
        employeeDao.save(employee);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.EmployeeService#modifyEmployee(org.tton.hrm.domain.Employee) <br/>
     */
    @CacheEvict(value={"findEmployee","findEmployeeById"})
    public void modifyEmployee(Employee employee) {
        // TODO Auto-generated method stub
        employeeDao.update(employee);
    }

}
