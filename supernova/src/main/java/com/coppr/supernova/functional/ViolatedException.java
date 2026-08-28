package com.coppr.supernova.functional;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Thrown when operation of {@link Result} is violated.
 */
public class ViolatedException extends Exception {

    private final Collection<Violation> violations;

    /**
     * The default constructor.
     *
     * @param violations Collection of violations.
     */
    public ViolatedException(Collection<Violation> violations) {
        super("com.supernova.functional.Result of an operation is violated with: " + violations);
        this.violations = violations;
    }

    /**
     * Returns the result's collection of violations.
     *
     * @return the result's collection of violations
     */
    public Collection<Violation> getViolations() {
        return violations;
    }

    private static String toMessages(List<Violation> violations) {
        StringJoiner stringJoiner = new StringJoiner(", ");

        for (Violation violation : violations) {
            stringJoiner.add(violation.getCode() + "=" + violation.getMessage());
        }

        return stringJoiner.toString();
    }
}