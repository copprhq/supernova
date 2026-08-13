package supernova.instance;

/**
 * Exception thrown when trying to retrieve the instance but the instance is not yet initialized.
 */
public class InstanceNotInitialized extends IllegalStateException {

    private static final String MESSAGE =
            "Unable to retrieve the instance because the instance is not yet initialized";

    InstanceNotInitialized() {
        super(MESSAGE);
    }
}
