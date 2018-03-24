package org.tton.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.tton.hrm.domain.OvertimeRecord;
import org.tton.hrm.util.common.HrmConstants;

/**
 * ClassName: OvertimeRecordSqlProvider <br/>
 * Description: TODO <br/>
 * Date: 2018年1月24日 上午10:46:22 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class OvertimeRecordSqlProvider {

    public String count(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("count(*)");
                FROM(HrmConstants.OVERTIMERECORDTABLE);
                if (params.get("OvertimeRecord") != null) {
                    OvertimeRecord overtimeRecord = (OvertimeRecord) params.get("OvertimeRecord");
                    if (overtimeRecord.getName() != null && !overtimeRecord.getName().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',#{overtimeRecord.name},'%')");
                    }
                }
            }
        }.toString();
        return sql;
    }

    // 分页显示
    public String selectWhitParam(final Map<String, Object> params) {

        String sql = new SQL() {
            {
                SELECT("*");
                FROM(HrmConstants.OVERTIMERECORDTABLE);
                if (params.get("OvertimeRecord") != null) {
                    OvertimeRecord overtimeRecord = (OvertimeRecord) params.get("OvertimeRecord");
                    if (overtimeRecord.getName() != null && !overtimeRecord.getName().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',#{overtimeRecord.name},'%')");
                    }
                }
            }
        }.toString();
        return sql;
    }

    public String insertOvertimeRecord(final OvertimeRecord overtimeRecord) {
        String sql = new SQL() {
            {
                INSERT_INTO(HrmConstants.OVERTIMERECORDTABLE);
                if (overtimeRecord.getWorkId() != null && !overtimeRecord.getWorkId().equals("")) {
                    VALUES("work_id", "#{workId}");
                }
                if (overtimeRecord.getName() != null && !overtimeRecord.getName().equals("")) {
                    VALUES("name", "#{name}");
                }
                if (overtimeRecord.getStartDate() != null && !overtimeRecord.getStartDate().equals("")) {
                    VALUES("start_date", "#{startDate}");
                }
                if (overtimeRecord.getEndDate() != null && !overtimeRecord.getEndDate().equals("")) {
                    VALUES("end_date", "#{endDate}");
                }
                if (overtimeRecord.getStartTime() != null && !overtimeRecord.getStartTime().equals("")) {
                    VALUES("start_time", "#{startTime}");
                }
                if (overtimeRecord.getEndTime() != null && !overtimeRecord.getEndTime().equals("")) {
                    VALUES("end_time", "#{endTime}");
                }
                if (overtimeRecord.getOvertimeLenth() != null && !overtimeRecord.getOvertimeLenth().equals("")) {
                    VALUES("overtime_lenth", "#{overtimeLenth}");
                }
                if (overtimeRecord.getOvertimeReason() != null && !overtimeRecord.getOvertimeReason().equals("")) {
                    VALUES("overtime_reason", "#{overtimeReason}");
                }
                if (overtimeRecord.getOvertimeProject() != null && !overtimeRecord.getOvertimeProject().equals("")) {
                    VALUES("overtime_Project_id", "#{overtimeProject.id}");
                }
            }
        }.toString();
        System.out.println(sql);
        return sql;
    }

    public String updateOvertimeRecord(final OvertimeRecord overtimeRecord) {
        String sql = new SQL() {
            {
                UPDATE(HrmConstants.OVERTIMERECORDTABLE);
                if (overtimeRecord.getWorkId() != null && !overtimeRecord.getWorkId().equals("")) {
                    SET(" work_id = #{workId} ");
                }
                if (overtimeRecord.getName() != null && !overtimeRecord.getName().equals("")) {
                    SET(" name=#{name} ");
                }
                if (overtimeRecord.getStartDate() != null && !overtimeRecord.getStartDate().equals("")) {
                    SET(" start_date=#{startDate} ");
                }
                if (overtimeRecord.getEndDate() != null && !overtimeRecord.getEndDate().equals("")) {
                    SET(" end_date=#{endDate} ");
                }
                if (overtimeRecord.getStartTime() != null && !overtimeRecord.getStartTime().equals("")) {
                    SET(" start_time=#{startTime} ");
                }
                if (overtimeRecord.getEndTime() != null && !overtimeRecord.getEndTime().equals("")) {
                    SET("end_time=#{endTime}");
                }
                if (overtimeRecord.getOvertimeLenth() != null && !overtimeRecord.getOvertimeLenth().equals("")) {
                    SET(" overtime_lenth=#{overtimeLenth} ");
                }
                if (overtimeRecord.getOvertimeReason() != null && !overtimeRecord.getOvertimeReason().equals("")) {
                    SET(" overtime_reason=#{overtimeReason} ");
                }
                if (overtimeRecord.getOvertimeProject() != null && !overtimeRecord.getOvertimeProject().equals("")) {
                    SET(" overtime_Project_id=#{overtimeProject.overtimeProjectId} ");
                }
                WHERE("id=#{id}");
            }
        }.toString();
        System.out.println(sql);
        return sql;
    }

}
