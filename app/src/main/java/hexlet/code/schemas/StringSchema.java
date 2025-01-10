package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        Predicate<String> predicate = data -> data != null && !data.isEmpty();
        super.checkList.add(predicate);

        // Update
        String predicateName = "required";
        namedPredicate.put(predicateName, predicate);
        return this;
    }
    public StringSchema minLength(int minLength) {
        final int min = Math.max(minLength, 0);
        Predicate<String> predicate = data -> data != null && data.length() >= min;
        super.checkList.add(predicate);

        // Update
        String predicateName = "minLength";
        super.namedPredicate.remove(predicateName);
        super.namedPredicate.put(predicateName, predicate);
        return this;
    }

    public StringSchema contains(String subString) {
        if (subString == null) {
            throw new IllegalArgumentException("Not null");
        }

        final String searchString = subString;
        Predicate<String> predicate = date -> !(date == null) && date.contains(searchString);
        super.checkList.add(predicate);


        // Update
        String predicateName = "contains";
        super.namedPredicate.put(predicateName, predicate);
        return this;
    }

}
