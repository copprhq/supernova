package com.coppr.supernova.functional;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * An immutable container representing the outcome of an operation that may {@code null}able value
 * and contain a zero or more violation. A result that have no violations and have no interruption is
 * stated as successful; otherwise it is stated as violated/interrupted.
 *
 * <p>A result can have interruption where interruption in these terms means error. Which can only
 * contain one interruption. Interruption is used when in a flow of result is interrupted by
 * unrecoverable fail. e.g. {@code DATABASE_TIMEOUT}, {@code CONNECTION_TIMEOUT},
 * {@code ENTITY_NOT_FOUND}, etc.</p>
 *
 * <p>A result can also have warnings which don't cause any fatal errors for the operation but is
 * important enough to worth knowing.</p>
 *
 * <p>A violated result is also can be interpreted as failed contract from operation.</p>
 *
 * <p>Violation represent as failed contract and can contain any type of object such as string,
 * integer, custom object, etc.</p>
 *
 * <p>You can picture the differences of violation and interruption by: violation is input fault
 * while interruption is system fault.</p>
 *
 * @param <T> the type of the contained value.
 * @author Izhar Atharzi
 * @since 0.0.1
 */
public final class Result<T> {

    /**
     * Shared instance for empty violation collection.
     */
    private static final List<Violation> EMPTY_VIOLATIONS = Collections.emptyList();

    /**
     * Shared instance for empty warning collection.
     */
    private static final List<Warning> EMPTY_WARNINGS = Collections.emptyList();

    /**
     * Shared instance for {@code successful()}.
     */
    private static final Result<Void> EMPTY_SUCCESSFUL = new Result<>(null, EMPTY_VIOLATIONS, EMPTY_WARNINGS, null);

    /**
     * Nullable value which does not trigger anything in result.
     */
    private final T value;

    /**
     * Collection of violations; if empty, state as successful.
     */
    private final List<Violation> violations;

    /**
     * Collections of warnings
     */
    private final List<Warning> warnings;

    /**
     * Interruption of Result.
     */
    private final Throwable interruption;

    /**
     * A shared helper for creating a singleton list.
     *
     * @param element the instance of the element
     * @return singleton list
     * @param <E> the type of the element
     */
    private static <E> List<E> singleton(E element) {
        return Collections.singletonList(element);
    }

    /**
     * Constructs an instance with value and violations.
     *
     * @param value the value of the result
     * @param violations collection of violations; if null, it is treated as an empty collection.
     */
    private Result(T value, List<Violation> violations, List<Warning> warnings, Throwable interruption) {
        this.value = value;

        this.violations = violations == null || violations.isEmpty()
                ? EMPTY_VIOLATIONS
                : List.copyOf(violations);

        this.warnings = warnings == null || warnings.isEmpty()
                ? EMPTY_WARNINGS
                : List.copyOf(warnings);

        this.interruption = interruption;
    }

    /**
     * Creates a {@link Result} builder.
     *
     * @return a Result builder
     * @param <T> the type of value
     */
    public static <T> ResultBuilder<T> builder() {
        return new ResultBuilder<>();
    }

    /**
     * Returns a successful {@link Result} with a value.
     *
     * @param value The value
     * @return a successful {@link Result} with the value
     * @param <T> the type of value
     */
    public static <T> Result<T> successful(T value) {
        return new Result<>(value, EMPTY_VIOLATIONS, EMPTY_WARNINGS, null);
    }

    /**
     * Returns a successful {@link Result} with a value and a warning.
     *
     * @param value the object of the value
     * @param warning the instance of the warning
     * @return a successful {@link Result} with a value and a warning
     * @param <T> the type of value
     */
    public static <T> Result<T> successful(T value, Warning warning) {
        return new Result<>(value, EMPTY_VIOLATIONS, singleton(warning), null);
    }

    /**
     * Returns a successful {@link Result} with a value and list of warnings.
     *
     * @param value the object of the value
     * @param warnings list of warnings
     * @return a successful {@link Result} with a value and a warning
     * @param <T> the type of value
     */
    public static <T> Result<T> successful(T value, List<Warning> warnings) {
        return new Result<>(value, EMPTY_VIOLATIONS, warnings, null);
    }

