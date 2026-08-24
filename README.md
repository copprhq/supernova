# Supernova: Coppr Commons Libraries for Java

[![](https://jitpack.io/v/copprhq/supernova.svg)](https://jitpack.io/#copprhq/supernova)



Supernova is a commons libraries from Coppr that introduces new functional 
(such as result), pagination, and more!



## Adding Supernova to your project

To add Supernova to Maven, use the following:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.copprhq</groupId>
    <artifactId>supernova</artifactId>
    <version>Tag</version>
</dependency>
```

To add Supernova to Gradle

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
        mavenCentral()
		maven { url 'https://jitpack.io' }
    }
}
```

```gradle
dependencies {
    implementation 'com.github.copprhq:supernova:Tag'
}
```



## Documentations
Result have common violations that may be useful which you can read in
[VIOLATIONS](./docs/VIOLATIONS.md)




## Important

1.  An API marked with `@Concept` annotation is meant to 
    be only a concept and which may or may not have implementation. 
    An API with ``@Concept`` is also sometimes is just a temporary
    API design which only implement something that is later will be 
    improved or even be removed.

2.  An API marked with `@Experimental` annotation is unstable and not 
    recommended for production use.