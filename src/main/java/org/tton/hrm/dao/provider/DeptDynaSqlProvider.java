package org.tton.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.tton.hrm.domain.Dept;
import org.tton.hrm.util.common.HrmConstants;


 /**
 * ClassName: DeptDynaSqlProvider <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:45:24 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
public class DeptDynaSqlProvider {

    public String selectWhitParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(HrmConstants.DEPTTABLE);
                if (params.get("dept") != null) {
                    Dept dept = (Dept) params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE(" name like concat('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        }

        return sql;
    }
    
    public String count(final Map<String, Object> params) {
        return new SQL(){
            {
            SELECT("count(*)");
            FROM(HrmConstants.DEPTTABLE);
            if (params.get("dept") != null) {
                Dept dept = (Dept) params.get("dept");
                if (dept.getName() != null && !dept.getName().equals("")) {
                    WHERE(" name like concat('%',#{dept.name},'%') ");
                }
            }
        }
        }.toString();
    }
    
    public  String insertDept(final Dept dept) {
        return new SQL(){
            {
                INSERT_INTO(HrmConstants.DEPTTABLE);
                if(dept.getName()!=null &!dept.getName().equals("")){
                    VALUES("name", "#{name}");
                }
                if(dept.getRemark()!=null &!dept.getRemark().equals("")){
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }
    public String updateDept(final Dept dept) {
        
        return new SQL(){
            {
                UPDATE(HrmConstants.DEPTTABLE);
                if(dept.getName()!=null){
                    SET(" name=#{name} ");
                }
                if(dept.getRemark()!=null){
                    SET(" remark=#{remark} ");
                }
            }
        }.toString();
    }
    
    
    
    

}
