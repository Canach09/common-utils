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

    public static String GetStackTraceAsString(Throwable exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

