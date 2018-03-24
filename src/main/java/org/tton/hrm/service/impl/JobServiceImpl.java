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
import org.tton.hrm.dao.JobDao;
import org.tton.hrm.domain.Job;
import org.tton.hrm.service.JobService;
import org.tton.hrm.util.tag.PageModel;

 /**
 * ClassName: JobServiceImpl <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:48:59 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.JobService#findAllJob() <br/>
     */
    @Cacheable(value="findAllJob")
    @Transactional(readOnly = true)
    public List<Job> findAllJob() {
        return jobDao.selectAllJob();
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.JobService#findJob(org.tton.hrm.domain.Job,
     *      org.tton.hrm.util.tag.PageModel) <br/>
     */
    @Cacheable(value="findJob")
    @Transactional(readOnly = true)
    public List<Job> findJob(Job job, PageModel pageModel) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("job", job);
        int recordCount = jobDao.count(params);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        List<Job> jobs = jobDao.selectByPage(params);

        return jobs;
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.JobService#removeJobById(java.lang.Integer) <br/>
     */
    @CacheEvict(value={"findAllJob","findAllJob","findJobById"})
    public void removeJobById(Integer id) {
        // TODO Auto-generated method stub
        jobDao.deleteById(id);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.JobService#addJob(org.tton.hrm.domain.Job) <br/>
     */
    @CacheEvict(value={"findAllJob","findAllJob","findJobById"})
    public void addJob(Job job) {
        // TODO Auto-generated method stub
        jobDao.save(job);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.JobService#findJobById(java.lang.Integer) <br/>
     */
    @Cacheable(value="findJobById")
    @Transactional(readOnly = true)
    public Job findJobById(Integer id) {
        return jobDao.selectById(id);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.JobService#modifyJob(org.tton.hrm.domain.Job) <br/>
     */
    @CacheEvict(value={"findAllJob","findAllJob","findJobById"})
    public void modifyJob(Job job) {
        // TODO Auto-generated method stub
         jobDao.update(job);
    }

}
