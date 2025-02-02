package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    private final static String REQUIRED_SCHEMA_NAME = "required";
    private final static String MIN_LENGTH_SCHEMA_NAME = "minLength";
    private final static String CONTAINS_SCHEMA_NAME = "contains";

    @Override
    public StringSchema required() {
        Predicate<String> predicate = data -> data != null && !data.isEmpty();
        namedPredicate.put(REQUIRED_SCHEMA_NAME, predicate);
        return this;
    }

    public StringSchema minLength(int minLength) {

        if (minLength < 0) {
            throw new IllegalArgumentException("Length less than zero");
        }

        Predicate<String> predicate = data -> data != null && data.length() >= minLength;
        super.namedPredicate.put(MIN_LENGTH_SCHEMA_NAME, predicate);

        return this;
    }

    public StringSchema contains(String subString) {
        if (subString == null) {
            throw new IllegalArgumentException("Not null");
        }

        final String searchString = subString;
        Predicate<String> predicate = date -> !(date == null) && date.contains(searchString);
        super.namedPredicate.put(CONTAINS_SCHEMA_NAME, predicate);
        return this;
    }

}
