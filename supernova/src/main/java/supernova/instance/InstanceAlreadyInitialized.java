package supernova.instance;

/**
 * Exception thrown when trying to initialize the instance but the instance is already initialized.
 */
public class InstanceAlreadyInitialized extends IllegalStateException {

    private static final String MESSAGE =
            "Unable to initialize the instance because the instance is already initialized";

    InstanceAlreadyInitialized() {
        super(MESSAGE);
    }
}