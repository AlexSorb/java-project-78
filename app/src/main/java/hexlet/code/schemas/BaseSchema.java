package hexlet.code.schemas;

public class BaseSchema <T> {
    boolean flagRequired = false;
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
