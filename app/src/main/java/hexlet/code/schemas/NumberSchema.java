package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer>{
    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> predicate = date -> date == null || date > 0;
        super.checkList.add(predicate);
        return this;
    }

    public NumberSchema range(Integer left, Integer right) throws IllegalArgumentException {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Граница диапазона не может быть null");
        }

        int leftBord = Math.min(left, right);
        int rightBord = Math.max(left, right);

        Predicate<Integer> predicate = data -> (leftBord <= data) && (data <= rightBord);
        super.checkList.add(predicate);
        return this;
    }

}
