package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    private static final String POSITIVE_SCHEMA_NAME = "positive";
    private static final String RANGE_SCHEMA_NAME = "range";

    public final NumberSchema positive() {
        Predicate<Integer> predicate = date -> date == null || date > 0;
        super.checks.put(POSITIVE_SCHEMA_NAME, predicate);
        return this;
    }

    public final NumberSchema range(Integer left, Integer right) throws IllegalArgumentException {
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
        super.checks.put(RANGE_SCHEMA_NAME, predicate);
        return this;
    }

}
