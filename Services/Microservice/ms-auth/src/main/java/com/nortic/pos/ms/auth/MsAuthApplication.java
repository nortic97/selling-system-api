package com.nortic.pos.ms.auth;

import com.nortic.pos.es.employee.grpc.EmployeeServiceGrpc;
import com.nortic.pos.es.role.grpc.RoleServiceGrpc;
import com.nortic.pos.es.user.grpc.UserServiceGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.grpc.client.ImportGrpcClients;

@ImportGrpcClients(
        target = "es-user",
        types = UserServiceGrpc.UserServiceBlockingStub.class)
@ImportGrpcClients(
        target = "es-employee",
        types = EmployeeServiceGrpc.EmployeeServiceBlockingStub.class)
@ImportGrpcClients(
        target = "es-role",
        types = RoleServiceGrpc.RoleServiceBlockingStub.class)
@SpringBootApplication
@EnableDiscoveryClient
public class MsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAuthApplication.class, args);
    }

}
