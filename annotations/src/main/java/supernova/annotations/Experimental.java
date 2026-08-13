package supernova.annotations;

import java.lang.annotation.*;

/**
 * Indicates that the annotated API is unstable and not recommended for production use.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Experimental {
}