    /**
     * Returns a successful {@link Result} for void type.
     *
     * @return a successful {@link Result} for void type
     */
    public static Result<Void> successful() {
        return EMPTY_SUCCESSFUL;
    }

    /**
     * Returns a successful {@link Result} for void type with a warning.
     *
     * @param warning the instance of the warning
     * @return a successful {@link Result}
     */
    public static Result<Void> successful(Warning warning) {
        return new Result<>(null, EMPTY_VIOLATIONS, singleton(warning), null);
    }

    /**
     * Returns a successful {@link Result} for void type with list of warnings.
     *
     * @param warnings list of warnings
     * @return a successful {@link Result}
     */
    public static Result<Void> successful(List<Warning> warnings) {
        return new Result<>(null, EMPTY_VIOLATIONS, warnings, null);
    }

    /**
     * Returns violated {@link Result} with collection of violations.
     *
     * @param violations list of violations
     * @return violated {@link Result} with collection of violations
     * @param <T> the type of value
     */
    public static <T> Result<T> violated(List<Violation> violations) {
        return new Result<>(
                null,
                violations,
                EMPTY_WARNINGS,
                null
        );
    }

    /**
     * Returns violated {@link Result} with single violation.
     *
     * @param violation a violation
     * @return violated {@link Result} with single violation.
     * @param <T> the type of value
     */
    public static <T> Result<T> violated(Violation violation) {
        return violated(singleton(violation));
    }

    /**
     * Creates a result with all the params.
     */
    public static <T> Result<T> of(T value, List<Violation> violations, List<Warning> warnings) {
        return new Result<>(value, violations, warnings, null);
    }

    /**
     * Interrupt a result with {@code throwable}.
     */
    public static <T> Result<T> interrupted(Throwable interruption) {
        return new Result<>(null, EMPTY_VIOLATIONS, EMPTY_WARNINGS, interruption);
    }

    /**
     * Checks if the result is successful.
     *
     * @return {@code true} if the result contains no violations.
     */
    public boolean isSuccessful() {
        return violations.isEmpty() && interruption == null;
    }

    /**
     * Checks if the result is failed or violated.
     *
     * @return {@code true} if the result contains violations.
     */
    public boolean isViolated() {
        return !violations.isEmpty();
    }

    /**
     * Checks if the result have warnings.
     *
     * @return {@code true} if the result contains warnings.
     */
    public boolean hasWarning() {
        return !warnings.isEmpty();
    }

    public boolean isInterrupted() {
        return interruption != null;
    }

    /**
     * Checks if the result has violation specified by the violation code.
     *
     * @param code the violation code
     * @return {@code true} if there is a violation specified by the code
     */
    public boolean hasViolation(String code) {
        return violations.stream().anyMatch(violation ->
                Objects.equals(violation.getCode(), code));
    }

    /**
     * Checks if the result has warning specified by the warning code.
     *
     * @param code the warning code
     * @return {@code true} if there is a warning specified by the code
     */
    public boolean hasWarning(String code) {
        return warnings.stream().anyMatch(warning ->
                Objects.equals(warning.code(), code));
    }

    /**
     * If result is successful then performs the given action with the reference, otherwise
     * perform nothing.
     */
    public Result<T> whenSuccessful(Consumer<? super T> action) {
        if (violations.isEmpty() && interruption == null) {
            action.accept(value);
        }

        return this;
    }

    /**
     * If result is violated then performs the given action with the violation, otherwise
     * perform nothing.
     */
    public Result<T> whenViolated(Consumer<Collection<Violation>> action) {
        if (!violations.isEmpty()) {
            action.accept(violations);
        }

        return this;
    }

    /**
     * If result have warning then performs the given action with the warnings, otherwise
     * perform nothing.
     */
    public Result<T> whenWarning(Consumer<Collection<Warning>> action) {
        if (!warnings.isEmpty()) {
            action.accept(warnings);
        }

        return this;
    }

    public Result<T> whenInterrupted(Consumer<Throwable> action) {
        if (interruption != null) {
            action.accept(interruption);
        }

        return this;
    }

