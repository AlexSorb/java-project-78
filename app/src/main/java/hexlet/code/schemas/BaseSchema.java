package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema <T> {

    private boolean isValid = true;
    List<Predicate<T>> checkList;
    Map<String, Predicate<T>> namedPredicate;

    private boolean newisValid = true;

    public BaseSchema () {
        checkList = new ArrayList<Predicate<T>>();
        namedPredicate = new HashMap<>();
    }
    public BaseSchema<T> required() {
        Predicate<T> predicate = data -> isValid = data != null;
        checkList.add(predicate);

        String name = "required";
        namedPredicate.put(name, predicate);
        return this;
    }

    public boolean isValid(T data) {
        checkList.forEach(tPredicate -> {
            var currentTest = tPredicate.test(data);
            if (isValid) {
                isValid = currentTest;
            }

        });

        namedPredicate.forEach((s, tPredicate) -> {
            if (newisValid) {
                newisValid = tPredicate.test(data);
            }
        });

        return isValid && newisValid;
    }

}
