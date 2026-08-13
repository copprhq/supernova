package supernova.instance;

/**
 * Exception thrown when trying to register an instance but the instance is already registered.
 */
public class InstanceAlreadyRegistered extends IllegalStateException {

    private static final String MESSAGE =
            "Unable to register an instance because the instance is already registered.";

    InstanceAlreadyRegistered() {
        super(MESSAGE);
    }
}
