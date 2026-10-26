package com.nortic.pos.es.role.grpc.interceptor;

import com.nortic.pos.es.role.security.SecurityConstants;
import io.grpc.Context;
import io.grpc.Metadata;

import java.util.List;
import java.util.Map;

public final class ContextKeys {

    private static final String GRPC_SERVICE = "com.nortic.pos.es.role.grpc.RoleService";

    private static final String CORRELATION_ID_CONSTANT = "correlation-id";

    private static final String CONSUMER_ID_CONSTANT = "consumer-id";

    private static final String AUTHORIZATION_CONSTANT = "authorization";

    public static final Context.Key<String> CORRELATION_ID_CONTEXT_KEY = Context.key(CORRELATION_ID_CONSTANT);

    public static final Context.Key<String> CONSUMER_ID_CONTEXT_KEY = Context.key(CONSUMER_ID_CONSTANT);

    public static final Metadata.Key<String> METADATA_CORRELATION_ID =
            Metadata.Key.of(CORRELATION_ID_CONSTANT, Metadata.ASCII_STRING_MARSHALLER);

    public static final Metadata.Key<String> METADATA_CONSUMER_ID =
            Metadata.Key.of(CONSUMER_ID_CONSTANT, Metadata.ASCII_STRING_MARSHALLER);

    public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY =
            Metadata.Key.of(AUTHORIZATION_CONSTANT, Metadata.ASCII_STRING_MARSHALLER);

    public static final Context.Key<String> AUTHORIZATION_CONTEXT_KEY = Context.key(AUTHORIZATION_CONSTANT);

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String UNAUTHENTICATED_MESSAGE = "Authentication required";

    public static final String PERMISSION_DENIED_MESSAGE = "Permission denied";

    public static final Map<String, String> METHOD_PERMISSIONS = Map.ofEntries(
            Map.entry(GRPC_SERVICE + "/CreateRole", SecurityConstants.ROLE_CREATE),
            Map.entry(GRPC_SERVICE + "/GetRole", SecurityConstants.ROLE_READ),
            Map.entry(GRPC_SERVICE + "/ListRoles", SecurityConstants.ROLE_READ),
            Map.entry(GRPC_SERVICE + "/UpdateRole", SecurityConstants.ROLE_UPDATE),
            Map.entry(GRPC_SERVICE + "/DeleteRole", SecurityConstants.ROLE_DELETE),
            Map.entry(GRPC_SERVICE + "/RestoreRole", SecurityConstants.ROLE_DELETE),
            Map.entry(GRPC_SERVICE + "/ChangeStatus", SecurityConstants.ROLE_UPDATE),
            Map.entry(GRPC_SERVICE + "/GetRolePermission", SecurityConstants.ROLE_READ),
            Map.entry(GRPC_SERVICE + "/AddPermissionToRole", SecurityConstants.ROLE_CREATE),
            Map.entry(GRPC_SERVICE + "/RemovePermissionsFromRole", SecurityConstants.ROLE_DELETE),
            Map.entry(GRPC_SERVICE + "/RemovePermissionFromRole", SecurityConstants.ROLE_DELETE)
    );

    public static final List<String> PUBLIC_SERVICE_METHODS = List.of(
            "grpc.reflection.v1.ServerReflection",
            "grpc.health.v1.Health"
    );
}
