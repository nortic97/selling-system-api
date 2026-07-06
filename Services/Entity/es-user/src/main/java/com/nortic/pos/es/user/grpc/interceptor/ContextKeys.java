package com.nortic.pos.es.user.grpc.interceptor;

import io.grpc.Context;
import io.grpc.Metadata;

public final class ContextKeys {

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

}