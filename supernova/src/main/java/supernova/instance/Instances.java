package supernova.instance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides access to globally registered {@link Instance} containers.
 *
 * <p>Each instance type can only be registered once. Registered instances
 * can be retrieved by their corresponding {@link Class} object.
 *
 * <p>This class is thread-safe and can be safely accessed from multiple
 * threads.
 *
 * @since 0.0.2
 * @author Izhar Atharzi
 */
public final class Instances {

    private static final Map<Class<?>, Instance<?>> INSTANCES = new ConcurrentHashMap<>();

    private Instances() {
    }

    /**
     * Registers a new {@link Instance} container for the specified type.
     *
     * <p>A type can only be registered once. Attempting to register a type
     * that has already been registered will throw an exception.
     *
     * @param type the type for which the instance container is registered
     * @param <T> the type of the instance
     *
     * @return the newly registered instance container
     *
     * @throws InstanceAlreadyRegistered if an instance container has already
     *         been registered for the specified type
     */
    public static <T> Instance<T> register(Class<T> type) {
        Instance<T> instance = new Instance<>();
        Instance<?> existing = INSTANCES.putIfAbsent(type, instance);

        if (existing != null) {
            throw new InstanceAlreadyRegistered();
        }

        return instance;
    }

    /**
     * Retrieves the {@link Instance} container registered for the specified type.
     *
     * <p>This method does not initialize or register an instance container.
     * If no container has been registered for the specified type, {@code null}
     * is returned.
     *
     * @param type the type whose instance container should be retrieved
     * @param <T> the type of the instance
     *
     * @return the registered instance container, or {@code null} if no
     *         container has been registered for the specified type
     */
    @SuppressWarnings("unchecked")
    public static <T> Instance<T> getInstance(Class<T> type) {
        if (!INSTANCES.containsKey(type)) {
            throw new InstanceNotRegistered();
        }

        return (Instance<T>) INSTANCES.get(type);
    }
}