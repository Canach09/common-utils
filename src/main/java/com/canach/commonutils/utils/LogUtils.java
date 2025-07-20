package com.canach.commonutils.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public final class LogUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String GetCallingMethodAndClass() {
        return StackWalker.getInstance()
                .walk(frames -> frames
                        .skip(1) // salta este método (GetCallingMethodAndClass) y trae el nombre de quien lo invocó mas arriba
                        .findFirst()
                        .map(f -> {
                            String className = f.getClassName();
                            String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
                            return simpleClassName + "." + f.getMethodName();
                        })
                        .orElse("Unknown"));
    }

    public static String ToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "Failed to serialize object: " + e.getMessage();
        }
    }

    //    public static void info(String message) {
//        logAndTrace(LogLevel.INFO, message);
//    }
//
//    public static void debug(String message) {
//        logAndTrace(LogLevel.DEBUG, message);
//    }
//
//    public static void warn(String message) {
//        logAndTrace(LogLevel.WARN, message);
//    }
//
//    public static void error(String message) {
//        logAndTrace(LogLevel.ERROR, message);
//    }
//
//    public static void trace(String message) {
//        logAndTrace(LogLevel.TRACE, message);
//    }
//
//    public static void logAndTrace(LogLevel logLevel, String message) {
//        Span currentSpan = Span.current();
//        if (currentSpan.isRecording()) {
//            currentSpan.addEvent(message);
//            setSpanAttributes(currentSpan, logLevel, message);
//        }
//        logMessage(logLevel, message);
//    }
//
//    public static void logAndTrace(LogLevel logLevel, Span span, String message) {
//        if (span != null && span.isRecording()) {
//            span.addEvent(message);
//            setSpanAttributes(span, logLevel, message);
//        }
//        logMessage(logLevel, message);
//    }
//
//    private static void setSpanAttributes(Span span, LogLevel logLevel, String message) {
//        span.setAttribute("log.level", logLevel.name());
//        span.setAttribute("log.message", message);
//
//        // Set span status for errors and warnings
//        switch (logLevel) {
//            case ERROR -> span.setStatus(StatusCode.ERROR, message);
//            case WARN -> span.setAttribute("warning", true);
//            default -> { /* No special handling needed */ }
//        }
//    }
//
    public static String GetStackTraceAsString(Throwable exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
//
//    public static void logAndTrace(LogLevel logLevel, Tracer tracer, String message) {
//        Span span = tracer.spanBuilder(GetSpanName())
//                .startSpan();
//        span.addEvent(message);
//        log(logLevel, message, span);
//    }
//
//    private static void logMessage(LogLevel logLevel, String message) {
//        switch (logLevel) {
//            case TRACE -> log.trace(message);
//            case DEBUG -> log.debug(message);
//            case WARN -> log.warn(message);
//            case ERROR -> log.error(message);
//            default -> log.info(message);
//        }
//    }
//
////    private static void log(LogLevel logLevel, String message, Span span){
////
////        span.setAttribute("message", message);
////        switch (logLevel) {
////            case DEBUG:{
////                log.debug(message);
////                break;
////            }
////            case TRACE:{
////                log.trace(message);
////                break;
////            }
////            case WARN:{
////                log.warn(message);
////                break;
////            }
////            case ERROR:{
////                log.error(message);
////                span.setStatus(StatusCode.ERROR);
////                break;
////            }
////            default: {
////                log.info(message);
////                break;
////            }
////        }
////    }

//public static void logAndTrace(LogLevel logLevel, String message) {
//    Span currentSpan = Span.current();
//    if (currentSpan.isRecording()) {
//        currentSpan.addEvent(message);
//        setSpanAttributes(currentSpan, logLevel, message);
//    }
//    logMessage(logLevel, message);
//}

    /**
     * Creates a new span and logs message as an event
     */

}

