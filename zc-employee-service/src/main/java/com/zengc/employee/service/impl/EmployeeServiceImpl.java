package com.zengc.employee.service.impl;

import com.google.common.collect.Lists;
import com.zengc.employee.api.params.Employee;
import com.zengc.employee.service.IEmployeeService;
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
public class EmployeeServiceImpl implements IEmployeeService {


    public Employee findById(Long id) {
        Employee employee = new Employee();
        employee.setId(123L);
        employee.setName("曾川");
        return employee;
    }

    public List<Employee> findByDepartment(Long departMentId) {
        Employee employee = new Employee();
        employee.setId(123L);
        employee.setName("曾川");

        Employee employee2 = new Employee();
        employee.setId(124L);
        employee.setName("曾川2");
        return Lists.newArrayList(employee, employee2);
    }


}
