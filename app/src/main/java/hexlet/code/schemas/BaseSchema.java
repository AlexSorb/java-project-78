package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    private static final String REQUIRED_SCHEMA_NAME = "required";
    private boolean isValid = true;
    protected final Map<String, Predicate<T>> checks;

    public BaseSchema() {
        checks = new LinkedHashMap<>();
    }

    /**
     * Метод добовляет предикат проверки объекта на null.
     * @return BaseSchema
     */
    public BaseSchema<T> required() {

        Predicate<T> predicate = data -> isValid = data != null;
        checks.put(REQUIRED_SCHEMA_NAME, predicate);
        return this;
    }

    public final boolean isValid(T data) {
        checks.forEach((s, tPredicate) -> {
            if (isValid) {
                isValid = tPredicate.test(data);
            }
        });
        var result = isValid;
        this.isValid = true;
        return result;
    }

}
