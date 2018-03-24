package org.tton.hrm.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tton.hrm.domain.Document;
import org.tton.hrm.domain.User;
import org.tton.hrm.service.DocumentService;
import org.tton.hrm.util.common.HrmConstants;
import org.tton.hrm.util.tag.PageModel;

/**
 * ClassName: DocumentController <br/>
 * Description: TODO <br/>
 * Date: 2018年1月23日 下午3:05:20 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Controller
public class DocumentController {

    private static final Log logger = LogFactory.getLog(DocumentController.class);
    @Autowired
    @Qualifier("documentService")
    DocumentService documentService;

    /**
     * 列表
     * @param model
     * @param pageIndex
     * @param document
     * @return
     */
    @RequestMapping(value = "/document/selectDocument")
    public String selectDocument(Model model, Integer pageIndex, @ModelAttribute Document document) {
        logger.info(HrmConstants.DOCUMENTINFO + DeptController.class.getName() + ".selectDocument");
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Document> documents = documentService.findDocument(document, pageModel);

        model.addAttribute("pageModel", pageIndex);
        model.addAttribute("documents", documents);
        return "/document/document";

    }

    /**
     * 处理添加请求
     * @param String flag 标记， 1表示跳转到上传页面，2表示执行上传操作
     * @param Notice notice  要添加的公告对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "/document/addDocument")
    public ModelAndView addDocument(String flag, ModelAndView mv, @ModelAttribute Document document, HttpSession session)
            throws IllegalStateException, IOException {
        logger.info(HrmConstants.DOCUMENTINFO + DeptController.class.getName() + ".addDocument");
        if (flag.equals("1")) {
            mv.setViewName("/document/showAddDocument");
        } else {

            String path = session.getServletContext().getRealPath("/upload/");
            String fileName = document.getFile().getOriginalFilename();
            document.getFile().transferTo(new File(path + File.separator + fileName));
            System.out.println("File.separator--" + File.separator);
            System.out.println("===" + path + "====");
            System.out.println("fileName:" + fileName);
            document.setFilename(fileName);
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            document.setUser(user);
            documentService.addDocument(document);
            mv.setViewName("redirect:/document/selectDocument");
        }
        return mv;

    }

    /**
     * 处理删除文档请求
     * @param String ids 需要删除的id字符串
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "document/removeDocument")
    public ModelAndView removeDocument(String ids, ModelAndView mv) {
        logger.info(HrmConstants.DOCUMENTINFO + DeptController.class.getName() + ".removeDocument");
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            documentService.removeDocumentById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/document/selectDocument");
        return mv;

    }

    /**
     * 处理修改文档请求
     * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param Document document 要修改文档的对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "/document/updateDocument")
    public ModelAndView updateDocument(String flag, @ModelAttribute Document document, HttpSession session,
            ModelAndView mv) {
        logger.info(HrmConstants.DOCUMENTINFO + DeptController.class.getName() + ".updateDocument");
        if (flag.equals("1")) {

            Document target = documentService.findDocumentById(document.getId());
            mv.addObject("document", target);
            mv.setViewName("/document/showUpdateDocument");
        } else {
            documentService.modifyDocument(document);
            mv.setViewName("redirect:/document/selectDocument");
        }
        return mv;
    }
    
    /**
     * 处理文档下载请求
     * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param Document document 要修改文档的对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value = "/document/downLoad")
    public ResponseEntity<byte[]> downLoad(Integer id, HttpSession session) throws IOException {
        logger.info(HrmConstants.DOCUMENTINFO + DeptController.class.getName() + ".downLoad");
        Document target = documentService.findDocumentById(id);

        String fileName = target.getFilename();
        // 文件路径
        String path = session.getServletContext().getRealPath("/upload/");
        File file = new File(path + File.separator + fileName);
        // 创建springframwork 的httpheader对象
        HttpHeaders httpHeaders = new HttpHeaders();
        String downLoadfileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        // 通知浏览器以attachment的下载方式打开图片
        httpHeaders.setContentDispositionFormData("attachment", downLoadfileName);
        // 二进制流的方式下载，比较常用的方式
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);

    }

}
