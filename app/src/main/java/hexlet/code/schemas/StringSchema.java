package hexlet.code.schemas;

public class StringSchema {

    private boolean state = true;

    // использовать флаг для проверки в isValid на Null
    private boolean flagRequired = false;

    // использовать флаг для проверки размера в isValid
    // minLength используется для проверки
    private boolean flagLength = false;
    private int minLength;

    // использовать флаг для проверки размера в isValid
    // searchString используется для проверки
    private boolean flagContains = false;
    private String searchString;

    public StringSchema required() {
        flagRequired = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.minLength = Math.max(minLength, 0);
        flagLength = true;
        return this;
    }

    public StringSchema contains(String searchString) {
        flagContains = true;
        this.searchString = searchString;
        return this;
    }

    public boolean isValid(String string) {
        if (flagRequired) {
            state = string != null;
        }

        if (flagLength) {
            if (string == null) {
                state = false;
            } else {
                state = string.length() >= minLength;
            }
        }

        if (flagContains) {
            state = string.contains(searchString);
        }

        return state;
    }
}
