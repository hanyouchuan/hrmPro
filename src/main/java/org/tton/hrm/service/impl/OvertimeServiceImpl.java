package org.tton.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tton.hrm.dao.OvertimeProjectDao;
import org.tton.hrm.dao.OvertimeRecordDao;
import org.tton.hrm.domain.OvertimeProject;
import org.tton.hrm.domain.OvertimeRecord;
import org.tton.hrm.service.OvertimeService;
import org.tton.hrm.util.tag.PageModel;

import com.mysql.fabric.xmlrpc.base.Array;

 /**
 * ClassName: OvertimeServiceImpl <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:49:13 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Service("overtimeService")
public class OvertimeServiceImpl implements OvertimeService {

    @Autowired
    private OvertimeProjectDao overtimeProjectDao;

    @Autowired
    private OvertimeRecordDao overtimeRecordDao;

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#findEmployee(org.tton.hrm.domain.OvertimeProject,
     *      org.tton.hrm.util.tag.PageModel) <br/>
     */
    public List<OvertimeProject> findOvertimeProjects(OvertimeProject overtimeProject, PageModel pageModel) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("overtimeProject", overtimeProject);
        System.out.println("findOvertimeProjects" + overtimeProject.getTitle());
        int recordCount = overtimeProjectDao.count(params);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        List<OvertimeProject> overtimeProjects = overtimeProjectDao.selectByPage(params);
        return overtimeProjects;
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#findOvertimeProjectId(java.lang.Integer) <br/>
     */
    public OvertimeProject findOvertimeProjectById(Integer id) {
        System.out.println("findOvertimeProjectById: " + id);
        OvertimeProject overtimeProject = overtimeProjectDao.selectById(id);
        System.out.println(overtimeProject.getTitle());
        return overtimeProject;
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#modifyOvertimeProject(org.tton.hrm.domain.OvertimeProject) <br/>
     */
    public void modifyOvertimeProject(OvertimeProject overtimeProject) {
        // TODO Auto-generated method stub
        overtimeProjectDao.update(overtimeProject);

    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#removeOvertimeProject(java.lang.String) <br/>
     */
    public void removeOvertimeProject(Integer id) {
        // TODO Auto-generated method stub
        overtimeProjectDao.deleteById(id);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#removeOvertimeRecordByProjectId(java.lang.Integer) <br/>
     */
    public void removeOvertimeRecordByProjectId(Integer id) {
        // TODO Auto-generated method stub
        overtimeRecordDao.deleteByOvertimeProjectId(id);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#findOvertimeRecordById(java.lang.Integer) <br/>
     */
    public List<OvertimeRecord> findOvertimeRecordsById(Integer id) {

        return overtimeRecordDao.selectOvertimeRecordsByProjectId(id);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#findOvertimeRecordById(java.lang.Integer) <br/>
     */
    public OvertimeRecord findOvertimeRecordById(Integer id) {

        return overtimeRecordDao.selectById(id);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#modifyOvertimeRecord(org.tton.hrm.domain.OvertimeRecord) <br/>
     */
    public void modifyOvertimeRecord(OvertimeRecord overtimeRecord) {
        // TODO Auto-generated method stub
        overtimeRecordDao.update(overtimeRecord);

    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#addOvertimeProject(org.tton.hrm.domain.OvertimeProject) <br/>
     */
    public void addOvertimeProject(OvertimeProject overtimeProject) {
        // TODO Auto-generated method stub
        
         overtimeProjectDao.save(overtimeProject);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.OvertimeService#addOvertimeRecord(org.tton.hrm.domain.OvertimeRecord) <br/>
     */
    public void addOvertimeRecord(OvertimeRecord overtimeRecord) {
        // TODO Auto-generated method stub
        
        overtimeRecordDao.save(overtimeRecord);
        
    }

}
