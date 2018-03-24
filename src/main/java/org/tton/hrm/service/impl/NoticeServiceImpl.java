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
import org.tton.hrm.dao.NoticeDao;
import org.tton.hrm.domain.Notice;
import org.tton.hrm.service.NoticeService;
import org.tton.hrm.util.tag.PageModel;

 /**
 * ClassName: NoticeServiceImpl <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:49:04 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeDao noticeDao;
    
    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.NoticeService#findNotice(org.tton.hrm.domain.Notice, org.tton.hrm.util.tag.PageModel) <br/>
     */
    @Cacheable(value="findNotice")
    @Transactional(readOnly=true)
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("notice", notice);
       int recordCount=noticeDao.count(params);
       pageModel.setRecordCount(recordCount);
       if(recordCount>0){
           params.put("pageModel", pageModel);
       }
       List<Notice> notices = noticeDao.selectByPage(params);
        return notices;
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.NoticeService#findNoticeById(java.lang.Integer) <br/>
     */
    @Cacheable(value="findNoticeById")
    @Transactional(readOnly=true)
    public Notice findNoticeById(Integer id) {
        
        return noticeDao.selectById(id);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.NoticeService#removeNoticeById(java.lang.Integer) <br/>
     */
    @CacheEvict(value={"findNotice","findNoticeById"})
    public void removeNoticeById(Integer id) {
        // TODO Auto-generated method stub
        noticeDao.deleteById(id);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.NoticeService#addNotice(org.tton.hrm.domain.Notice) <br/>
     */
    @CacheEvict(value={"findNotice","findNoticeById"})
    public void addNotice(Notice notice) {
        // TODO Auto-generated method stub
        noticeDao.save(notice);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.NoticeService#modifyNotice(org.tton.hrm.domain.Notice) <br/>
     */
    @CacheEvict(value={"findNotice","findNoticeById"})
    public void modifyNotice(Notice notice) {
        // TODO Auto-generated method stub
        noticeDao.update(notice);
    }

}
