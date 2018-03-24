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
import org.tton.hrm.dao.DocumentDao;
import org.tton.hrm.domain.Document;
import org.tton.hrm.service.DocumentService;
import org.tton.hrm.util.tag.PageModel;


 /**
 * ClassName: DocumentServiceImpl <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:48:46 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDao documentDao;

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DocumentService#findDocument(org.tton.hrm.domain.Document,
     *      org.tton.hrm.util.tag.PageModel) <br/>
     */
    @Cacheable(value="findDocument")
    @Transactional(readOnly = true)
    public List<Document> findDocument(Document document, PageModel pageModel) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("document", document);
        int recordCount = documentDao.count(params);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        List<Document> documents = documentDao.selectByPage(params);
        return documents;
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DocumentService#addDocument(org.tton.hrm.domain.Document) <br/>
     */
    @CacheEvict(value="findDocument")
    public void addDocument(Document document) {
        documentDao.save(document);

        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DocumentService#findDocumentById(java.lang.Integer) <br/>
     */
    @CacheEvict(value="findDocument")
    @Transactional(readOnly=true)
    public Document findDocumentById(Integer id) {
        return documentDao.selectById(id);
        // TODO Auto-generated method stub
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DocumentService#removeDocumentById(java.lang.Integer) <br/>
     */
    @CacheEvict(value="findDocument")
    public void removeDocumentById(Integer id) {
        // TODO Auto-generated method stub
        documentDao.deleteById(id);
    }

    /**
     * TODO Description <br/>
     * 
     * @see org.tton.hrm.service.DocumentService#modifyDocument(org.tton.hrm.domain.Document) <br/>
     */
    @CacheEvict(value="findDocument")
    public void modifyDocument(Document document) {
        // TODO Auto-generated method stub
        documentDao.update(document);
    }

}
