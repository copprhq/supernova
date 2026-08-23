package com.coppr.supernova.functional;

import supernova.annotations.Concept;

/**
 * An immutable container that holds exactly one non-{@code null} value,
 * either a left value or a right value.
 *
 * <p>An {@code Either} can never contain both values or neither value.</p>
 *
 * <p>{@link Either} is only a concept that its design & implementation may change at any time.
 * Either is also may be removed at any time too.</p>
 *
 * @param <L> the type of the left value
 * @param <R> the type of the right value
 *
 * @since 0.0.2
 * @author Izhar Atharzi
 */
@Concept
public class Either<L, R> {

    /**
     * The left value, or {@code null} when this instance contains a right value.
     */
    private final L left;

    /**
     * The right value, or {@code null} when this instance contains a left value.
     */
    private final R right;

    /**
     * Creates an {@code Either} containing either a left or a right value.
     *
     * @param left the left value, or {@code null} if this instance contains a right value
     * @param right the right value, or {@code null} if this instance contains a left value
     * @throws IllegalStateException if both values are {@code null} or both values are non-{@code null}
     */
    private Either(L left, R right) {
        if (left == null && right == null) {
            throw new IllegalStateException(
                    "supernova.functional.Either can't have both left and right empty"
            );
        }

        if (left != null && right != null) {
            throw new IllegalStateException(
                    "supernova.functional.Either can't have both left and right present"
            );
        }

        this.left = left;
        this.right = right;
    }

    /**
     * Creates an {@code Either} containing a left value.
     *
     * @param left the non-{@code null} left value
     * @param <L> the type of the left value
     * @param <R> the type of the right value
     * @return an {@code Either} containing the given left value
     */
    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    /**
     * Creates an {@code Either} containing a right value.
     *
     * @param right the non-{@code null} right value
     * @param <L> the type of the left value
     * @param <R> the type of the right value
     * @return an {@code Either} containing the given right value
     */
    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    /**
     * Checks whether {@link Either} contain only left value.
     *
     * @return {@code true} when left value is not null, and right value is null
     */
    public boolean isLeft() {
        return left != null && right == null;
    }

    /**
     * Checks whether {@link Either} contain only right value.
     *
     * @return {@code true} when right value is not null, and left value is null
     */
    public boolean isRight() {
        return left == null && right != null;
    }

    public L left() {
        if (left == null) throw new NullPointerException("Left is null");
        return left;
    }

    public R right() {
        if (right == null) throw new NullPointerException("Right is null");
        return right;
    }
}