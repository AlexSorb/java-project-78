package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer>{
    private boolean flagPositive = false;
    private boolean flagRange = false;
    private Integer left;
    private Integer right;

    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        flagPositive = true;
        return this;
    }

    public NumberSchema range(Integer left, Integer right) throws IllegalArgumentException {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Граница диапазона не может быть null");
        }

        if (left > right) {
            left = left + right;
            right = left - right;
            left = left - right;
        }

        flagRange = true;
        this.left = left;
        this.right = right;
        return this;
    }

    public boolean isValid(Integer number) {
        boolean isValid = super.isValid(number);

        if (flagPositive) {
            isValid = number == null ? true : number > 0;
        }

        if (flagRange) {
            isValid = number >= left && number <= right;
        }

        return isValid;
    }
}
