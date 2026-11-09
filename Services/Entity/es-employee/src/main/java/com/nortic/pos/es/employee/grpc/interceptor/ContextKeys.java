package com.nortic.pos.es.employee.grpc.interceptor;

import com.nortic.pos.es.employee.security.SecurityConstants;
import io.grpc.Context;
import io.grpc.Metadata;

import java.util.List;
import java.util.Map;

public final class ContextKeys {

    private static final String GRPC_SERVICE = "com.nortic.pos.es.employee.grpc.EmployeeService";

    private static final String CORRELATION_ID_CONSTANT = "correlation-id";

    private static final String CONSUMER_ID_CONSTANT = "consumer-id";

    private static final String STORE_ID_CONSTANT = "store-id";

    private static final String AUTHORIZATION_CONSTANT = "authorization";

    public static final Context.Key<String> CORRELATION_ID_CONTEXT_KEY = Context.key(CORRELATION_ID_CONSTANT);

    public static final Context.Key<String> CONSUMER_ID_CONTEXT_KEY = Context.key(CONSUMER_ID_CONSTANT);

    public static final Context.Key<String> STORE_ID_CONTEXT_KEY = Context.key(STORE_ID_CONSTANT);

    public static final Metadata.Key<String> METADATA_CORRELATION_ID =
            Metadata.Key.of(CORRELATION_ID_CONSTANT, Metadata.ASCII_STRING_MARSHALLER);

    public static final Metadata.Key<String> METADATA_CONSUMER_ID =
            Metadata.Key.of(CONSUMER_ID_CONSTANT, Metadata.ASCII_STRING_MARSHALLER);

    public static final Metadata.Key<String> METADATA_STORE_ID =
            Metadata.Key.of(STORE_ID_CONSTANT, Metadata.ASCII_STRING_MARSHALLER);

    public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY =
            Metadata.Key.of(AUTHORIZATION_CONSTANT, Metadata.ASCII_STRING_MARSHALLER);

    public static final Context.Key<String> AUTHORIZATION_CONTEXT_KEY = Context.key(AUTHORIZATION_CONSTANT);

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String UNAUTHENTICATED_MESSAGE = "Authentication required";

    public static final String PERMISSION_DENIED_MESSAGE = "Permission denied";

    public static final Map<String, String> METHOD_PERMISSIONS = Map.ofEntries(
            Map.entry(GRPC_SERVICE + "/CreateEmployee", SecurityConstants.PERMISSION_EMPLOYEE_CREATE),
            Map.entry(GRPC_SERVICE + "/GetEmployeeById", SecurityConstants.PERMISSION_EMPLOYEE_READ),
            Map.entry(GRPC_SERVICE + "/GetAllEmployees", SecurityConstants.PERMISSION_EMPLOYEE_READ),
            Map.entry(GRPC_SERVICE + "/SearchEmployees", SecurityConstants.PERMISSION_EMPLOYEE_READ),
            Map.entry(GRPC_SERVICE + "/QueryEmployees", SecurityConstants.PERMISSION_EMPLOYEE_READ),
            Map.entry(GRPC_SERVICE + "/UpdateEmployee", SecurityConstants.PERMISSION_EMPLOYEE_UPDATE),
            Map.entry(GRPC_SERVICE + "/DeleteEmployee", SecurityConstants.PERMISSION_EMPLOYEE_DELETE),
            Map.entry(GRPC_SERVICE + "/RestoreEmployee", SecurityConstants.PERMISSION_EMPLOYEE_DELETE),
            Map.entry(GRPC_SERVICE + "/ChangeEmployeeStatus", SecurityConstants.PERMISSION_EMPLOYEE_UPDATE),
            Map.entry(GRPC_SERVICE + "/ChangePhone", SecurityConstants.PERMISSION_EMPLOYEE_UPDATE),
            Map.entry(GRPC_SERVICE + "/ChangeEmail", SecurityConstants.PERMISSION_EMPLOYEE_UPDATE),
            Map.entry(GRPC_SERVICE + "/Changeusername", SecurityConstants.PERMISSION_EMPLOYEE_UPDATE),
            Map.entry(GRPC_SERVICE + "/ChangeEmployeePassword", SecurityConstants.PERMISSION_EMPLOYEE_UPDATE)
    );

    public static final List<String> PUBLIC_SERVICE_METHODS = List.of(
            "grpc.reflection.v1.ServerReflection",
            "grpc.health.v1.Health"
    );
}