package hexlet.code.schemas;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    private static final String REQUIRED_SCHEMA_NAME = "required";
    private boolean isValid = true;
    @Getter
    private final Map<String, Predicate<T>> namedPredicate;

    public BaseSchema() {
        namedPredicate = new HashMap<>();
    }

    /**
     * Метод добовляет предикат проверки объекта на null.
     * @return BaseSchema
     */
    public BaseSchema<T> required() {

        Predicate<T> predicate = data -> isValid = data != null;
        namedPredicate.put(REQUIRED_SCHEMA_NAME, predicate);
        return this;
    }

    public final boolean isValid(T data) {
        namedPredicate.forEach((s, tPredicate) -> {
            if (isValid) {
                isValid = tPredicate.test(data);
            }
        });
        var result = isValid;
        this.isValid = true;
        return result;
    }

}
