package com.zengc.department.service;


import com.zengc.department.entity.Department;
import com.zengc.department.entity.DepartmentVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
public interface IDepartmentService  {

    DepartmentVO findById(Long id);

     List<Department> findAll();


}
