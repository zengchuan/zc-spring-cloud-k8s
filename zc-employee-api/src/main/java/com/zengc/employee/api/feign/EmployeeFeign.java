package com.zengc.employee.api.feign;

import com.zengc.employee.api.params.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengchuan
 * @since 2020-06-05
 */
@FeignClient(name = "zc-employee-service")
public interface EmployeeFeign {

    @GetMapping(value="/zc-employee-service/zc-employee/employee/department/{departmentId}", produces = "application/json;charset=UTF-8")
    List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId);

}
