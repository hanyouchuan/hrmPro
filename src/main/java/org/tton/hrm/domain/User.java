package org.tton.hrm.domain;

import java.io.Serializable;
import java.util.Date;


 /**
 * ClassName: User <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:48:01 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
public class User implements Serializable {

	/**
     * serialVersionUID:TODO(描述这个变量用途).
     * 
     */
    private static final long serialVersionUID = 4022361728207337659L;
    private Integer id;			// id
	private String username;	// 用户名
	private String loginname;	// 登录名
	private String password;	// 密码
	private Integer status;		// 状态
	private Date createDate;	// 建档日期
	// 无参数构造器
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	// setter和getter方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", loginname="
				+ loginname + ", password=" + password + ", status=" + status
				+ ", createDate=" + createDate + "]";
	}
	
	
}
