package org.tton.hrm.dao;

import static org.tton.hrm.util.common.HrmConstants.DEPTTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.tton.hrm.dao.provider.DeptDynaSqlProvider;
import org.tton.hrm.domain.Dept;

/**
 * 
 * ClassName: DeptDao <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:38:14 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 *
 */

public interface DeptDao {

	// 动态查询
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWhitParam")
	List<Dept> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	@Select("select * from "+DEPTTABLE+" ")
	List<Dept> selectAllDept();
	@Select("select * from "+DEPTTABLE+" where ID = #{id}")
	Dept selectById(int id);

	// 根据id删除部门
	@Delete(" delete from "+DEPTTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入部门
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);
	
	// 动态修改用户
	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update(Dept dept);
}
