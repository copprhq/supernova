package supernova.instance;

/**
 * A container that holds a single instance of a specified type.
 *
 * <p>An {@code Instance} can only be initialized once. Once an instance has
 * been set, subsequent attempts to initialize it will fail.
 *
 * @param <T> the type of the contained instance
 *
 * @since 0.0.2
 * @author Izhar Atharzi
 */
public class Instance<T> {

    private volatile T instance;

    public Instance() {
    }

    /**
     * Initializes this container with the specified instance.
     *
     * <p>This method can only be called once. Calling it after the container
     * has already been initialized will throw an exception.
     *
     * @param instance the instance to contain
     *
     * @return this instance container
     *
     * @throws InstanceAlreadyInitialized if this container has already been initialized
     */
    public Instance<T> set(T instance) {
        if (this.instance != null) throw new InstanceAlreadyInitialized();
        this.instance = instance;
        return this;
    }

    /**
     * Returns the contained instance.
     *
     * @return the contained instance
     *
     * @throws InstanceNotInitialized if this container has not yet been initialized
     */
    public T get() {
        if (instance == null) throw new InstanceNotInitialized();
        return instance;
    }
}