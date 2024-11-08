package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    private boolean flagLength = false;
    private int minLength;
    private boolean flagContains = false;
    private String searchString;


    public StringSchema required() {
        super.required();
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
        boolean isValid = super.isValid(string);

        if (flagLength) {
            if (string == null) {
                isValid = false;
            } else {
                isValid = string.length() >= minLength;
            }
        }

        if (flagContains) {
            if (searchString == null || string == null) {
                isValid = false;
            } else {
                isValid = string.contains(searchString);
            }
        }

        return isValid;
    }
}
