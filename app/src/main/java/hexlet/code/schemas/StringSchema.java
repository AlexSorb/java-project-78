package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        Predicate<String> predicate = data -> data != null && !data.isEmpty();
        String predicateName = "required";
        namedPredicate.put(predicateName, predicate);
        return this;
    }

    public StringSchema minLength(int minLength) {

        if (minLength < 0) {
            throw new IllegalArgumentException("Length less than zero");
        }

        String predicateName = "minLength";
        Predicate<String> predicate = data -> data != null && data.length() >= minLength;
        super.namedPredicate.put(predicateName, predicate);

        return this;
    }

    public StringSchema contains(String subString) {
        if (subString == null) {
            throw new IllegalArgumentException("Not null");
        }

        final String searchString = subString;
        Predicate<String> predicate = date -> !(date == null) && date.contains(searchString);
        String predicateName = "contains";
        super.namedPredicate.put(predicateName, predicate);
        return this;
    }

}
