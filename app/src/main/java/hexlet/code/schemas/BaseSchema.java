package hexlet.code.schemas;

import java.util.List;

public class BaseSchema <T> {
    boolean flagRequired = false;
    List<String> checkList;
    public BaseSchema<T> required() {
        flagRequired = true;
        return this;
    }

    public boolean isValid(T data) {
        boolean isValid = true;
        if (isValid && flagRequired) {
            isValid = data != null;
        }
        return isValid;
    }
}
