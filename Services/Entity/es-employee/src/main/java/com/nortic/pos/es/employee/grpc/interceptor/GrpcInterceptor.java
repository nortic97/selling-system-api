package com.nortic.pos.es.employee.grpc.interceptor;

import com.nortic.pos.es.employee.security.jwt.JwtService;
import com.nortic.pos.es.employee.security.jwt.model.JwtEmployee;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.GlobalServerInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.nortic.pos.es.employee.grpc.interceptor.ContextKeys.BEARER_PREFIX;
import static com.nortic.pos.es.employee.grpc.interceptor.ContextKeys.METHOD_PERMISSIONS;
import static com.nortic.pos.es.employee.grpc.interceptor.ContextKeys.PERMISSION_DENIED_MESSAGE;
import static com.nortic.pos.es.employee.grpc.interceptor.ContextKeys.PUBLIC_SERVICE_METHODS;
import static com.nortic.pos.es.employee.grpc.interceptor.ContextKeys.UNAUTHENTICATED_MESSAGE;

@Slf4j
@Component
@GlobalServerInterceptor
@RequiredArgsConstructor
public class GrpcInterceptor implements ServerInterceptor {

    private final JwtService jwtService;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next
    ) {
        String serviceName = call.getMethodDescriptor().getServiceName();
        String fullMethodName = call.getMethodDescriptor().getFullMethodName();

        if (isPublicService(serviceName) || isPublicService(fullMethodName)) {
            log.debug("Skipping authentication for public service: {}", fullMethodName);
            return next.startCall(call, headers);
        }

        try {
            var consumerId = validateHeaders(ContextKeys.METADATA_CONSUMER_ID.name(),
                    headers.get(ContextKeys.METADATA_CONSUMER_ID));

            var correlationId = validateHeaders(ContextKeys.METADATA_CORRELATION_ID.name(),
                    headers.get(ContextKeys.METADATA_CORRELATION_ID));

            var ownerId = validateHeaders(ContextKeys.METADATA_STORE_ID.name(),
                    headers.get(ContextKeys.METADATA_STORE_ID));

            var authorization = validateHeaders(ContextKeys.AUTHORIZATION_METADATA_KEY.name(),
                    headers.get(ContextKeys.AUTHORIZATION_METADATA_KEY));

            var token = extractBearerToken(authorization);

            var jwtEmployee = jwtService.validateToken(token);

            setAuthentication(jwtEmployee);

            validateMethodPermission(fullMethodName, jwtEmployee);

            Context context = Context.current()
                    .withValue(ContextKeys.CORRELATION_ID_CONTEXT_KEY, correlationId)
                    .withValue(ContextKeys.CONSUMER_ID_CONTEXT_KEY, consumerId)
                    .withValue(ContextKeys.STORE_ID_CONTEXT_KEY, ownerId)
                    .withValue(ContextKeys.AUTHORIZATION_CONTEXT_KEY, authorization);

            log.debug("gRPC request - CorrelationId: {}, ConsumerId: {}, StoreId: {}",
                    correlationId, consumerId, ownerId);

            return Contexts.interceptCall(context, call, headers, next);

        } catch (StatusRuntimeException e) {
            log.error("Status error: {}", e.getMessage());
            throw e;
        } catch (SecurityException e) {
            log.error("Permission denied: {}", e.getMessage());
            throw Status.PERMISSION_DENIED
                    .withDescription(PERMISSION_DENIED_MESSAGE)
                    .asRuntimeException();
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw Status.UNAUTHENTICATED
                    .withDescription(UNAUTHENTICATED_MESSAGE)
                    .asRuntimeException();
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private String validateHeaders(String header, String value) {
        if (value == null || value.isEmpty()) {
            throw Status.INVALID_ARGUMENT
                    .withDescription(
                            String.format("Header '%s' is required", header)
                    )
                    .asRuntimeException();
        }

        return value;
    }

    private String extractBearerToken(String authorization) {
        if (!authorization.startsWith(BEARER_PREFIX)) {
            throw new IllegalArgumentException("Invalid authorization format");
        }

        return authorization.substring(BEARER_PREFIX.length()).trim();
    }

    private void setAuthentication(JwtEmployee jwtEmployee) {
        List<SimpleGrantedAuthority> authorities = jwtEmployee.getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(jwtEmployee, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void validateMethodPermission(String fullMethodName, JwtEmployee jwtEmployee) {
        String requiredPermission = METHOD_PERMISSIONS.get(fullMethodName);
        log.info("Validate permissions for {} permission required {}", fullMethodName, requiredPermission);
        if (requiredPermission != null) {
            jwtEmployee.validatePermission(requiredPermission);
        }
    }

    private boolean isPublicService(String fullMethodName) {
        return PUBLIC_SERVICE_METHODS.contains(fullMethodName);
    }
}