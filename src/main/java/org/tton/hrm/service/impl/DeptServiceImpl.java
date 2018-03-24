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
import org.tton.hrm.dao.DeptDao;
import org.tton.hrm.domain.Dept;
import org.tton.hrm.service.DeptService;
import org.tton.hrm.util.tag.PageModel;

 /**
 * ClassName: DeptServiceImpl <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:48:41 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("deptService")
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptDao deptDao;
    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DeptService#findDept(org.tton.hrm.domain.Dept, org.tton.hrm.util.tag.PageModel) <br/>
     */
    @Cacheable(value="findDept")
    @Transactional(readOnly=true)
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("dept", dept);
       System.out.println("====================");
       int recordCount= deptDao.count(params);
       pageModel.setRecordCount(recordCount);
       if(recordCount>0){
           params.put("pageModel", pageModel);
       }
       List<Dept> depts= deptDao.selectByPage(params);
       return depts;
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DeptService#findAllDept() <br/>
     */
    @Cacheable(value="findAllDept")
    @Transactional(readOnly=true)
    public List<Dept> findAllDept() {
        return deptDao.selectAllDept();
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DeptService#removeDeptById(java.lang.Integer) <br/>
     */
    @CacheEvict(value={"findDept","findAllDept"})
    public void removeDeptById(Integer id) {
        // TODO Auto-generated method stub
        deptDao.deleteById(id);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DeptService#addDept(org.tton.hrm.domain.Dept) <br/>
     */
    @CacheEvict(value={"findDept","findAllDept"})
    public void addDept(Dept dept) {
        // TODO Auto-generated method stub
        deptDao.save(dept);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DeptService#findDeptById(java.lang.Integer) <br/>
     */
    @CacheEvict(value={"findDept","findAllDept"})
    @Transactional(readOnly=true)
    public Dept findDeptById(Integer id) {
        return deptDao.selectById(id);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DeptService#modifyDept(org.tton.hrm.domain.Dept) <br/>
     */
    @CacheEvict(value={"findDept","findAllDept"})
    @Transactional(readOnly=true)
    public void modifyDept(Dept dept) {
        // TODO Auto-generated method stub
        deptDao.update(dept);
    }

}
