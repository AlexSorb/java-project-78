package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema <T> {

    private boolean isValid = true;
    boolean flagRequired = false;
    List<Predicate<T>> checkList;
    Map<String, Predicate<T>> checkMap;


    public BaseSchema () {
        checkList = new ArrayList<Predicate<T>>();
        checkMap = new HashMap<>();
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

    private void addPredicate(String predicateName, Predicate<T> pred) {
        this.checkMap.put(predicateName, pred);
    }
}
