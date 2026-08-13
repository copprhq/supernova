package supernova.functional;

/**
 * Represent object as a warning of {@link Result} that does not affect the operation of the {@link Result} but
 * is worth enough for developer to notice.
 *
 * @param code
 * @param message
 *
 * @author Izhar Atharzi
 * @since 0.0.1
 */
public record Warning(String code, String message) {

    public static Warning of(String code, String message) {
        return new Warning(code, message);
    }
}