    /**
     * Recovers a violated result by producing a replacement value from its violations.
     *
     * <p>If this result is successful, this method returns the current result unchanged
     * and the recovery function is not invoked.</p>
     *
     * @param recovery the function used to produce a replacement value from the violations
     * @return this result if successful; otherwise, a successful result containing the
     *         value produced by the recovery function
     * @throws NullPointerException if {@code recovery} is {@code null}
     */
    public Result<T> recover(Function<? super List<Violation>, ? extends T> recovery) {
        Objects.requireNonNull(recovery, "recovery");

        if (isInterrupted()) {
            return this;
        }

        if (isSuccessful()) {
            return this;
        }

        return Result.successful(recovery.apply(violations));
    }

    /**
     * Recovers a violated result by producing a replacement result from its violations.
     *
     * <p>If this result is successful, this method returns the current result unchanged
     * and the recovery function is not invoked.</p>
     *
     * @param recovery the function used to produce a replacement result from the violations
     * @return this result if successful; otherwise, the result produced by the recovery function
     * @throws NullPointerException if {@code recovery} is {@code null}, or if the recovery
     *         function returns {@code null}
     */
    public Result<T> recoverWith(Function<? super List<Violation>, ? extends Result<T>> recovery) {
        Objects.requireNonNull(recovery, "recovery");

        if (isInterrupted()) {
            return this;
        }

        if (isSuccessful()) {
            return this;
        }

        return Objects.requireNonNull(recovery.apply(violations));
    }

    /**
     * If the result is successful returns the value.
     *
     * @return the instance of the value
     */
    public T get() throws InterruptedException, ViolatedException {
        if (interruption != null) {
            throw new InterruptedException(interruption);
        }

        if (!violations.isEmpty()) {
            throw new ViolatedException(violations);
        }

        return value;
    }

    /**
     * Returns the value if successful.
     *
     * <p>Unlike {@link #get()}, this method does not declare checked exceptions.
     * If interrupted, the underlying exception is rethrown as an unchecked exception.</p>
     *
     * @return the instance of the value
     * @throws RuntimeException if the result was interrupted
     */
    public T join() {
        if (interruption != null) {
            if (interruption instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new RuntimeException(interruption);
        }

        if (!violations.isEmpty()) {
            throw new RuntimeException(new ViolatedException(violations));
        }

        return value;
    }

    /**
     * Returns the value if successful; otherwise returns the default value.
     *
     * @param defaultValue the instance of the default value
     * @return the value if successful, or defaultValue if violated or interrupted
     */
    public T getOrElse(T defaultValue) {
        if (!isSuccessful()) {
            return defaultValue;
        }
        return value;
    }

    /**
     * Returns a sequential {@link Stream} containing the value if this result is
     * successful. Otherwise, returns an empty stream.
     *
     * <p>If this result is successful and its value is {@code null}, an empty
     * stream is returned.
     *
     * @return a stream containing the value if this result is successful and
     *         non-{@code null}; otherwise an empty stream
     */
    public Stream<T> stream() {
        return violations.isEmpty() && interruption == null
                ? Stream.ofNullable(value)
                : Stream.empty();
    }

    public <U> Result<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isSuccessful()) {
            return new Result<>(null, violations, warnings, interruption);
        }
        return Result.of(mapper.apply(value), EMPTY_VIOLATIONS, warnings);
    }

    public <U> Result<U> flatMap(Function<? super T, Result<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isSuccessful()) {
            return new Result<>(null, violations, warnings, interruption);
        }
        return Objects.requireNonNull(mapper.apply(value));
    }

    /**
     * Gets a collection of violations.
     *
     * @return unmodifiable collection of violations
     */
    public List<Violation> violations() {
        return violations;
    }

    /**
     * Gets a collection of warnings.
     *
     * @return unmodifiable collection of warnings
     */
    public List<Warning> warnings() {
        return warnings;
    }

    public Throwable getInterruption() {
        return interruption;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Result<?> other)) return false;

        return Objects.equals(value, other.value)
                && Objects.equals(violations, other.violations)
                && Objects.equals(warnings, other.warnings)
                && Objects.equals(interruption, other.interruption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, violations, warnings, interruption);
    }

    @Override
    public String toString() {
        if (isInterrupted()) {
            return interruption.toString();
        }

        StringJoiner joiner = new StringJoiner(", ", "Result[", "]");

        joiner.add("value=" + value);

        if (!violations.isEmpty()) {
            joiner.add("violations=" + violations);
        }

        if (!warnings.isEmpty()) {
            joiner.add("warnings=" + warnings);
        }

        return joiner.toString();
    }
}
