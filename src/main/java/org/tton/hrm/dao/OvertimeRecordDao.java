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
import org.tton.hrm.dao.provider.OvertimeRecordSqlProvider;
import org.tton.hrm.domain.OvertimeRecord;
import org.tton.hrm.util.common.HrmConstants;

/**
 * ClassName: OvertimeRecordDao <br/>
 * Description: TODO <br/>
 * Date: 2018年1月24日 上午10:45:20 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public interface OvertimeRecordDao {

    @SelectProvider(type = OvertimeRecordSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = OvertimeRecordSqlProvider.class, method = "selectWhitParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "work_id", property = "workId"),
            @Result(column = "name", property = "name"),
            @Result(column = "start_date", property = "startDate"),
            @Result(column = "end_date", property = "endDate"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "overtime_lenth", property = "overtimeLenth"),
            @Result(column = "overtime_reason", property = "overtimeReason"),
            @Result(column = "overtime_project_id", property = "overtimeProject", one = @One(select = "org.tton.hrm.dao.OvertimeProjectDao.selectById", fetchType = FetchType.EAGER)) })
    List<OvertimeRecord> selectByPage(Map<String, Object> params);

    @SelectProvider(type = OvertimeRecordSqlProvider.class, method = "insertOvertimeRecord")
    void save(OvertimeRecord overtimeRecord);

    @Delete(" delete from " + HrmConstants.OVERTIMERECORDTABLE + " where id=#{id} ")
    void deleteById(Integer id);

    @Delete(" delete from " + HrmConstants.OVERTIMERECORDTABLE + " where overtime_project_id=#{overtimeProjectId} ")
    void deleteByOvertimeProjectId(Integer overtimeProjectId);
    
    @Select("select * from " + HrmConstants.OVERTIMERECORDTABLE + " where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "work_id", property = "workId"),
            @Result(column = "name", property = "name"),
            @Result(column = "start_date", property = "startDate"),
            @Result(column = "end_date", property = "endDate"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "overtime_lenth", property = "overtimeLenth"),
            @Result(column = "overtime_reason", property = "overtimeReason"),
            @Result(column = "overtime_project_id", property = "overtimeProject", one = @One(select = "org.tton.hrm.dao.OvertimeProjectDao.selectById", fetchType = FetchType.EAGER)) })
    OvertimeRecord selectById(Integer id);

    
    @Select("select * from " + HrmConstants.OVERTIMERECORDTABLE + " where overtime_project_id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "work_id", property = "workId"),
            @Result(column = "name", property = "name"),
            @Result(column = "start_date", property = "startDate"),
            @Result(column = "end_date", property = "endDate"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "overtime_lenth", property = "overtimeLenth"),
            @Result(column = "overtime_reason", property = "overtimeReason"),
            @Result(column = "overtime_project_id", property = "overtimeProject", one = @One(select = "org.tton.hrm.dao.OvertimeProjectDao.selectById", fetchType = FetchType.EAGER)) })
    List<OvertimeRecord> selectOvertimeRecordsByProjectId(Integer id);
    
    
    @SelectProvider(type = OvertimeRecordSqlProvider.class, method = "updateOvertimeRecord")
    void update(OvertimeRecord overtimeRecord);
}
