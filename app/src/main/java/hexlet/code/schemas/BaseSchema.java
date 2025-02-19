package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    private static final String REQUIRED_SCHEMA_NAME = "required";
    private Map<String, Predicate> checks;

    public BaseSchema() {
        checks = new LinkedHashMap<>();
    }

    /**
     * Функция добовляет проверку в LinkedHashMap проверок.
     *
     * @param checkName - название проверки
     * @param check - предикат проверки на валидность
     */
    protected void addCheck(String checkName, Predicate check) {
        if (check == null) {
            throw new IllegalArgumentException("Predicate can't be null");
        }

        if (checkName.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null");
        }

        checks.put(checkName, check);
    }

    /**
     * Метод добовляет предикат проверки объекта на null.
     * @return BaseSchema
     */
    public BaseSchema<T> required() {

        Predicate<T> predicate = data -> data != null;
        addCheck(REQUIRED_SCHEMA_NAME, predicate);
        return this;
    }

    public final boolean isValid(T data) {
        Predicate<T> predicate = (t) -> checks.values().stream().allMatch(p -> p.test(t));
        return predicate.test(data);
    }

}
