package com.zengc.department.service.impl;

import com.google.common.collect.Lists;
import com.zengc.core.exception.ZCException;
import com.zengc.department.entity.Department;
import com.zengc.department.entity.DepartmentVO;
import com.zengc.department.service.IDepartmentService;
import com.zengc.employee.api.feign.EmployeeFeign;
import com.zengc.employee.api.params.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private EmployeeFeign employeeService;


    public DepartmentVO findById(Long id) {
        DepartmentVO departmentVO = null;
        Department department = new Department();
        department.setId(12L);
        department.setName("内科");
        if (department != null) {
            departmentVO = new DepartmentVO();
            departmentVO.setDepartment(department);
            try {
                List<Employee> employeeList = employeeService.findByDepartment(id);
                departmentVO.setEmployees(employeeList);
            } catch (Exception e) {
//                System.out.println("调用employeeService失败：" + e.getMessage());
                throw new ZCException("调用employeeService失败：" + e.getMessage());
            }
        }
        return departmentVO;
    }

    public List<Department> findAll() {
        Department department = new Department();
        department.setId(12L);
        department.setName("内科");

        Department department2 = new Department();
        department2.setId(13L);
        department2.setName("外科");
        return Lists.newArrayList(department, department2);
    }

}
