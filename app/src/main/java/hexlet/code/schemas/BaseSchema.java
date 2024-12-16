package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema <T> {

    private boolean isValid = true;
    boolean flagRequired = false;
    List<Predicate<T>> checkList;


    public BaseSchema () {
        checkList = new ArrayList<Predicate<T>>();
    }
    public BaseSchema<T> required() {
        Predicate<T> predicate = data -> isValid = data != null;
        flagRequired = true;
        return this;
    }

    public boolean isValid(T data) {
        checkList.forEach(tPredicate -> {
            var currentTest = tPredicate.test(data);
            if (isValid) {
                isValid = currentTest;
            }

        });
//        if (isValid && flagRequired) {
//            isValid = data != null;
//        }
        return isValid;
    }
}
