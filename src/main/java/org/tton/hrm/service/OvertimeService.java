package org.tton.hrm.service;

import java.util.List;

import org.tton.hrm.domain.OvertimeProject;
import org.tton.hrm.domain.OvertimeRecord;
import org.tton.hrm.util.tag.PageModel;

 /**
 * ClassName: OvertimeService <br/>
 * Description: TODO <br/>
 * Date: 2018年1月24日 下午6:52:29 <br/>
 * <br/>
 * 
 * @author hanyouchuan(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public interface OvertimeService {

    List<OvertimeProject> findOvertimeProjects(OvertimeProject overtimeProject,PageModel pageModel);
    
    
    OvertimeProject findOvertimeProjectById(Integer id);
    
    
    void modifyOvertimeProject(OvertimeProject overtimeProject);
    
    List<OvertimeRecord> findOvertimeRecordsById(Integer id);
    
    void removeOvertimeProject(Integer id);
    
    void removeOvertimeRecordByProjectId(Integer id);
    
    
    OvertimeRecord findOvertimeRecordById(Integer id);
    
    void modifyOvertimeRecord(OvertimeRecord overtimeRecord);
    
    void addOvertimeProject(OvertimeProject overtimeProject);
    
    void addOvertimeRecord(OvertimeRecord overtimeRecord);
    
    
}
