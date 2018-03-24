package org.tton.hrm.service;

import java.util.List;

import org.tton.hrm.domain.Document;
import org.tton.hrm.util.tag.PageModel;


public interface DocumentService {
    List<Document> findDocument(Document document,PageModel pageModel);
    
    void addDocument(Document document);
    
    Document findDocumentById(Integer id);
    
    public void removeDocumentById(Integer id);
    
    void modifyDocument(Document document);
}
