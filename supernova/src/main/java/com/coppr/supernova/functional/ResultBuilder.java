package com.coppr.supernova.functional;

import java.util.ArrayList;
import java.util.List;

/**
 * Object represent builder for the {@link Result}.
 *
 * @author Izhar Atharzi
 * @since 0.0.1
 */
public final class ResultBuilder<T> {

    private final List<Violation> violations;
    private final List<Warning> warnings;

    private T value;

    ResultBuilder() {
        this.violations = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public ResultBuilder<T> warn(Warning warning) {
        this.warnings.add(warning);
        return this;
    }

    public ResultBuilder<T> warn(List<Warning> warnings) {
        this.warnings.addAll(warnings);
        return this;
    }

    public ResultBuilder<T> violate(Violation violation) {
        this.violations.add(violation);
        return this;
    }

    public ResultBuilder<T> violate(List<Violation> violations) {
        this.violations.addAll(violations);
        return this;
    }

    public ResultBuilder<T> value(T value) {
        this.value = value;
        return this;
    }

    /**
     * Valuing the Result with empty value which mostly used in void type result.
     */
    public ResultBuilder<T> emptyValue() {
        this.value = null;
        return this;
    }

    public Result<T> build() {
        return Result.of(value, violations, warnings, null);
    }
}