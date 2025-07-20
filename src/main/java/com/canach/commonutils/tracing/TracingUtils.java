package com.canach.commonutils.tracing;

import com.canach.commonutils.utils.LogUtils;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class TracingUtils {

    public static void setNewSpanAttribute(String key, String value){
        Span.current().setAttribute(key, value);
    }

    public static void setNewSpanAttribute(String key, long value){
        Span.current().setAttribute(key, value);
    }

    public static void setNewSpanAttribute(String key, double value){
        Span.current().setAttribute(key, value);
    }

    public static void setNewSpanAttribute(String key, boolean value){
        Span.current().setAttribute(key, value);
    }

    public static void logAndTrace(LogLevel logLevel, String message) {
        Span currentSpan = Span.current();
        if (currentSpan.isRecording()) {
            currentSpan.addEvent(message);
            setSpanAttributes(currentSpan, logLevel, message);
        }
        logMessage(logLevel, message);
    }

    public static void logAndTraceAsJson(LogLevel logLevel, Object messageObj) {
        final var message = LogUtils.ToJson(messageObj);
        logAndTrace(logLevel, message);
    }

    private static String getSpanName() {
        return StackWalker.getInstance()
                .walk(frames -> frames
                        .skip(2) // salta este método (GetCallingMethodAndClass) y trae el nombre de quien lo invocó mas arriba
                        .findFirst()
                        .map(f -> {
                            String className = f.getClassName();
                            String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
                            return simpleClassName + "." + f.getMethodName();
                        })
                        .orElse("Unknown"));
    }

    public static void logAndTraceWithNewSpan(LogLevel logLevel, Tracer tracer, String message) {
        final var spanName = getSpanName();

        logAndTraceWithSpan(logLevel, tracer, message, spanName);
    }

    public static void logAndTraceWithSpan(LogLevel logLevel, Tracer tracer, String message, String spanName) {

        Span span = tracer.spanBuilder(spanName).startSpan();

        try (Scope scope = span.makeCurrent()) {
            span.addEvent(message);
            setSpanAttributes(span, logLevel, message);
            logMessage(logLevel, message);
        } finally {
            span.end();
        }
    }

    public static void logAndTraceAsJsonWithNewSpan(LogLevel logLevel, Tracer tracer, Object messageObj) {
        final var spanName = getSpanName();
        logAndTraceAsJsonWithSpan(logLevel, tracer, messageObj, spanName);
    }

    public static void logAndTraceAsJsonWithSpan(LogLevel logLevel, Tracer tracer, Object messageObj, String spanName) {
        final var message = LogUtils.ToJson(messageObj);
        logAndTraceWithSpan(logLevel, tracer, message, spanName);
    }


    private static void setSpanAttributes(Span span, LogLevel logLevel, String message) {
        span.setAttribute("level", logLevel.name());
        switch (logLevel) {
            case ERROR -> span.setStatus(StatusCode.ERROR, message);
            case WARN -> span.setAttribute("warning", true);
            default -> {}
        }
    }

    private static void logMessage(LogLevel logLevel, String message) {
        switch (logLevel) {
            case TRACE -> log.trace(message);
            case DEBUG -> log.debug(message);
            case WARN -> log.warn(message);
            case ERROR -> log.error(message);
            default -> log.info(message);
        }
    }

    public static void info(String message) {
        logAndTrace(LogLevel.INFO, message);
    }

    public static void debug(String message) {
        logAndTrace(LogLevel.DEBUG, message);
    }

    public static void warn(String message) {
        logAndTrace(LogLevel.WARN, message);
    }

    public static void error(String message) {
        logAndTrace(LogLevel.ERROR, message);
    }

    public static void trace(String message) {
        logAndTrace(LogLevel.TRACE, message);
    }

    // Utility methods with exception handling
    public static void error(String message, Throwable throwable) {
        Span currentSpan = Span.current();
        if (currentSpan.isRecording()) {
            currentSpan.recordException(throwable);
            currentSpan.setStatus(StatusCode.ERROR, message);
            currentSpan.addEvent(message);
        }
        log.error(message, throwable);
    }

//    public static void warn(String message, Throwable throwable) {
//        Span currentSpan = Span.current();
//        if (currentSpan.isRecording()) {
//            currentSpan.recordException(throwable);
//            currentSpan.setAttribute("warning", true);
//            currentSpan.addEvent(message);
//        }
//        log.warn(message, throwable);
//    }
}

