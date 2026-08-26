# The Plan for Supernova



## Rename the Supernova package (DONE)

The current package of Supernova is `supernova`. While this package name is
clean and simple, using a generic top-level package increases the risk of
package-name collisions with other libraries.

To provide a unique namespace and better identify Supernova as a Coppr
project, the package will be renamed to `com.coppr.supernova`.


### Main Issue

The `supernova` package is a generic top-level namespace and may conflict
with packages from other libraries or applications.


### Decision

Rename the root package from: `supernova` to: `com.coppr.supernova`


### Impacts

- Imports: Applications or projects that use Supernova will have trouble with imports


### Conclusion

Supernova will use `com.coppr.supernova` as its root package going forward.

## Result's Interruption (done)