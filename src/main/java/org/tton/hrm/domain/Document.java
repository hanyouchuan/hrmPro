package org.tton.hrm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

 /**
 * ClassName: Document <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:47:22 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
public class Document implements Serializable{


    private static final long serialVersionUID = 2097319238129505825L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document_inf.id
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document_inf.title
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document_inf.filename
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    private String fileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document_inf.remark
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    private String remark;

    private MultipartFile file;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document_inf.create_date
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document_inf.user_id
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    private User user;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document_inf.id
     *
     * @return the value of document_inf.id
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document_inf.id
     *
     * @param id the value for document_inf.id
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document_inf.title
     *
     * @return the value of document_inf.title
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document_inf.title
     *
     * @param title the value for document_inf.title
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document_inf.filename
     *
     * @return the value of document_inf.filename
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public String getFilename() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document_inf.filename
     *
     * @param filename the value for document_inf.filename
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public void setFilename(String filename) {
        this.fileName = filename == null ? null : filename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document_inf.remark
     *
     * @return the value of document_inf.remark
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * file.<br/>
     *
     * @return  the file <br/>
     * 
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * file.<br/>
     *
     * @param   file    the file to set <br/>
     * 
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document_inf.remark
     *
     * @param remark the value for document_inf.remark
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document_inf.create_date
     *
     * @return the value of document_inf.create_date
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document_inf.create_date
     *
     * @param createDate the value for document_inf.create_date
     *
     * @mbggenerated Wed Jan 17 19:35:48 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * user.<br/>
     *
     * @return  the user <br/>
     * 
     */
    public User getUser() {
        return user;
    }

    /**
     * user.<br/>
     *
     * @param   user    the user to set <br/>
     * 
     */
    public void setUser(User user) {
        this.user = user;
    }


}