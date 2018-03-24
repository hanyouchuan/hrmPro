package org.tton.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.tton.hrm.dao.provider.JobDynaSqlProvider;
import org.tton.hrm.domain.Job;

import static org.tton.hrm.util.common.HrmConstants.JOBTABLE;
/**
 * ClassName: JobDao <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:41:09 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
public interface JobDao {

	@Select("select * from "+JOBTABLE+" where ID = #{id}")
	Job selectById(int id);
	
	@Select("select * from "+JOBTABLE+" ")
	List<Job> selectAllJob();

	// 动态查询
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWhitParam")
	List<Job> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	// 根据id删除部门
	@Delete(" delete from "+JOBTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入部门
	@SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
	void save(Job job);
	
	// 动态修改用户
	@SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
	void update(Job job);
}
