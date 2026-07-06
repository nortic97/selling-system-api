package com.nortic.pos.es.user.grpc.interceptor;

import io.grpc.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.GlobalServerInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@GlobalServerInterceptor
@RequiredArgsConstructor
public class GrpcInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next
    ) {

        var consumerId = validateHeaders(ContextKeys.METADATA_CONSUMER_ID.name(),
                headers.get(ContextKeys.METADATA_CONSUMER_ID));

        var correlationId = validateHeaders(ContextKeys.METADATA_CORRELATION_ID.name(),
                headers.get(ContextKeys.METADATA_CORRELATION_ID));

        var token = validateHeaders(ContextKeys.AUTHORIZATION_METADATA_KEY.name(),
                headers.get(ContextKeys.AUTHORIZATION_METADATA_KEY));

        Context context = Context.current()
                .withValue(ContextKeys.CORRELATION_ID_CONTEXT_KEY, correlationId)
                .withValue(ContextKeys.CONSUMER_ID_CONTEXT_KEY, consumerId)
                .withValue(ContextKeys.AUTHORIZATION_CONTEXT_KEY, token);

        log.debug("gRPC request - CorrelationId: {}, ConsumerId: {}",
                correlationId, consumerId);

        return Contexts.interceptCall(context, call, headers, next);
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
}