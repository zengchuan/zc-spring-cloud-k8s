package com.zengc.employee.service;

import com.zengc.employee.api.params.Employee;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
public interface IEmployeeService {

    Employee findById(Long id);

    List<Employee> findByDepartment(Long departMentId);

}
