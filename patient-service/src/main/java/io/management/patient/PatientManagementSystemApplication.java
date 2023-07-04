package io.management.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PatientManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientManagementSystemApplication.class, args);
    }

}
