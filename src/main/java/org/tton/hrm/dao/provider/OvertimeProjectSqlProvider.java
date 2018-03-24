package org.tton.hrm.dao.provider;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.jdbc.SQL;
import org.tton.hrm.domain.OvertimeProject;
import org.tton.hrm.util.common.HrmConstants;

/**
 * ClassName: OvertimeProjectSqlProvider <br/>
 * Description: TODO <br/>
 * Date: 2018年1月24日 上午10:45:58 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class OvertimeProjectSqlProvider {

    private static final Log logger = LogFactory.getLog(OvertimeProjectSqlProvider.class);
    // 分页显示
    public String selectWhitParam(final Map<String, Object> params) {
         //System.out.println("==="+params.get("overtimeProject"));
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(HrmConstants.OVERTIMEPROJECTTABLE);
                if (params.get("overtimeProject") != null) {
                    OvertimeProject overtimeProject = (OvertimeProject) params.get("overtimeProject");
                    if (overtimeProject.getTitle() != null && !overtimeProject.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT ('%',#{overtimeProject.title},'%')");
                    }
                    if (overtimeProject.getRemark() != null && !overtimeProject.getRemark().equals("")) {
                        WHERE(" remark LIKE CONCAT ('%',#{overtimeProject.remark},'%')");
                    }
                }
            }
        }.toString();
        return sql;
    }

    public String insertOvertimeProject(final OvertimeProject overtimeProject) {
        String sql = new SQL() {
            {
                INSERT_INTO(HrmConstants.OVERTIMEPROJECTTABLE);
                if (overtimeProject.getTitle() != null && !overtimeProject.getTitle().equals("")) {
                    VALUES("title", "#{title}");
                }
                if (overtimeProject.getRemark() != null && !overtimeProject.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }
                if (overtimeProject.getUser() != null && !overtimeProject.getUser().equals("")) {
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
        return sql;
    }

    public String updateOvertimeProject(final OvertimeProject overtimeProject) {

        String sql = new SQL() {
            {
                UPDATE(HrmConstants.OVERTIMEPROJECTTABLE);
                if (overtimeProject.getTitle() != null && !overtimeProject.getTitle().equals("")) {
                    SET(" title=#{title} ");
                }
                if (overtimeProject.getRemark() != null && !overtimeProject.getRemark().equals("")) {
                    SET(" remark=#{remark} ");
                }
                if (overtimeProject.getUser() != null && !overtimeProject.getUser().equals("")) {
                    SET("user_id=#{user.id}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
        return sql;
    }

    public String count(final Map<String, Object> params) {

        String sql = new SQL() {
            {
                SELECT("count(*)");
                FROM(HrmConstants.OVERTIMEPROJECTTABLE);
                if (params.get("OvertimeProject") != null) {
                    OvertimeProject overtimeProject = (OvertimeProject) params.get("OvertimeProject");
                    if (overtimeProject.getTitle() != null && !overtimeProject.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT ('%',#{overtimeProject.title},'%')");
                    }
                }
            }
        }.toString();
        return sql;

    }
}
