package org.tton.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.tton.hrm.domain.Job;
import org.tton.hrm.util.common.HrmConstants;


 /**
 * ClassName: JobDynaSqlProvider <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:46:43 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
public class JobDynaSqlProvider {
    // 按要求查找数据
    public String selectWhitParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(HrmConstants.JOBTABLE);
                if (params.get("job") != null) {
                    Job job = (Job) params.get("job");
                    if (job.getName() != null && !job.getName().equals("")) {
                        WHERE("  name LIKE CONCAT ('%',#{job.name},'%') ");
                    }
                }
            }
        }.toString();

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }

        return sql;
    }

    // 计数
    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(HrmConstants.JOBTABLE);
                if (params.get("job") != null) {
                    Job job = (Job) params.get("job");
                    if (job.getName() != null && !job.getName().equals("")) {
                        WHERE("  name LIKE CONCAT ('%',#{job.name},'%') ");
                    }
                }
            }
        }.toString();
    }

    // 插入数据
    public String insertJob(final Job job) {

        return new SQL() {
            {
                INSERT_INTO(HrmConstants.JOBTABLE);
                if (job.getName() != null && !job.getName().equals("")) {
                    VALUES("name", "#{name}");
                }
                if (job.getRemark() != null && !job.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    // 更新数据
    public String updateJob(final Job job) {

        return new SQL() {
            {
                UPDATE(HrmConstants.JOBTABLE);
                if (job.getName() != null) {
                    SET(" name = #{name} ");
                }
                if (job.getRemark() != null) {
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
