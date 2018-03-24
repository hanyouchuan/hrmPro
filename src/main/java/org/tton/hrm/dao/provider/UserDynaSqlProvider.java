package org.tton.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.tton.hrm.domain.User;
import org.tton.hrm.util.common.HrmConstants;

 /**
 * ClassName: UserDynaSqlProvider <br/>
 * Description: TODO <br/>
 * Date: 2018年3月23日 下午10:47:03 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
public class UserDynaSqlProvider {

    public String selectWhitParam(final Map<String, Object> params){
        String sql = new SQL(){
            {SELECT("*");
            FROM (HrmConstants.USERTABLE);
            if(params.get("user")!=null){
                User user = (User)params.get("user");
                if(user.getUsername()!=null && !user.getUsername().equals("")){
                    WHERE(" username like concat ('%',#{user.username},'%')");
                }
                if(user.getStatus()!=null && !user.getStatus().equals("")){
                    WHERE(" status like concat ('%',#{user.status},'%')");
                }
            }
            }
            
        }.toString();
        
        if(params.get("pageModel")!=null){
            sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        }
        System.out.println(sql);
        return sql;
    }
    
    public String count(final Map<String , Object> params){
        
        String sql = new SQL(){
            {
                SELECT("count(*)");
                FROM(HrmConstants.USERTABLE);
                if(params.get("user")!=null){
                    User user = (User)params.get("user");
                    if(user.getUsername()!=null && !user.getUsername().equals("")){
                        WHERE(" username like concat ('%',#{user.username},'%')");
                    }
                    if(user.getStatus()!=null && !user.getStatus().equals("")){
                        WHERE(" status like concat ('%',#{user.status},'%')");
                    }
                }
            }
        }.toString();
        
        return sql;
    }
    
    public String insertUser(final User user){
       System.out.println(user+"<====>");
       return new SQL(){
           {
               INSERT_INTO(HrmConstants.USERTABLE);
               if(user.getUsername() != null && !user.getUsername().equals("")){
                   VALUES("username", "#{username}");
               }
               if(user.getStatus() != null && !user.getStatus().equals("")){
                   VALUES("status", "#{status}");
               }
               if(user.getLoginname() != null && !user.getLoginname().equals("")){
                   VALUES("loginname", "#{loginname}");
               }
               if(user.getPassword() != null && !user.getPassword().equals("")){
                   VALUES("password", "#{password}");
               }
           }
       }.toString();
    }
    public String updateUser(final User user){
        return new SQL(){
            {
                UPDATE(HrmConstants.USERTABLE);
                if(user.getUsername() != null){
                    SET(" username = #{username} ");
                }
                if(user.getLoginname() != null){
                    SET(" loginname = #{loginname} ");
                }
                if(user.getPassword()!= null){
                    SET(" password = #{password} ");
                }
                if(user.getStatus()!= null){
                    SET(" status = #{status} ");
                }
                if(user.getCreateDate()!= null){
                    SET(" create_date = #{createDate} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
   
}

    
    
    
    
    
    
    
    
    
    

