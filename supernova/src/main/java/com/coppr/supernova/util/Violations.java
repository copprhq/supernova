package com.coppr.supernova.util;

import com.coppr.supernova.functional.Violation;

/**
 * Utility methods for {@link Violation}.
 *
 * @since 0.0.2
 */
public final class Violations {

    private Violations() {
        throw new AssertionError("Cannot create supernova.util.Violations instance");
    }

    public static Violation invalidValue(String valueName, String reason) {
        return Violation.of(
                "INVALID_VALUE",
                "Value \"" + valueName + "\" is invalid: " + reason
        );
    }

    public static Violation missingValue(String valueName) {
        return Violation.of(
                "MISSING_VALUE",
                "Value " + valueName + " is missing"
        );
    }

    public static Violation valueMismatch(String valueName) {
        return Violation.of(
                "VALUE_MISMATCH",
                "Value " + valueName + " mismatch"
        );
    }

    /**
     * Creates a violation indicating that a conflict occurred.
     *
     * @param object the object associated with the conflict
     * @return a violation with code {@code CONFLICT}
     */
    public static Violation conflict(Object object) {
        return Violation.of(
                "CONFLICT",
                "A conflict occurred",
                object
        );
    }

    public static Violation internal(Object object) {
        return Violation.of(
                "INTERNAL",
                "Internal error",
                object
        );
    }
}