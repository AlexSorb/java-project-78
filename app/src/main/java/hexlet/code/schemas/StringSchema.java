package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    private static final String REQUIRED_SCHEMA_NAME = "required";
    private static final String MIN_LENGTH_SCHEMA_NAME = "minLength";
    private static final String CONTAINS_SCHEMA_NAME = "contains";

    @Override
    public final StringSchema required() {
        Predicate<String> predicate = data -> data != null && !data.isEmpty();
        super.checks.put(REQUIRED_SCHEMA_NAME, predicate);
        return this;
    }

    public final StringSchema minLength(int minLength) {

        if (minLength < 0) {
            throw new IllegalArgumentException("Length less than zero");
        }

        Predicate<String> predicate = data -> data != null && data.length() >= minLength;
        super.checks.put(MIN_LENGTH_SCHEMA_NAME, predicate);

        return this;
    }

    public final StringSchema contains(String subString) {
        if (subString == null) {
            throw new IllegalArgumentException("Not null");
        }

        final String searchString = subString;
        Predicate<String> predicate = date -> !(date == null) && date.contains(searchString);
        super.checks.put(CONTAINS_SCHEMA_NAME, predicate);
        return this;
    }
}
