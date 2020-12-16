package com.zengc.department;

import com.zengc.ZCApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName TestLauncher
 * @Description:
 * @Author baixa
 * @Date 2019/11/7
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zengc")
public class DepartmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZCApplication.class, args);
    }
}
