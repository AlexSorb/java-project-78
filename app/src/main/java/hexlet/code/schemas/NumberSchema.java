package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    private final static String POSITIVE_SCHEMA_NAME = "positive";
    private final static String RANGE_SCHEMA_NAME = "range";

    public NumberSchema positive() {
        Predicate<Integer> predicate = date -> date == null || date > 0;
        super.namedPredicate.put(POSITIVE_SCHEMA_NAME, predicate);
        return this;
    }

    public NumberSchema range(Integer left, Integer right) throws IllegalArgumentException {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Граница диапазона не может быть null");
        }

        int leftBord = Math.min(left, right);
        int rightBord = Math.max(left, right);

        Predicate<Integer> predicate = data -> {
            if (data == null) {
                return false;
            }
            return  (leftBord <= data) && (data <= rightBord);
        };
        super.namedPredicate.put(RANGE_SCHEMA_NAME, predicate);
        return this;
    }

}
