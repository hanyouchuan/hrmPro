package org.tton.hrm.service;

import java.util.List;

import org.tton.hrm.domain.Dept;
import org.tton.hrm.util.tag.PageModel;

public interface DeptService {

    List<Dept> findDept(Dept dept, PageModel pageModel);

    List<Dept> findAllDept();

    void removeDeptById(Integer id);

    void addDept(Dept dept);

    Dept findDeptById(Integer id);

    void modifyDept(Dept dept);

}
