package com.zengc.employee;

import com.zengc.ZCApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName TestLauncher
 * @Description:
 * @Author baixa
 * @Date 2019/11/7
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZCApplication.class, args);
//        SpringApplication.run(EmployeeApplication.class, args);
    }
}
