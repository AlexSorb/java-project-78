package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    private boolean lengthFlag = false;
    private int minLength;
    private boolean containsFlag = false;
    private String searchString;


    public StringSchema required() {
        super.required();
        return this;
    }
    public StringSchema minLength(int minLength) {
        this.minLength = Math.max(minLength, 0);
        lengthFlag = true;
        return this;
    }

    public StringSchema contains(String searchString) {
        containsFlag = true;
        this.searchString = searchString;
        return this;
    }

    public boolean isValid(String string) {
        boolean isValid = super.isValid(string);

        if (lengthFlag) {
            if (string == null) {
                isValid = false;
            } else {
                isValid = string.length() >= minLength;
            }
        }

        if (containsFlag) {
            if (searchString == null || string == null) {
                isValid = false;
            } else {
                isValid = string.contains(searchString);
            }
        }

        return isValid;
    }
}
