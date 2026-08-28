package com.coppr.supernova.functional;

import java.util.Objects;

/**
 * Violation represent as error that contain error field and error message.
 *
 * @author Izhar Atharzi
 * @since 0.0.1
 */
public class Violation {

    // ----------
    // Default Violations
    // ----------
    public static final Builder INVALID_VALUE = builder()
            .status(400)
            .code("INVALID_VALUE");
    public static final Builder MISSING_VALUE = builder()
            .status(400)
            .code("MISSING_VALUE");
    public static final Builder VALUE_MISMATCH = builder()
            .status(400)
            .code("VALUE_MISMATCH");
    public static final Builder CONFLICT = builder()
            .status(409)
            .code("CONFLICT");
    public static final Builder INTERNAL = builder()
            .status(500)
            .code("INTERNAL");

    private final int status;
    private final String code;
    private final String message;
    private final Object object;

    /**
     * Creates violation builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates violation from object only.
     */
    public static Violation of(Object object) {
        return new Violation(0, null, null, object);
    }

    /**
     * Creates violation from code and message.
     */
    public static Violation of(int status, String code, String message) {
        return new Violation(status, code, message, null);
    }

    /**
     * Creates violation from all variables.
     */
    public static Violation of(int status, String code, String message, Object object) {
        return new Violation(status, code, message, object);
    }

    /**
     * Construct all the fields.
     */
    private Violation(int status, String code, String message, Object object) {
        this.status = status;
        this.object = object;
        this.code = Objects.requireNonNullElse(code, "");
        this.message = Objects.requireNonNullElse(message, "The result is violated");
    }

    /**
     * Gets the status code of the violation.
     *
     * @return status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets the code of the violation.
     *
     * <p>It could be anything, e.g. "ACCOUNT_NOT_FOUND".</p>
     *
     * @return violation code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the message of the violation.
     *
     * @return violation message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets a nullable object of the violation that might have to hold, e.g. Exception/Throwable.
     *
     * @return violation object.
     */
    public Object getObject() {
        return object;
    }

    /**
     * Builder for violation
     */
    public static final class Builder {

        private Object object;
        private int status;
        private String code;
        private String message;

        public Builder() {
        }

        public Builder object(Object object) {
            this.object = object;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Violation build() {
            return new Violation(status, code, message, object);
        }
    }
}