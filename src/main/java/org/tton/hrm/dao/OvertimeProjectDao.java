package org.tton.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.tton.hrm.dao.provider.OvertimeProjectSqlProvider;
import org.tton.hrm.domain.OvertimeProject;
import org.tton.hrm.util.common.HrmConstants;

/**
 * ClassName: OvertimeProjectDao <br/>
 * Description: TODO <br/>
 * Date: 2018年1月24日 上午10:44:31 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
public interface OvertimeProjectDao {

    @SelectProvider(type = OvertimeProjectSqlProvider.class, method = "selectWhitParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "CREATE_DATE", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", one = @One(select = "org.tton.hrm.dao.UserDao.selectById", fetchType = FetchType.EAGER)) })
    List<OvertimeProject> selectByPage(Map<String, Object> params);

    @SelectProvider(type = OvertimeProjectSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    // @Select("select * from " + HrmConstants.OVERTIMEPROJECTTABLE +
    // " order by id desc")
    // List<OvertimeProject> selectAllOverTimeProject();

    @Select("select * from " + HrmConstants.OVERTIMEPROJECTTABLE + " where ID=#{id} order by id desc ")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "CREATE_DATE", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", one = @One(select = "org.tton.hrm.dao.UserDao.selectById", fetchType = FetchType.EAGER)) })
    OvertimeProject selectById(int id);

    @Delete(" delete from " + HrmConstants.OVERTIMEPROJECTTABLE + " where id=#{id} ")
    void deleteById(Integer id);
    
    @SelectProvider(type = OvertimeProjectSqlProvider.class, method = "insertOvertimeProject")
    void save(OvertimeProject overtimeProject);

    @SelectProvider(type = OvertimeProjectSqlProvider.class, method = "updateOvertimeProject")
    void update(OvertimeProject overtimeProject);

}
