package com.canach.commonutils.exception.enums.plan;

public enum PlanType {
    UNLIMITED_ACCESS,   // Acceso ilimitado por X días
    CLASS_PACKAGE, // Plans based on number of classes with no expiration
    TIME_BASED,    // Plans based on time periods (2 weeks, 1 month, etc.)
    CLASS_BASED    // Plans based on number of classes with validity period
}
