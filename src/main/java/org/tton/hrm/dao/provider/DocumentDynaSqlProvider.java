package org.tton.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.tton.hrm.domain.Document;
import org.tton.hrm.util.common.HrmConstants;

import static org.tton.hrm.util.common.HrmConstants.DOCUMENTTABLE;

/**
 * ClassName: deptDynaSqlProvider <br/>
 * Description: TODO <br/>
 * Date: 2018年1月18日 下午2:27:59 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
public class DocumentDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(final Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(HrmConstants.DOCUMENTTABLE);
				if(params.get("document") != null){
					Document document = (Document) params.get("document");
					if(document.getTitle() != null && !document.getTitle().equals("")){
						WHERE("  title LIKE CONCAT ('%',#{document.title},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(final Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(HrmConstants.DOCUMENTTABLE);
				if(params.get("document") != null){
					Document document = (Document) params.get("document");
					if(document.getTitle() != null && !document.getTitle().equals("")){
						WHERE("  title LIKE CONCAT ('%',#{document.title},'%') ");
					}
				}
			}
		}.toString();
	}	
	// 动态插入
	public String insertDocument(final Document document){
		
		return new SQL(){
			{
				INSERT_INTO(HrmConstants.DOCUMENTTABLE);
				if(document.getTitle() != null && !document.getTitle().equals("")){
					VALUES("title", "#{title}");
				}
				if(document.getFilename() != null && !document.getFilename().equals("")){
					VALUES("filename", "#{fileName}");
				}
				if(document.getRemark() != null && !document.getRemark().equals("")){
					VALUES("remark", "#{remark}");
				}
				if(document.getUser() != null && document.getUser().getId() != null){
					VALUES("user_id", "#{user.id}");
				}
			}
		}.toString();
	}
	
	// 动态更新
	public String updateDocument(final Document document){
		
		return new SQL(){
			{
				UPDATE(DOCUMENTTABLE);
				if(document.getTitle() != null && !document.getTitle().equals("")){
					SET(" title = #{title} ");
				}
				if(document.getFilename() != null && !document.getFilename().equals("")){
					SET(" filename = #{fileName} ");
				}
				if(document.getRemark() != null && !document.getRemark().equals("")){
					SET("remark = #{remark}");
				}
				if(document.getUser() != null && document.getUser().getId() != null){
					SET("user_id = #{user.id}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	

}
