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
        if (searchString == null) {
            throw new IllegalArgumentException("Not null");
        }

        containsFlag = true;
        this.searchString = searchString;
        return this;
    }

    public boolean isValid(String string) {
        boolean isValid = super.isValid(string);

        if(isValid && flagRequired) {
           isValid = string != null && !string.isEmpty();
        }

        if (isValid && lengthFlag) {
            isValid = string != null && string.length() >= minLength;
        }

        if (isValid && containsFlag) {
            isValid = !(searchString == null || string == null) && string.contains(searchString);
        }

        return isValid;
    }
}
