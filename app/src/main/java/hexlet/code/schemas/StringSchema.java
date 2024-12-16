package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }
    public StringSchema minLength(int minLength) {
        final int min = Math.max(minLength, 0);

        Predicate<String> predicate = data -> data != null && data.length() >= min;
        super.checkList.add(predicate);
        return this;
    }

    public StringSchema contains(String subString) {
        if (subString == null) {
            throw new IllegalArgumentException("Not null");
        }

        final String searchString = subString;
        Predicate<String> predicate = date -> !(date == null) && date.contains(searchString);
        super.checkList.add(predicate);
        return this;
    }

}
