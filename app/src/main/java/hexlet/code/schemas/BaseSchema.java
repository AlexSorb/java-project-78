package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema <T> {

    private boolean isValid = true;
    Map<String, Predicate<T>> namedPredicate;

    public BaseSchema () {
        namedPredicate = new HashMap<>();
    }
    public BaseSchema<T> required() {
        Predicate<T> predicate = data -> isValid = data != null;
        String name = "required";
        namedPredicate.put(name, predicate);
        return this;
    }

    public boolean isValid(T data) {
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
