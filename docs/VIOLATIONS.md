# Violations
Collection of common violations that may be useful for `Result`.

**Example Use**
```java
public Result<String> get(String name) {
    return Result.violated(Violations.entityNotFound("John Doe"));
}
```



# Values

- INVALID_VALUE
- INVALID_FORMAT
- MISSING_VALUE
- VALUE_MISMATCH



## Others

- INVALID_CONTRACT
- INVALID_STATE
- CONFLICT
- INTERNAL
